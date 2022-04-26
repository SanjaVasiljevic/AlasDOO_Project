package PagesSauceDemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInformationSauceDemo {
    WebDriver driver;
    @FindBy(id = "first-name")
    WebElement firstNameInput;
    public void enterFirstName(String firstname) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstname);
    }
    @FindBy(id = "last-name")
    WebElement lastNameInput;
    public void enterLastName(String lastname) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastname);
    }
    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    public void enterPostalCode(String postalCode) {
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
    }
    @FindBy(id = "continue")
    WebElement continueButton;
    public void clickOnContinueButton() {
        continueButton.click();
    }
    @FindBy(className = "error-message-container error")
    WebElement alertMessageZipCode;
    public boolean isAlertMessageDisplayed() {
        return alertMessageZipCode.isDisplayed();
    }

    public CheckOutInformationSauceDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
