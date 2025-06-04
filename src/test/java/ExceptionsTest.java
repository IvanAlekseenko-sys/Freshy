import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionsTest extends BaseTest {


    @Test
    public void testNoSuchElementException() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl + "/practice-test-exceptions/");
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.clickAddButton();
        Assert.assertTrue(exceptionsPage.isRow2InputDisplayed());
    }

//    @Test
//    public void testNoSuchElementException(){
//        String baseUrl = ConfigReader.getProperty("baseUrl");
//        driver.get(baseUrl + "/practice-test-exceptions/");
//
//        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
//        Assert.assertThrows(NoSuchElementException.class, () -> {
//            exceptionsPage.isRow2InputDisplayed();
//        });
}

