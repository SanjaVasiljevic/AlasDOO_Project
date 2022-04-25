package PagesSauceDemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
public class InventorySauceDemo {
    WebDriver driver;
    Select select;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartBackpack;
    public void clickOnAddToCart1Button() {
        addToCartBackpack.click();
    }
    @FindBy (id ="add-to-cart-sauce-labs-bike-light")
    WebElement addToCartBikeLight;
    public void clickOnAddToCart2Button() {
        addToCartBikeLight.click();
    }
    @FindBy(id= "shopping_cart_container")
    WebElement shoppingCart;
    public void clickOnCart() {
        shoppingCart.click();
    }
    @FindBy(className = "select_container")
    WebElement sortingButton;
    public void clickOnSortingButton() {
        sortingButton.click();
    }
    @FindBy(className = "inventory_list")
    List<WebElement> inventoryItemNames;
    @FindBy(id = "item_3_img_link")
    WebElement redTShirtItem;
    @FindBy(className = "product_sort_container")
    WebElement sortingButtonOptions;
    public void selectSortingOptionHighToLow() {
        select = new Select(sortingButtonOptions);
        select.selectByValue("hilo");
    }
    @FindBy(className = "inventory_item_price")
    List<WebElement> inventoryPrice;
    public boolean checkSortingByPriceHighToLow() {
        int totalNumberOfEntries = inventoryPrice.size() - 1;
        boolean test = true;
        for (int i = 0; i <= totalNumberOfEntries; i++) {
            if (i == totalNumberOfEntries) {
                test = true;
            } else {
                String currentPrice = inventoryPrice.get(i).getText().replace("$", "");
                String nextPrice = inventoryPrice.get(i + 1).getText().replace("$", "");
                double currentPrice2 = Double.parseDouble(currentPrice);
                double nextPrice2 = Double.parseDouble(nextPrice);
                test = currentPrice2 > nextPrice2;
            }
        }
        return test;
    }
    public void selectSortingOptionAtoZ() {
        select = new Select(sortingButtonOptions);
        select.selectByValue("az");
    }
    public boolean checkSortingByNameAtoZ() {
        for (int i = 0; i < inventoryItemNames.size(); i++) {
            if (redTShirtItem == inventoryItemNames.get(0)) return true;
        }
        return false;
    }
    public InventorySauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
