package Tests;

import BaseUtility.MobileBaseUtility;
import BaseUtility.ReadFromExcel;
import Pages.HomePage;
import Pages.LoginPage;
import genralized_utillity.ExtentReport.ExtentTestManager;
import genralized_utillity.Log4j.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LoginTests extends MobileBaseUtility {

    List<String> data = new ArrayList<>();

    /*
     *This method read  input form given external source
     */
    @BeforeMethod
    public void setup() throws IOException {

        //Create an object of ReadFromExcel class

        ReadFromExcel objExcelFile = new ReadFromExcel();

        //Prepare the path of excel file

        String filePath = System.getProperty("user.dir") + "/src/test/resources/app";

        //Call read file method of the class to read data

        data = objExcelFile.readExcel(filePath, "Amazon_data.xlsx", "itemSheet");

    }


    @Test
    public void verifyLoginSuccessful(Method method) {

        ExtentTestManager.startTest(method.getName(), "Login Successful");

        Log.startTestCase("verifyLoginSuccessful");

        loginToAmazon();

        Log.info("Login to amazon application");

        Log.endTestCase();

    }

    @Test
    public void verifyLoginUnSuccessful(Method method) {

        ExtentTestManager.startTest(method.getName(), "Login Unsuccessful");

        Log.startTestCase("verifyLoginUnSuccessful");

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        Boolean userName = login.enterUser().isDisplayed();
        Assert.assertTrue(userName);
        login.enterUser().click();
        login.enterUser().sendKeys(data.get(3));
        Boolean password = login.enterPassword().isDisplayed();
        Assert.assertTrue(password);
        login.clickContinue().click();
        login.enterPassword().click();
        login.enterPassword().sendKeys(data.get(4));
        login.loginToAmazon().click();
        HomePage homePage = new HomePage(driver);
        Boolean home = homePage.homeNavigation().isDisplayed();
        Assert.assertFalse(home);

        Log.info("Login to amazon application failed");

        Log.endTestCase();

    }


    @Test
    public void verifyCorrectUsernameAndWrongPassword(Method method) {

        ExtentTestManager.startTest(method.getName(), "verify Correct Username And Wrong Password");


        Log.startTestCase("verifyCorrectUsernameAndWrongPassword");

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        Boolean userName = login.enterUser().isDisplayed();
        Assert.assertTrue(userName);
        login.enterUser().click();
        login.enterUser().sendKeys(data.get(3));
        Boolean password = login.enterPassword().isDisplayed();
        Assert.assertTrue(password);
        login.clickContinue().click();
        login.enterPassword().click();
        login.enterPassword().sendKeys(data.get(4));
        login.loginToAmazon().click();

        String msg = login.enteredInvalidPassword().getText();
        Assert.assertEquals("Password is Incorrect, Please Enter a Valid Password",msg);

        Log.endTestCase();

    }


    @Test
    public void verifyLoginWithUsernameWithoutEnteringData(Method method) {

        ExtentTestManager.startTest(method.getName(), "Verify Forgot Password");


        Log.startTestCase("verifyLoginWithUsernameWithoutEnteringData");

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        Boolean userName = login.enterUser().isDisplayed();
        Assert.assertTrue(userName);
        login.clickContinue().click();

        String msg = login.userNameBlank().getText();
        Assert.assertEquals("Please Enter a UserName",msg);

        Log.endTestCase();

    }

    @Test
    public void verifyLoginWithPasswordWithoutEnteringData(Method method) {

        ExtentTestManager.startTest(method.getName(), "Verify Forgot Password");


        Log.startTestCase("verifyLoginWithPasswordWithoutEnteringData");

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        Boolean userName = login.enterUser().isDisplayed();
        Assert.assertTrue(userName);
        login.enterUser().click();
        login.enterUser().sendKeys(data.get(3));
        Boolean password = login.enterPassword().isDisplayed();
        Assert.assertTrue(password);
        login.clickContinue().click();
        login.loginToAmazon().click();

        String msg = login.passwordBlank().getText();
        Assert.assertEquals("Please Enter a Password",msg);

        Log.endTestCase();

    }


    /*
     *This method will login to amazon application
     */
    public void loginToAmazon() {

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        Boolean userName = login.enterUser().isDisplayed();
        Assert.assertTrue(userName);
        login.enterUser().click();
        login.enterUser().sendKeys(data.get(3));
        Boolean password = login.enterPassword().isDisplayed();
        Assert.assertTrue(password);
        login.clickContinue().click();
        login.enterPassword().click();
        login.enterPassword().sendKeys(data.get(4));
        login.loginToAmazon().click();
        HomePage homePage = new HomePage(driver);
        Boolean home = homePage.homeNavigation().isDisplayed();
        Assert.assertTrue(home);
    }


    /*
     * This will kill the application after test case completion
     */
    @AfterMethod
    public void teardown() {

        driver.closeApp();
    }
}
