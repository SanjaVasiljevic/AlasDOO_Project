package TestSauceDemo;
import PagesSauceDemo.CartSauceDemo;
import PagesSauceDemo.InventorySauceDemo;
import PagesSauceDemo.LogInSauceDemo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
public class TestSortingSauceDemo {
    private WebDriver driver;
    private LogInSauceDemo logInSauceDemo;
    private CartSauceDemo cartSauceDemo;
    private InventorySauceDemo inventorySauceDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        logInSauceDemo = new LogInSauceDemo(driver);
        cartSauceDemo = new CartSauceDemo(driver);
        inventorySauceDemo = new InventorySauceDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /**
     * This test verifies when you choose sorting option for price - high to low, items are displayed in descending order by price
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on sorting button
     * 6. Select "high to low" option from the sorting drop down menu
     * 7. Assert prices are sorted in descending order
     */

    @Test
    public void testHighToLowPriceOrder() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnSortingButton();
        inventorySauceDemo.selectSortingOptionHighToLow();
        Assert.assertTrue(inventorySauceDemo.checkSortingByPriceHighToLow());
    }

    /**
     * This test verifies when you choose sorting option for name - A to Z,
     * the first item displayed is not "Test.allTheThings() T-Shirt (Red)"
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on sorting button
     * 6. Select "A to Z" option from the sorting drop down menu
     * 7. Assert "Test.allTheThings() T-Shirt (Red)" is not first on the items' list
     */

    @Test
    public void testSortingByNameAtoZ() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        inventorySauceDemo.clickOnSortingButton();
        inventorySauceDemo.selectSortingOptionAtoZ();
        Assert.assertFalse(inventorySauceDemo.checkSortingByNameAtoZ());
    }
}
