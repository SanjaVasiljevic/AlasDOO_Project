package PagesSauceDemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CheckOutCompleteSauceDemo {
    WebDriver driver;
    @FindBy(id = "checkout_complete_container")
    WebElement checkoutConfirmation;
    public boolean checkCheckoutConfirmation() {
        return checkoutConfirmation.isDisplayed();
    }
    @FindBy(linkText = "THANK YOU FOR YOUR ORDER")
    WebElement checkoutMessage;
    public boolean checkCheckoutMessage() {
        return checkoutMessage.isDisplayed();
    }
    public CheckOutCompleteSauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
