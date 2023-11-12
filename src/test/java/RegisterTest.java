package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
public class RegisterTest {
    WebDriver driver;
    final String URL = "http://training.skillo-bg.com:4200";
    final String HOME_URL = URL + "/posts/all";
    final String REGISTER_URL = URL + "/users/register";
    WebDriverWait wait;

    @BeforeMethod
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Parameters({"username", "email", "password"})
    @Test
    public void registerTest(String username, String email, String password) {
        System.out.println("1. Go to homepage");
        driver.get(HOME_URL);

        System.out.println("2. Click Login link");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        clickElement(loginLink);

        System.out.println("3. Verify 'Sign in' title is visible");
        WebElement singInHeader = driver.findElement(By.cssSelector("p.h4"));
        wait.until(ExpectedConditions.visibilityOf(singInHeader));
        wait.until(ExpectedConditions.textToBePresentInElement(singInHeader, "Sign in"));

        System.out.println("4. Click Register link");
        WebElement registerLink = driver.findElement(By.linkText("Register"));
        clickElement(registerLink);

        System.out.println("5. Verify that URL has changed to /register");
        wait.until(ExpectedConditions.urlToBe(REGISTER_URL));

        System.out.println("6. Verify that sign up form is visible");
        WebElement signUpHeader = driver.findElement(By.xpath("//h4[contains(text(), 'Sign up')]"));
        // Could use both Assert and Expected conditions to check for the visibility of the element
        // Assert.assertTrue(signUpHeader.isDisplayed(), "Sign up header is not visible!");
        wait.until(ExpectedConditions.visibilityOf(signUpHeader));

        System.out.println("7. Populate valid username and verify a valid sign is displayed");
        WebElement usernameField = driver.findElement(By.name("username"));
        typeInputField(usernameField, username);

        System.out.println("8. Populate a valid email and verify a valid sign is displayed");
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        typeInputField(emailField, email);

        System.out.println("9. Populate password and verify a valid sign is displayed");
        WebElement passwordField = driver.findElement(By.id("defaultRegisterFormPassword"));
        typeInputField(passwordField, password);

        System.out.println("10. Populate confirm password and verify a valid sign is displayed");
        WebElement confirmPasswordField = driver.findElement(By.id("defaultRegisterPhonePassword"));
        typeInputField(confirmPasswordField, password);

        System.out.println("11. Click Sign-in");
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        clickElement(signInButton);

        System.out.println("12. Check that the toast message is correct");
        WebElement toastMsg = driver.findElement(By.className("toast-message"));
        String toastMsgText = toastMsg.getText();
        Assert.assertEquals(toastMsgText, "Successful register!",
                "Toast message incorrect. Actual message " + toastMsgText);

        System.out.println("13. Verify the URL has changed to homepage");
        wait.until(ExpectedConditions.urlToBe(HOME_URL));

        System.out.println("14. Check that Profile tab is visible");
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.visibilityOf(profileLink));
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void typeInputField(WebElement input, String text) {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(text);
        String classAttribute = input.getAttribute("class");
        Assert.assertTrue(classAttribute.contains("is-valid"), "The input is not valid!");
    }

}