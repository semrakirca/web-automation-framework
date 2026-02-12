
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;


// Bu sınıf tüm testlerin temelidir
public class BaseTest {

    // Tüm testlerin kullanacağı driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ExtentReports extent;
    protected ExtentTest test;

    protected WebDriver getDriver() {
        return driver.get();
    }

    // Her testten önce çalışır
    @BeforeMethod
    public void setUp() {

        extent = ExtentReportManager.getReportInstance();

        String browser = ConfigReader.get("browser");
        String headless = ConfigReader.get("headless");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());

        } else {
            throw new RuntimeException("Desteklenmeyen browser: " + browser);
        }

        getDriver().manage().window().maximize();
    }


    // Her testten sonra çalışır
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test başarıyla geçti");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(getDriver(), result.getName());

            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // THREAD TEMİZLİĞİ (ÇOK ÖNEMLİ)
        }
    }
}
