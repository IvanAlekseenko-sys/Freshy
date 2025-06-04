import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // Инициализируем WebDriverWait с таймаутом в 10 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // <-- Инициализируем
    }

    // --- ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ОЖИДАНИЙ ---

    /**
     * Ожидает, пока элемент станет видимым на странице.
     * @param element Веб-элемент, которого мы ждем.
     */
    protected void waitForVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает, пока элемент станет кликабельным.
     * @param element Веб-элемент, которого мы ждем.
     */
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
