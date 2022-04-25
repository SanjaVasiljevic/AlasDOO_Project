package TestSauceDemo;
import PagesSauceDemo.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

public class TestShoppingSauceDemo {
    WebDriver driver;
    private LogInSauceDemo logInSauceDemo;
    private InventorySauceDemo inventorySauceDemo;
    private CartSauceDemo cartSauceDemo;
    private CheckOutInformationSauceDemo checkOutInformationSauceDemo;
    private CheckOutCompleteSauceDemo checkOutCompleteSauceDemo;
    private CheckOutFinishSauceDemo checkOutFinishSauceDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logInSauceDemo = new LogInSauceDemo(driver);
        inventorySauceDemo=new InventorySauceDemo(driver);
        cartSauceDemo=new CartSauceDemo(driver);
        checkOutInformationSauceDemo=new CheckOutInformationSauceDemo(driver);
        checkOutCompleteSauceDemo=new CheckOutCompleteSauceDemo(driver);
        checkOutFinishSauceDemo=new CheckOutFinishSauceDemo(driver);
    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec(("taskkill /F /IM chromedriver.exe /T"));
    }
    /**
     * This test verifies that when all the input is correct, checkout is completed successfully
     * and the right message is displayed for placing order
     * Test data: UName: standard_user, pWord: secret_sauce, firstName: Sanja,
     * lastName: Vasiljevic, zipCode: 21000
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on the cart icon, in the top right corner
     * 7. Assert "Sauce Labs Backpack" item is displayed in the cart
     * 8. Click on checkout button
     * 9. Input test data in fistName, lastName, zipCode fields
     * 10. Click on continue button
     * 11. Assert current URL is https://www.saucedemo.com/checkout-step-two.html
     * 12. Click on finish button
     * 13. Assert you get checkout confirmation message "THANK YOU FOR YOUR ORDER"
     */

    @Test
    public void testShoppingOneItemHappyPath() throws InterruptedException {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);
        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart1Button();
        inventorySauceDemo.clickOnCart();

        String productTitle = cartSauceDemo.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

        cartSauceDemo.clickOnCheckoutButton();
        Thread.sleep(3000);
        checkOutInformationSauceDemo.enterFirstName("Sanja");
        checkOutInformationSauceDemo.enterLastName("Vasiljevic");
        checkOutInformationSauceDemo.enterPostalCode("21000");
        checkOutInformationSauceDemo.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html",
                "current URL should be equal to https://www.saucedemo.com/checkout-step-two.html");

        Assert.assertTrue(checkOutFinishSauceDemo.checkIfCorrectHeaderIsDisplayed());
        checkOutFinishSauceDemo.clickOnFinishButton();
        Assert.assertTrue(checkOutCompleteSauceDemo.checkCheckoutConfirmation());
    }

    /**
     * This test verifies correct alert message is displayed when tried to complete checkout
     * without entering zipCode
     * Test data: uName: standard_user, pWord: secret_sauce, firstName: Sanja,
     * lastName: Vasiljevic
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on add to cart button below "Sauce Labs Backpack" item
     * 6. Click on the cart icon, in the top right corner
     * 7. Assert "Sauce Labs Backpack" item is displayed in the cart
     * 8. Click on checkout button
     * 9. Input test data only in fistName, lastName fields
     * 10. Click on continue button
     * 11. Assert "Error: Postal Code is required" message is displayed
     */

    @Test
    public void testShoppingWithNoZipCodeEnteredAlertIsPresent() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnAddToCart1Button();
        inventorySauceDemo.clickOnCart();

        String productTitle = cartSauceDemo.getProductTitle();
        Assert.assertTrue(productTitle.contains("Sauce Labs Backpack"));

        cartSauceDemo.clickOnCheckoutButton();
        checkOutInformationSauceDemo.enterFirstName("Sanja");
        checkOutInformationSauceDemo.enterLastName("Vasiljevic");
        checkOutInformationSauceDemo.clickOnContinueButton();
        Assert.assertTrue(checkOutInformationSauceDemo.isAlertMessageDisplayed());
    }
}

