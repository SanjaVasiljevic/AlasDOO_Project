package TestSauceDemo;
import PagesSauceDemo.LogInSauceDemo;;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

public class TestLoginSauceDemo {
    private WebDriver driver;
    private LogInSauceDemo logInSauceDemo;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logInSauceDemo = new LogInSauceDemo(driver);
    }

    @AfterClass
    public void tearDown() throws IOException {
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    }

    /**
     * This test verifies user is successfully logged in with valid data entered
     * Test data: uName: standard_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password filed
     * 4. Click on login
     * 5. Assert that user is logged in - https://www.saucedemo.com/inventory.html page shows
     */

    @Test()
    public void testSuccessfulLogin() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.inputUserName("standard_user");
        logInSauceDemo.inputPassword("secret_sauce");
        logInSauceDemo.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "You are successfully logged in");
    }

    /**
     * This test verifies an alert message is displayed when trying to login with locked out user
     * Test data: uName: locked_out_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password field
     * 4. Click on login button
     * 5. Assert message "Epic sadface: Sorry, this user has been locked out." is displayed
     */

    @Test
    public void testLoginWithLockedOutUser() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.inputUserName("locked_out_user");
        logInSauceDemo.inputPassword("secret_sauce");
        logInSauceDemo.clickOnLoginButton();
        Assert.assertTrue(logInSauceDemo.alertMessageIsDisplayed());
    }

    /**
     * This test verifies user is successfully logged in with valid data entered.
     * Test data: uName: problem_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password field
     * 4. Click on login button
     * 5. Assert that pictures of products have not been displayed properly after login and that all are the same.
     */
    @Test
    public void testLoginWithProblemUser() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);
        logInSauceDemo.inputUserName("problem_user");
        logInSauceDemo.inputPassword("secret_sauce");
        logInSauceDemo.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "You are successfully logged in");
    }

    /**
     * This test verifies user is successfully logged in with valid data entered
     * Test data: uName: performance_glitch_user, pWord: secret_sauce
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password field
     * 4. Click on login button
     * 5. Assert that user is logged in - https://www.saucedemo.com/inventory.html page shows
     */
    @Test
    public void testLoginWithPerformanceGlitchUser() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);

        logInSauceDemo.inputUserName("performance_glitch_user");
        logInSauceDemo.inputPassword("secret_sauce");
        logInSauceDemo.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "You are successfully logged in");
    }

    /**
     * This test verifies correct message is displayed when tried logging in with incorrect data
     * Test data: uName: Sanja, pWord: Vasiljevic
     * Steps to reproduce:
     * 1. Navigate to https://www.saucedemo.com/
     * 2. Input uName in username field
     * 3. Input pWord in password field
     * 4. Click on login button
     * 5. Assert "Username and password do not match any user in this service" message is displayed
     */

    @Test()
    public void testLoginWithInvalidData() {
        String baseURL = "https://www.saucedemo.com/";
        driver.navigate().to(baseURL);
        Assert.assertEquals(driver.getCurrentUrl(), baseURL,
                "The current URL should be equal to " + baseURL);
        logInSauceDemo.inputUserName("Sanja");
        logInSauceDemo.inputPassword("Vasiljevic");
        logInSauceDemo.clickOnLoginButton();
        String poruka = logInSauceDemo.getAlertMessage();
        System.out.println(poruka);
        Assert.assertTrue(poruka.contains("do not match any user"));
    }
}

