import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Тест упал: {}", result.getName());
        log.info("Делаем скриншот...");

        // Получаем доступ к WebDriver из нашего BaseTest
        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTest) testInstance).driver;

        // Делаем скриншот
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Создаем уникальное имя для файла скриншота с датой и временем
        String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotName = result.getName() + "_" + timestamp + ".png";
        String screenshotPath = "build/screenshots/" + screenshotName;

        try {
            // Копируем файл скриншота в папку build/screenshots
            FileUtils.copyFile(scrFile, new File(screenshotPath));
            log.info("Скриншот сохранен: {}", screenshotPath);
        } catch (IOException e) {
            log.error("Не удалось сохранить скриншот", e);
        }
    }
}