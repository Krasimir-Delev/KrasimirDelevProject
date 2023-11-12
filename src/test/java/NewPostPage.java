package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class NewPostPage extends BasePage {

    @FindBy(css = "input[type='file']")
    WebElement fileInput;

    @FindBy(css = ".image-preview")
    WebElement imagePreview;

    @FindBy(css = ".input-group input")
    WebElement fileNameInput;

    @FindBy(name = "caption")
    WebElement captionInput;

    @FindBy(id = "create-post")
    WebElement submitBtn;

    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadFile(File file) {
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public void verifyImagePreview() {
        wait.until(ExpectedConditions.visibilityOf(imagePreview));
    }

    public String getFileName() {
        return fileNameInput.getAttribute("placeholder");
    }

    public void populateCaption(String captionText) {
        wait.until(ExpectedConditions.visibilityOf(captionInput));
        captionInput.sendKeys(captionText);
    }

    public void submitPost() {
        clickElement(submitBtn);
    }
}