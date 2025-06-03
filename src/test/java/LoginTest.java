import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
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
        // Используем неверный пароль
        LoggedInPage loggedInPage = loginPage.login("student", "WrongPassword");

        // Эта проверка упадет, так как мы не перейдем на следующую страницу
        Assert.assertTrue(loggedInPage.isLogoutButtonPresent(), "Кнопка 'Log out' не должна была появиться!");
    }
}
