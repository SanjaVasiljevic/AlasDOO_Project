package PagesSauceDemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartSauceDemo {
    WebDriver driver;
    @FindBy(className = "inventory_item_name")
    WebElement productTitle;
    public String getProductTitle() {
        return productTitle.getText();
    }
    @FindBy(id = "cart_contents_container")
    WebElement cartContent;
    public boolean checkCartContentFor2Items() {
        return cartContent.getText().contains("Sauce Labs Backpack") &&
                cartContent.getText().contains("Sauce Labs Bike Light");
    }
    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeButton1;
    @FindBy(id = "item_0_title_link")
    WebElement cartItemBikeLight;
    public void clickOnRemoveButton1() {
        removeButton1.click();
    }
    public boolean checkCartContentAfterRemoving1Item() {
        return cartItemBikeLight.getText().contains("Sauce Labs Bike Light");
    }
    @FindBy(id = "checkout")
    WebElement checkoutButton;
    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }
    public CartSauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
