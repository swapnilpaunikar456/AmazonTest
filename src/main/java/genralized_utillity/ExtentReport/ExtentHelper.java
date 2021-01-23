package genralized_utillity.ExtentReport;

import BaseUtility.MobileBaseUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import genralized_utillity.Screenshot.Screenshot;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Method;

/*
------>This class and its functions can be used to create customized extent Report.
 */

public class ExtentHelper extends MobileBaseUtility {


    // Use it after class Declaration
    private static ExtentReports reports;
    private static ExtentTest testinfo;
    private static ExtentHtmlReporter htmlReporter;

    public ExtentHelper() {
    }


    //use at @BeforeTest
    public static void setup() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/CustomizedTestNGReport/test.html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/extent.xml");
        reports = new ExtentReports();
        reports.setSystemInfo("Enviornment", "QA");
        reports.attachReporter(htmlReporter);

    }

    //use at @Test
    public static void register(Method method) {
        String testname = method.getName();
        testinfo = reports.createTest(testname);

    }

    //use at @AfterTest
    public static void teardown() {
        reports.flush();
    }

    //use at @AfterMethod
    public static void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            testinfo.log(Status.FAIL, "Test Case Failed is " + result.getName());
            testinfo.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"

            String screenshotPath = Screenshot.getScreenshot(driver, result.getName());
            //To add it in the extent report
            //testinfo.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
            testinfo.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

    }
}
