import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInPage extends BasePage {
    public LoggedInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Log out']")
    private WebElement logoutButton;

    boolean isLogoutButtonPresent() {
        return logoutButton.isDisplayed();
    }
}
