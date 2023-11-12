package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.chrome.ChromeDriver;

public class HomePageTestClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","KrasimirDelevProject/src/test/java/resources/drivers/chromedriver.exe");
    ChromeDriver ChromeDriver = new ChromeDriver();
    driver.get("http://training.skillo-bg.com:4200/posts/all");
    driver.manage().window().maximize();
    //driver.close();
    }



    @Test
    private static void testFindElement2() {
        driver.get("http://training.skillo-bg.com/posts/all");

        //Finds Login link which you can use to interact with on a later stage
        WebElement loginElement = driver.findElement(By.id("nav-link-login"));

        //Finds Home link which you can use to interact with on a later stage
        WebElement homeElement = driver.findElement(By.linkText("Home"));
    }

    @Test
    private static void testFindElements() {
        driver.get("http://training.skillo-bg.com/posts/all");

        //Finds all posts elements
        List<WebElement> posts = driver.findElements(By.xpath("//*[@class='post-feed-img']"));
        System.out.println("The number of post elements is: " + posts.size());
    }





    @Test
    private static void explicitWait() {
        driver.get("http://training.skillo-bg.com/posts/all");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement goToLoginBtn = driver.findElement(By.id("nav-link-login"));

        // Wait for posts to not become visible
        WebElement allPosts = driver.findElement(By.tagName("app-all-posts"));
        webDriverWait.until(ExpectedConditions.visibilityOf(allPosts));

        goToLoginBtn.click();

        // Wait for posts to disappear
        webDriverWait.until(ExpectedConditions.invisibilityOf(allPosts));
    }

    @AfterMethod
    private static void closeDriver() {
        System.out.println("==========Closing WebDriver session==========");
        driver.close();
    }
}
