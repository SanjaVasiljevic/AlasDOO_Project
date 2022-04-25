package PagesSauceDemo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInSauceDemo {
    WebDriver driver;
    JavascriptExecutor js;
    @FindBy(id = "user-name")
    WebElement userName;
    public void inputUserName(String uName) {
        userName.sendKeys(uName);
    }
    @FindBy(id = "password")
    WebElement password;
    public void inputPassword(String pWord) {
        password.sendKeys(pWord);
    }
    @FindBy(id = "login-button")
    WebElement loginButton;
    public void clickOnLoginButton() {
        js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }
    public void logIn() {
        inputUserName("standard_user");
        inputPassword("secret_sauce");
        clickOnLoginButton();
    }
    public boolean loginButtonIsDisplayed() {
        return loginButton.isDisplayed();
    }
    @FindBy(xpath = " //*[@id=\"login_button_container\"]/div/form/div[3]")
    WebElement alertMessage;
    public String getAlertMessage() {
        return alertMessage.getText();
    }
    public boolean alertMessageIsDisplayed() {
        return alertMessage.isDisplayed();
    }
    public LogInSauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
