package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult {

    AppiumDriver driver;

    private final String searchBoxLocate = "rs_search_src_text";
    private final String lastRecordLocate = "//android.view.View[contains(@text,'Sceptre')][1]";
    private final String seeAllLocate = "rs_search_src_text";

    @FindBy(id = searchBoxLocate)
    MobileElement searchBox;

    @FindBy(xpath = lastRecordLocate)
    MobileElement lastRecord;

    @FindBy(xpath = seeAllLocate)
    MobileElement seeAllBuyingOption;


    // constructor method
    // params - driver - instance of driver
    public SearchResult(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement searchElement() {
        return searchBox;
    }

    public WebElement pickItem() {
        return lastRecord;
    }

    public WebElement checkBuyingOption() {
        return seeAllBuyingOption;
    }

}
