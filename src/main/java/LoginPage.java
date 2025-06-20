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

    public WebElement getErrorElement() {
        return errorMessage;
    }

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public void enterUsername(String username) {
        waitForElementToBeClickable(usernameField);
        log.info("Вводим имя пользователя: '{}'", username);
        jsSendKeys(usernameField, username);    }

    public void enterPassword(String password) {
        waitForElementToBeClickable(passwordField);
        log.info("Вводим пароль...");
        jsSendKeys(passwordField, password);    }

    public void clickSubmitButton() {
        waitForElementToBeClickable(submitButton);
        log.info("Нажимаем кнопку 'Submit'");
        jsClick(submitButton);   }

    public void login(String username, String password) {
        log.info("Выполняем полный процесс логина для пользователя '{}'", username);
        enterUsername(username);
        enterPassword(password);
        clickSubmitButton();
    }

    public boolean isErrorMessageDisplayed() {
        log.info("Проверяем видимость сообщения об ошибке");
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        String text = errorMessage.getText();
        log.info("Получен текст сообщения об ошибке: '{}'", text);
        return text;
    }


}
