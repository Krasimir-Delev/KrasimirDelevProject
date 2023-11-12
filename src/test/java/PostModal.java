package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage {

    @FindBy(className = "post-user")
    WebElement user;

    @FindBy(className = "modal-content")
    WebElement modal;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForModalToAppear() {
        wait.until(ExpectedConditions.visibilityOf(modal));
    }

    public String getPostUser() {
        wait.until(ExpectedConditions.visibilityOf(user));
        return user.getText();
    }
}