import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;


@Feature("Authentication")
public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @DataProvider(name = "loginScenarios")
    public Object[][] loginData() {
        return new Object[][]{
                //Successful login
                {"student", "Password123", "Successful"},
                //Wrong password
                {
                        "student", "WrongPassword", "Error"
                },
                //Wrong login
                {
                        "incorrectUser", "Password123", "Error"}
        };
    }

    @Test(dataProvider = "loginScenarios")
    @Story("Login Scenarios")
    @Description("This test covers both successful and failed login scenarios.")
    public void testLogin(String username, String password, String expectedResult) {
        log.info("Запуск теста для пользователя '{}' с ожидаемым результатом '{}'", username, expectedResult);
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // --- НОВОЕ УМНОЕ ОЖИДАНИЕ ---
        // Ждем, пока либо изменится URL (успех), либо появится сообщение об ошибке (неудача)
        log.info("Ожидаем обновления страницы после отправки формы...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("logged-in-successfully"),
                ExpectedConditions.visibilityOf(loginPage.getErrorElement())
        ));
        log.info("Страница обновилась. Переходим к проверкам.");
        // -----------------------------

        if (expectedResult.equals("Successful")) {
            log.info("Проверяем сценарий успешного входа");
            // Проверку на URL можно даже убрать, так как wait уже это сделал,
            // но для наглядности оставим.
            Assert.assertTrue(driver.getCurrentUrl().contains("logged-in-successfully"), "URL не соответствует странице успешного входа.");
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            Assert.assertTrue(loggedInPage.isLogoutButtonDisplayed(), "Кнопка 'Log out' не отображается после успешного входа.");
        } else if (expectedResult.equals("Error")) {
            log.info("Проверяем сценарий с ошибкой входа");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отображается после неудачного входа!");
        }
    }
}

