import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionsPage extends BasePage {
    public ExceptionsPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger log = LoggerFactory.getLogger(ExceptionsPage.class);

    @FindBy(xpath = "//div[@id='row2']/input")
    private WebElement row2Input;

    @FindBy(name = "Add")
    private WebElement addButton;

    public boolean isRow2InputDisplayed() {
        waitForVisibilityOf(row2Input);
        return row2Input.isDisplayed();

    }

    public void clickAddButton() {
        waitForElementToBeClickable(addButton); // Используем наш хелпер ожидания
        log.info("Нажимаем кнопку 'Add'");
        addButton.click();
    }
}
