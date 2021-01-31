package Tests;

import Actions.Action;
import BaseUtility.MobileBaseUtility;
import BaseUtility.ReadFromExcel;
import Pages.LoginPage;
import Pages.SearchResult;
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

public class AmazonApplication extends MobileBaseUtility {

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
    public void verifyAmazonApplicationItemPurchase(Method method) {

        ExtentTestManager.startTest(method.getName(), "verify AmazonApplication Item Purchase");


        Log.startTestCase("verifyAmazonApplicationItemPurchase");

        loginToAmazon();

        Log.info("Login to amazon application");

        purchaseItem();

        Log.info("search and purchase item");

        Log.endTestCase();

    }

    /*
     *This method will login to amazon application
     */
    public void loginToAmazon() {

        LoginPage login = new LoginPage(driver);
        login.gotoLoginPage().click();
        login.enterUser().click();
        login.enterUser().sendKeys(data.get(3));
        login.clickContinue().click();
        login.enterPassword().click();
        login.enterPassword().sendKeys(data.get(4));
        login.loginToAmazon().click();
    }

    /*
     *This method will search an item and go to checkout page
     */
    public void purchaseItem() {

        SearchResult searchResult = new SearchResult(driver);
        Boolean condition = searchResult.searchElement().isDisplayed();
        Assert.assertTrue(condition);
        Action.verticalSwipe(250, 380);
        searchResult.searchElement().click();
        searchResult.searchElement().sendKeys(data.get(5));
        searchResult.pickItem().click();
        Boolean buyCondition = searchResult.checkBuyingOption().isDisplayed();
        Assert.assertTrue(buyCondition);
        searchResult.checkBuyingOption().click();
    }

    /*
     * This will kill the application after test case completion
     */
    @AfterMethod
    public void teardown() {

        driver.closeApp();
    }
}
