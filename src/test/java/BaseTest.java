import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
   public void setUp() {
        log.info("Тест запускается...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log.info("Браузер успешно запущен и развернут на весь экран.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Браузер успешно закрыт.");
        }
    }
}
