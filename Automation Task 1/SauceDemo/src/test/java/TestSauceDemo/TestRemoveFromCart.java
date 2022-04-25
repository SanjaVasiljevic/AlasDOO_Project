package TestSauceDemo;
import PagesSauceDemo.CartSauceDemo;
import PagesSauceDemo.InventorySauceDemo;
import PagesSauceDemo.LogInSauceDemo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
public class TestRemoveFromCart {
    private WebDriver driver;
    private LogInSauceDemo logInSauceDemo;
    private CartSauceDemo cartSauceDemo;
    private InventorySauceDemo inventorySauceDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logInSauceDemo = new LogInSauceDemo(driver);
        cartSauceDemo = new CartSauceDemo(driver);
        inventorySauceDemo = new InventorySauceDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    }

    /**
     * This test verifies that when 2 items are in the cart when you remove it 1 , the cart has 1 item
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" description
     * 6.Click on add to cart button below "Sauce Labs Bike Light" description
     * 6. Click on cart icon, in the top right corner
     * 7. Assert one "Sauce Labs Backpack" and "Sauce Labs Bike Light" are present in the cart
     * 8. Click on remove button "Sauce Labs Backpack"
     * 9. Assert the cart have 1 item, "Sauce Labs Bike Light"
     */

    @Test()
    public void testOneItemIsRemoved() throws InterruptedException {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart1Button();
        inventorySauceDemo.clickOnAddToCart2Button();
        inventorySauceDemo.clickOnCart();
        String productTitle = cartSauceDemo.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

        cartSauceDemo.clickOnRemoveButton1();
        Thread.sleep(4000);
        Assert.assertTrue(cartSauceDemo.checkCartContentAfterRemoving1Item());

    }

    /**
     * This test verifies that when there are 2 items in the cart, if you remove 1, the correct one is left
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on add to cart button below "Sauce Labs Bike Light" item
     * 7. Click on cart icon, in the top right corner
     * 8. Click on remove button next to "Sauce Labs Backpack" item
     * 9. Assert that only "Sauce Labs Bike Light" item is displayed in the cart
     */

    @Test()
    public void testRemoveOneOutOfTwoItems() throws InterruptedException {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart1Button();
        inventorySauceDemo.clickOnAddToCart2Button();
        inventorySauceDemo.clickOnCart();
        Assert.assertTrue(cartSauceDemo.checkCartContentFor2Items());
        cartSauceDemo.clickOnRemoveButton1();
        Thread.sleep(4000);
        Assert.assertTrue(cartSauceDemo.checkCartContentAfterRemoving1Item());
    }
}
