package PagesSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class SideMenuSauseDemo {
    WebDriver driver;
    @FindBy(id = "react-burger-menu-btn")
    WebElement sideMenuButton;
    public void clickOnSideMenuButton() {
        sideMenuButton.click();
    }
    @FindBy(className = "bm-item-list")
    List<WebElement> sideMenuItems;
    public boolean checkSideMenuItemsAllAreListed() {
        return sideMenuItems.size() == 4;
    }
    public boolean sideMenuButtonIsClickable() {
        return sideMenuButton.isEnabled();
    }
    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButtonSideMenu;
    public void clickOnLogoutButtonSideMenu() {
        logoutButtonSideMenu.click();
    }
    @FindBy(id = "about_sidebar_link")
    WebElement aboutButtonSideMenu;
    public void clickOnAboutButtonSideMenu() {
        aboutButtonSideMenu.click();
    }
    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppState;
    public void clickResetAppStateSideMenu() {
        resetAppState.click();
    }
    @FindBy(id = "react-burger-cross-btn")
    WebElement xButtonSideMenu;
    public void clickXButtonSideMenu() {
        xButtonSideMenu.click();
    }
    public SideMenuSauseDemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
