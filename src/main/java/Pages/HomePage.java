package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final String homeLogo = "logo_img";

    AppiumDriver driver;
    @FindBy(id = homeLogo)
    MobileElement homePage;



    // constructor method
    // params - driver - instance of driver
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement homeNavigation() {
        return homePage;
    }

}
