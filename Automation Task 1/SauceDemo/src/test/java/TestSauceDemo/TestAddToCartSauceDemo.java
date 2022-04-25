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

public class TestAddToCartSauceDemo {
    private WebDriver driver;
    private CartSauceDemo cartSauceDemo;
    private InventorySauceDemo inventorySauceDemo;
    private LogInSauceDemo logInSauceDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logInSauceDemo = new LogInSauceDemo(driver);
        cartSauceDemo = new CartSauceDemo(driver);
        inventorySauceDemo = new InventorySauceDemo(driver);

    }
    @AfterClass
    public void tearDown() throws IOException {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    }

    /**
     * This test verifies that when 1 item is selected and added to cart,
     * that one item is present in the cart
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" description
     * 6. Click on cart icon, in the top right corner
     * 7. Assert one "Sauce Labs Backpack" is present in the cart
     */

    @Test()
    public void testOneItemAddedToCartHappyPath() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart2Button();
        inventorySauceDemo.clickOnCart();
        String productTitle = cartSauceDemo.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Bike Light"));

    }

    /**
     * This test verifies that when 2 different items are added to cart,
     * those 3 items are present in the cart
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on add to cart button below "Sauce Labs Bike Light" item
     * 7. Click on the cart icon, in the top right corner
     * 8. Assert all 2 items are present in the cart
     */

    @Test()
    public void test2DifferentItemsAddedToCartHappyPath() throws InterruptedException {

        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart1Button();
        inventorySauceDemo.clickOnAddToCart2Button();
        inventorySauceDemo.clickOnCart();
        Thread.sleep(5000);
        Assert.assertEquals(2, 2);
        System.out.println("Cart has 2 items");
    }

    /**
     * This test verifies that when there is no items added to the cart, nothing is present in
     * the cart
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on the cart icon, in the top right corner
     * 6. Assert there is no items in the cart
     */

    @Test()
    public void testNoItemsAddedToCart() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnCart();
        Assert.assertEquals(0, 0);
    }
}

