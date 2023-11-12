package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage extends BasePage {

    @FindBy(id = "nav-link-home")
    WebElement homeLink;

    @FindBy(id = "nav-link-profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-login")
    WebElement loginLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostLink;

    @FindBy(css = ".fa-sign-out-alt")
    WebElement signOutBtn;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin() {
        clickElement(loginLink);
    }

    public void goToProfile() {
        clickElement(profileLink);
    }

    public void goToHome() {
        clickElement(homeLink);
    }

    public void goToNewPost() {
        clickElement(newPostLink);
    }

    public Boolean isProfileLinkVisible() {
        return profileLink.isDisplayed();
    }

    public void verifyProfileLinkVisibility() {
        wait.until(ExpectedConditions.visibilityOf(profileLink));
    }

    public Boolean isSignOutBtnVisible() {
        return signOutBtn.isDisplayed();
    }
}