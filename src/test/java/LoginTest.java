import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


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

        // --- БЛОК ТОТАЛЬНОЙ ОТЛАДКИ ---
        try {
            // "Грязная", но эффективная пауза для диагностики.
            // Если с ней тест пройдет, значит проблема в сверхбыстрой перерисовке страницы.
            log.info("Добавляем 1-секундную паузу для стабилизации DOM...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Прикрепляем исходный код страницы к отчету Allure СРАЗУ ПОСЛЕ КЛИКА
        log.info("Прикрепляем исходный код страницы для анализа...");
        Allure.addAttachment("Page Source After Submit", "text/html", driver.getPageSource(), ".html");
        // ---------------------------------

        if (expectedResult.equals("Successful")) {
            // ... логика для успешного сценария ...
            log.info("Проверяем сценарий успешного входа");
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            Assert.assertTrue(loggedInPage.isLogoutButtonDisplayed(), "Кнопка 'Log out' не отображается после успешного входа.");
        } else if (expectedResult.equals("Error")) {
            // ... логика для негативного сценария ...
            log.info("Проверяем сценарий с ошибкой входа");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отображается после неудачного входа!");
        }
    }
}

