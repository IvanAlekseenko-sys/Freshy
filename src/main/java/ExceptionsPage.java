import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExceptionsPage extends BasePage {
    public ExceptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='row2']/input")
    private WebElement row2Input;


    public boolean isRow2InputDisplayed() {
        return row2Input.isDisplayed();

    }
}
