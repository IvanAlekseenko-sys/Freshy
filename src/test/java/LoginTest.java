import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);

        loginPage.login("student", "Password123");

        Assert.assertTrue(loggedInPage.isLogoutButtonPresent());
        String expectedUrl = baseUrl + "/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testFailedLogin() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);

        // Пытаемся залогиниться с неверным паролем.
        // Нам не важен результат этого метода (объект LoggedInPage),
        // так как мы знаем, что перехода на новую страницу не будет.
        loginPage.login("student", "WrongPassword");

        // ПРОВЕРКА №1: Убедимся, что сообщение об ошибке вообще появилось.
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отображается после неудачного входа!");

        // ПРОВЕРКА №2: Убедимся, что текст сообщения корректный.
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Текст сообщения об ошибке не соответствует ожидаемому!");
    }
}
