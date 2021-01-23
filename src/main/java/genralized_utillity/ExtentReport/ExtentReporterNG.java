package genralized_utillity.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
----->This is Standard Template to create ExtentReport at Suite level using TestNG.xml
 */
public class ExtentReporterNG implements IReporter {
    private static final String FILE_NAME = "Android_Report";

    private ExtentReports extent;

    /*
     * this method will use to generate report
     * * params - xmlSuites - suites from xmlfile
     * * params - suites - suites from class file
     * * params - outputDirectory - directory where report will be saved
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);

            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    /*
     * this method will generate report path and naming, timestamps, ect
     */
    private void init() {

        String dateName = new SimpleDateFormat("yyyy-MM-dd::hh-mm").format(new Date());
        String Destination = System.getProperty("user.dir") + "TestNGReports/" + FILE_NAME + "_" + dateName + ".html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Destination);
        htmlReporter.config().setDocumentTitle("ExtentReports - Wawa_Hybrid_Report");
        htmlReporter.config().setReportName("ExtentReports - Android_Version_7.1");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }
    /*
     * this method will use to build the nodes
     * params - tests - test case name from resultSet
     * params - status - test case status
     */

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
        WebDriver driver;


        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());

                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                    /*
                    ---> Below statement can add logs to Report for passed test cases

                    test.log(Status.INFO,"THIS IS SAMPLE TEST Passed");
                    */

                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }
    /*
     * this method get current timestamp
     * params : millis - time in mili seconds
     */
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
