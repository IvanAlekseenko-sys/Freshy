import io.qameta.allure.Allure; // <-- Новый импорт!
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream; // <-- Новый импорт!

public class TestListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    // Старый метод с аннотацией @Attachment нам больше не нужен, его можно удалить.

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Тест упал: {}", result.getName());
        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTest) testInstance).driver;

        if (driver != null) {
            log.info("Делаем скриншот...");
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Добавляем тип контента и расширение файла для надежности
            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } else {
            log.error("WebDriver был null, скриншот не сделан.");
        }
    }
}