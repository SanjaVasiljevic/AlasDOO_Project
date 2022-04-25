package PagesSauceDemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CheckOutFinishSauceDemo {
    WebDriver driver;
    @FindBy(id = "item_4_title_link")
    WebElement itemBikeLightDisplayed;
    public boolean checkIfCorrectHeaderIsDisplayed() {
        if (itemBikeLightDisplayed.isDisplayed()) return true;
        else return false;
    }
    @FindBy(id = "finish")
    WebElement finishButton;
    public void clickOnFinishButton() {
        finishButton.click();
    }
    public CheckOutFinishSauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}





