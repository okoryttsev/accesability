import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author Sasha on 3/21/2017.
 */
public class ChromeTest {
    private static final Logger LOGGER = Logger.getLogger( GoogleTest.class.getName() );

    private WebDriver driver;

    @Before
    public void setDriver(){

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        
        options.addExtensions(new File("chrome_plugin/extension_1_0_5.crx"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void OpenBrowser(){

        driver.get("https://www.google.com/");
        GooglePage googlePage = PageFactory.initElements(driver,GooglePage.class);
        googlePage.setValue("MNTU");
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement result = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='resultStats']")));
        LOGGER.warning(result.getText());
    }

    @After
    public void CloseAll(){
        driver.close();
        driver.quit();
    }
}
