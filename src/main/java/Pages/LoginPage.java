package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final String loginLocate = "sign_in_button";
    private final String userLocate = "//android.widget.EditText[@text=’username’]";
    private final String continueLocate = "//android.widget.Button[@text='Continue']";
    private final String passwordLocate = "//android.widget.EditText[@text=’password’]";
    private final String signInLocate = "//android.widget.Button[@text='Sign-In']";
    private final String incorrectUserName = "//android.widget.Button[@text='User is Invalid, Please Enter a Valid UserName']";
    private final String incorrectPassword = "//android.widget.Button[@text='Password is Incorrect, Please Enter a Valid Password']";
    private final String plzEnterUserNameMessage = "//android.widget.Button[@text='Please Enter a UserName']";
    private final String plzEnterPasswordMessage = "//android.widget.Button[@text='Please Enter a Password']";


    AppiumDriver driver;
    @FindBy(id = loginLocate)
    MobileElement gotoLogin;

    @FindBy(xpath = userLocate)
    MobileElement username;

    @FindBy(xpath = continueLocate)
    MobileElement continueButton;

    @FindBy(xpath = passwordLocate)
    MobileElement password;

    @FindBy(xpath = signInLocate)
    MobileElement signInButton;

    @FindBy(xpath = incorrectUserName)
    MobileElement wrongUser;

    @FindBy(xpath = incorrectPassword)
    MobileElement wrongPassword;

    @FindBy(xpath = plzEnterUserNameMessage)
    MobileElement plzEnterUser;

    @FindBy(xpath = plzEnterPasswordMessage)
    MobileElement plzEnterPassword;


    // constructor method
    // params - driver - instance of driver
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement gotoLoginPage() {
        return gotoLogin;
    }

    public WebElement enterUser() {
        return username;
    }

    public WebElement clickContinue() {
        return continueButton;
    }

    public WebElement enterPassword() {
        return password;
    }

    public WebElement enteredInvalidUserName() {
        return wrongUser;
    }

    public WebElement enteredInvalidPassword() {
        return wrongPassword;
    }

    public WebElement userNameBlank() {
        return plzEnterUser;
    }

    public WebElement passwordBlank() {
        return plzEnterPassword;
    }

    public WebElement loginToAmazon() {
        return signInButton;
    }

}
