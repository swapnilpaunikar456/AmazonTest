package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    AppiumDriver driver;

    private final String loginLocate = "sign_in_button";
    private final String userLocate = "//android.widget.EditText[@text=’username’]";
    private final String continueLocate = "//android.widget.Button[@text='Continue']";
    private final String passwordLocate = "//android.widget.EditText[@text=’password’]";
    private final String signInLocate = "//android.widget.Button[@text='Sign-In']";


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

    public WebElement loginToAmazon() {
        return signInButton;
    }

}
