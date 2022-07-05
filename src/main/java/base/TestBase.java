package base;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utlities.Constants;
import utlities.ScreenShoot;
import utlities.TestData;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;
    public static ExtentReports ExtentReport;
    public static ExtentTest ExtentTest;
    public static Logger logger = Logger.getLogger(TestBase.class);
    public TestData testDataReader;

    // Download from chrome
    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromePrefs.put("profile.default.content_settings.popups", 0);
        options.addArguments("--window-size=1024x768");
        return options;
    }

    // Download from FireFox
    public static FirefoxOptions firefoxOption() {
        FirefoxOptions option = new FirefoxOptions();
        option.setAcceptInsecureCerts(true);
        option.addPreference("browser.download.folderList", 2);
        option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        option.addPreference("browser.download.manager.showWhenStarting", false);
        option.setCapability("resolution", "1024x768");
        return option;
    }

    @BeforeTest
    @Parameters({"browser"})
    public void openDriver(@Optional("firefox") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOption());
            logger.info("driver started");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOption());
            logger.info("driver started");
        }
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com/index.php");
        testDataReader = new TestData(Constants.ExcelFilePath, Constants.SheetName);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ExtentReport = new ExtentReports(Constants.ReportPath + "\\report.html", true);
        ExtentReport.addSystemInfo("OS", "Windows");
        ExtentReport.addSystemInfo("Language", "Java");
        ExtentReport.addSystemInfo("FrameWork", "Page Object Model");
        ExtentReport.addSystemInfo("Owner", Constants.OwnerName);
    }

    @BeforeMethod
    public void ruNBeforeMethod(Method method) {
        ExtentTest = ExtentReport.startTest(method.getName());
    }

    @AfterMethod
    public void afterTestEnd(Method method, ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Success!");
            ExtentTest.log(LogStatus.PASS, "Test Pass");
            System.out.println("Taking Screenshot....");
            ScreenShoot.takePicture(method.getName());
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            ExtentTest.log(LogStatus.FAIL, result.getThrowable());


        } else if
        (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Skipped!");
            ExtentTest.log(LogStatus.SKIP, "Test Skipped ");
        }
        ExtentReport.endTest(ExtentTest);
        ExtentReport.flush();
    }

    @AfterSuite
    public void end() {
        driver.quit();

    }

}
