import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement submitButton;

    public void enterUsername(String username) {
        log.info("Вводим имя пользователя: '{}'", username);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        log.info("Вводим пароль...");
        passwordField.sendKeys(password);
    }

    public LoggedInPage clickSubmitButton() {
        log.info("Нажимаем кнопку 'Submit'");
        submitButton.click();
        return new LoggedInPage(driver);
    }

    public LoggedInPage login(String username, String password) {
        log.info("Выполняем полный процесс логина для пользователя '{}'", username);
        enterUsername(username);
        enterPassword(password);
        return clickSubmitButton();
    }
}
