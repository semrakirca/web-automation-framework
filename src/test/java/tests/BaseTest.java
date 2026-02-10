
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.firefox.FirefoxDriver;


// Bu sınıf tüm testlerin temelidir
public class BaseTest {

    // Tüm testlerin kullanacağı driver
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    // Her testten önce çalışır
    @BeforeMethod
    public void setUp() {

        extent = ExtentReportManager.getReportInstance();

        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Desteklenmeyen browser: " + browser);
        }

        driver.manage().window().maximize();
    }


    // Her testten sonra çalışır
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test başarıyla geçti");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test başarısız oldu: " + result.getThrowable());
        }

        extent.flush();

        if (driver != null) {
            driver.quit();
        }
    }

}
