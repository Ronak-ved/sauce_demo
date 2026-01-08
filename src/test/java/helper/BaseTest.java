package helper;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities_classes.WebDriver_Utils;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest testReporter;
    protected Logger log = LogManager.getLogger(BaseTest.class);

    // ---------- REPORT SETUP ----------
    @BeforeSuite
    public void beforeSuite() {
        String out = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(out);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        log.info("ExtentReports initialized at {}", out);
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
            log.info("ExtentReports flushed");
        }
    }

    // ---------- BROWSER SETUP (FLOW) ----------
    @BeforeClass
    public void setupClass() {
        driver = WebDriver_Utils.createDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        log.info("Browser started for FLOW test");
    }

    @AfterClass
    public void teardownClass() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed after FLOW test");
        }
    }

    // ---------- EXTENT TEST PER METHOD ----------
    @BeforeMethod
    public void startTest(Method m) {
        testReporter = extent.createTest(m.getName());
    }
}
