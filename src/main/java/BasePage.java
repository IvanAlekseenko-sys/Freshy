import org.openqa.selenium.JavascriptExecutor;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void waitForVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // --- НОВЫЕ ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ---

    /**
     * Выполняет клик с помощью JavaScript. Более надежен в headless-режиме.
     * @param element Веб-элемент, по которому нужно кликнуть.
     */
    protected void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Вводит текст в элемент с помощью JavaScript. Более надежен в headless-режиме.
     * @param element Веб-элемент, в который нужно ввести текст.
     * @param text Текст для ввода.
     */
    protected void jsSendKeys(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, text);
    }
}