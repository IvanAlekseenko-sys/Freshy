import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // <-- Новый импорт
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        log.info("Тест запускается...");

        // Создаем объект опций для Chrome
        ChromeOptions options = new ChromeOptions();

        // Проверяем, запущен ли тест в среде CI (GitHub Actions установит эту переменную)
        if (System.getenv("CI") != null) {
            log.info("Запуск в режиме CI/CD. Включаем headless-режим.");
            // Добавляем аргументы для запуска в headless-режиме
            options.addArguments("--headless");
            options.addArguments("--no-sandbox"); // Часто требуется для запуска в Docker/CI
            options.addArguments("--disable-dev-shm-usage"); // Преодоление проблем с ресурсами
            options.addArguments("--window-size=1920,1080"); // Устанавливаем размер окна
        }

        // Передаем наши опции в конструктор ChromeDriver
        driver = new ChromeDriver(options);

        // В обычном режиме (не headless) разворачиваем окно
        if (System.getenv("CI") == null) {
            driver.manage().window().maximize();
        }

        log.info("Браузер успешно запущен.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Браузер успешно закрыт.");
        }
    }
}