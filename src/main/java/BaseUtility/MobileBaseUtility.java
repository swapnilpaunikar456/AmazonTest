package BaseUtility;

import genralized_utillity.Log4j.Log;
import genralized_utillity.Screenshot.Screenshot;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
--> Base class for android to set all desired capability
 */

public class MobileBaseUtility {

    protected static AppiumDriver driver;
    protected static WebDriverWait wait;


    //Before Class Annotation makes java function to run every time before a TestNG Test class
    @BeforeClass
    protected void CreateAppiumDriver() {


        try {

            //Setting up Desired Capability
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "emulator-5554");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
            capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("autoGrantPermissions", "true");
            capabilities.setCapability("newCommandTimeout", 500);


            //Relative Path to .apk file
            final File classpath = new File(System.getProperty("user.dir"));
            final File appdir = new File(classpath, "src/test/resources/app/");
            //final File app = new File(appdir, AppVersion.getAPPTITLE() + ".apk");
            final File app = new File(appdir, "Amazon_shopping.apk");
            capabilities.setCapability("app", app.getAbsolutePath());

            //Initializing driver object
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            //Initializing explicit wait object
            driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
            wait = new WebDriverWait(driver, 30);

        } catch (Exception e) {

            Log.error("Found issue in Android Desired Capability");
            System.out.println(e);
        }
    }


    //After Class Annotation makes java function to run every time after a TestNG Test class
    @AfterSuite
    public void End() {
        //quit the driver
        driver.quit();
    }


    //Capture Screenshot for failed testCases
    //Params : result - it gives test case pass/fail status
    @AfterMethod
    public void screenshotTest(ITestResult result) {

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                Screenshot.getScreenshot(driver, result.getName());
            }
        } catch (Exception e) {
            Log.error("Screenshot method Issue");
            System.out.println(e);
        }
    }
}

