import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ExceptionsTest extends BaseTest{

    @Test
    public void testNoSuchElementException(){
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-exceptions/");

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        Assert.assertThrows(NoSuchElementException.class, () -> {
            exceptionsPage.isRow2InputDisplayed();
        });
    }
}
