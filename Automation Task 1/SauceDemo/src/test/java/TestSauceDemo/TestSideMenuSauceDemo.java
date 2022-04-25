package TestSauceDemo;
import PagesSauceDemo.LogInSauceDemo;
import PagesSauceDemo.SideMenuSauseDemo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;
public class TestSideMenuSauceDemo {

    private WebDriver driver;
    private LogInSauceDemo logInSauceDemo;

    private SideMenuSauseDemo sideMenuSauseDemo;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        sideMenuSauseDemo = new SideMenuSauseDemo(driver);
        logInSauceDemo = new LogInSauceDemo(driver);
    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    }

    /**
     * This test verifies all 4 options are listed when clicked on side menu
     * Test data: uName: standard_user, pWord: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Assert all 4 options are listed in the side menu
     */

    @Test
    public void testSideMenuItemsAreAllListed() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        sideMenuSauseDemo.clickOnSideMenuButton();
        Assert.assertTrue(sideMenuSauseDemo.checkSideMenuItemsAllAreListed());
    }

    /**
     * This test verifies that when clicked on logout button from inside the side menu,
     * you are successfully logged out
     * Test data: uName: standard_user, pWord: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on logout button from the side menu
     * 7. Assert you are logged out - login button is now displayed
     */

    @Test
    public void testWhenClickedOnLogoutInsideSideMenuYouAreLoggedOut() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        sideMenuSauseDemo.clickOnSideMenuButton();
        sideMenuSauseDemo.clickOnLogoutButtonSideMenu();
        Assert.assertTrue(logInSauceDemo.loginButtonIsDisplayed());
    }

    /**
     * This test verifies when clicked on about button, you are navigated to https://saucelabs.com/
     * Test data: uName: standard_user, pWord: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on about button from the side menu
     * 7. Assert you are navigated to https://saucelabs.com/
     */

    @Test
    public void testWhereNavigatedWhenClickedOnAboutButton() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        sideMenuSauseDemo.clickOnSideMenuButton();
        sideMenuSauseDemo.clickOnAboutButtonSideMenu();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    /**
     * This test verifies after opening the side menu, if you click on x (close) button, side menu is collapsed
     * Test data: uName: standard_user, pWord: secret_sauce
     * Test steps:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Click on side menu button in the left upper corner
     * 6. Click on x (close) button in the top right corner of the side menu
     * 7. Assert that side menu is collapsed
     */

    @Test
    public void testIfSideMenuIsCollapsedWhenClickedOnX() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.logIn();
        sideMenuSauseDemo.clickOnSideMenuButton();
        sideMenuSauseDemo.clickXButtonSideMenu();
        Assert.assertTrue(sideMenuSauseDemo.sideMenuButtonIsClickable());
    }
}

