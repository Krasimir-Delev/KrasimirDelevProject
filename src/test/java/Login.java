package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

/*
    Test name: Successful login with
    registered user

    1. Open homepage
    2. Click login button
    3. Verify that the URL is correct
    4. Verify that the login form has appeared
    5. Populate username field with 'auto_user'
    6. Populate password filed with 'auto_pass'
    7. Click Sing in button
    8. Verify that the URL is correct ( homepage )
    9. Verify that there is a Profile button visible
    10. Click the Profile button
    11. Verify that the URL contains /user
    12. Username title matches the text 'auto_user'
    13. Verify that the Sign out button is displayed
 */
public class LoginTest {

    ChromeDriver driver;
    final String BASE_URL = "http://training.skillo-bg.com:4200";
    final String HOME_URL = BASE_URL + "/posts/all";
    final String LOGIN_URL = BASE_URL + "/users/login";
    final String PROFILE_URL = BASE_URL + "/users/";

    @BeforeTest
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DataProvider(name = "getUserData")
    public Object[][] getUsers() {
        return new Object[][]{
                {"auto_user", "auto_pass"}
        };
    }

    @Test(dataProvider = "getUserData")
    public void loginTest(String username, String password) {
        System.out.println("1. Open homepage");
        driver.get(HOME_URL);

        System.out.println("2. Click login button");
        WebElement loginBtn = driver.findElement(By.id("nav-link-login"));
        loginBtn.click();

        System.out.println("3. Verify that the URL is correct");

        // This check will sometimes fail as the url takes some time to change!
        // Therefor it is better to use an explicit wait.
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl, "Invalid url.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));

        System.out.println("4. Verify that the login form has appeared");
        WebElement loginForm = driver.findElement(By.cssSelector(".login-container form"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not visible!");

        System.out.println("5. Populate username field with 'auto_user'");
        WebElement usernameField = driver.findElement(By.id("defaultLoginFormUsername"));
        usernameField.sendKeys(username);

        System.out.println("6. Populate password filed with 'auto_pass'");
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        System.out.println("7. Click Sing in button");
        WebElement signInBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        signInBtn.click();

        System.out.println("8. Verify that the URL is correct ( homepage )");
        wait.until(ExpectedConditions.urlToBe(HOME_URL));

        System.out.println("9. Verify that there is a Profile button visible");
        WebElement profileBtn = driver.findElement(By.linkText("Profile"));
        Boolean isProfileBtnDisplayed = profileBtn.isDisplayed();
        Assert.assertTrue(isProfileBtnDisplayed, "Profile button is not visible");

        System.out.println("10. Click the Profile button");
        profileBtn.click();

        System.out.println("11. Verify that the URL contains /user");
        wait.until(ExpectedConditions.urlContains(PROFILE_URL));

        System.out.println("12. Username title matches the text 'auto_user'");
        WebElement usernameHeader = driver.findElement(By.cssSelector(".profile-user-settings h2"));
        String actualUsernameTitle = usernameHeader.getText();
        Assert.assertEquals(actualUsernameTitle, username, "Username title is incorrect.");

        System.out.println("13. Verify that the Sign out button is displayed");
        WebElement signOutBtn = driver.findElement(By.cssSelector(".fa-sign-out-alt"));
        Assert.assertTrue(signOutBtn.isDisplayed(), "Sign out button is not visible");
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}