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
    @Description("This test covers both successful and failed login scenarios")
    public void testLogin(String username, String password, String expectedResult) {
        log.info("Initialising test for user '{}' with expected result '{}'", username, expectedResult);
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-login/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        if (expectedResult.equals("Successful")) {
            log.info("Checking successful login scenario");
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            Assert.assertTrue(loggedInPage.isLogoutButtonPresent(), "Logout button isn't present after successful login");
        } else if (expectedResult.equals("Error")) {
            log.info("Checking error scenario");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message isn't displayed after failed login");
        }
    }
}

