package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lecture16.examples.pages.HeaderPage;
import lecture16.examples.pages.HomePage;
import lecture16.examples.pages.LoginPage;
import lecture16.examples.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Parameters({"username", "password"})
    @Test
    public void loginTest(String username, String password) {
        System.out.println("1. Open homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2. Click login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("3. Verify that the URL is correct");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL(loginPage.LOGIN_URL);

        System.out.println("4. Verify that the login form has appeared");
        loginPage.verifyLoginFormVisible();

        System.out.println("5. Populate username field with 'auto_user'");
        loginPage.populateUsername(username);

        System.out.println("6. Populate password filed with 'auto_pass'");
        loginPage.populatePassword(password);

        System.out.println("7. Click Sing in button");
        loginPage.clickSignInBtn();

        System.out.println("8. Verify that the URL is correct");
        homePage.verifyURL(homePage.HOME_URL);

        System.out.println("9. Verify that there is a Profile button visible");
        Boolean isProfileLinkVisible = headerPage.isProfileLinkVisible();
        Assert.assertTrue(isProfileLinkVisible, "Profile link button is not visible!");
        // Another option:
        // headerPage.verifyProfileLinkVisibility();

        System.out.println("10. Click the Profile button");
        headerPage.goToProfile();

        System.out.println("12. Username title matches the text 'auto_user'");
        ProfilePage profilePage = new ProfilePage(driver);
        String actualUsernameTitle = profilePage.getUsernameTitle();
        Assert.assertEquals(actualUsernameTitle, username, "Username title is incorrect!");

        System.out.println("13. Verify that the Sign out button is displayed");
        Assert.assertTrue(headerPage.isSignOutBtnVisible(), "Sign out button is not visible!");
    }

    @AfterMethod
    public void cleanup() {
        driver.close();
    }
}