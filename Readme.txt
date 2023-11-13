1. Създаваме нов Maven проект през IntelliJ със съответните настройки:
Menu --> File --> New --> Project --> Language Java, Build Sustem Maven project --> 
Advanced Settings --> GroupId: org.krasimir.delev.project
--> ArtifactId: KrasimirDelevProject
* Create --> Създаваме си POM.XML файл 
Име на проекта: KrasimirDelevProject

2. Добавяме Dependencies в POM.XML файла

    <dependencies>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.12.1</version>
        </dependency>

    </dependencies>

3. Сваляме си Chrome Web Driver - в случая при мен са версия Chrome: 119.0.6045.124; Chrome Web Driver: Stable
Version: 119.0.6045.105 (r1204232)

4. Настройваме си Chrome Driver в нашия Maven project:

package lecture12.examples;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverWithDriverPath {
    public static void main(String[] args) {
        // To run this example download the chromedriver.exe for your browser version
        // and modify the path to it. This example works if the driver is put in automation/src/test/resources/drivers/ folder
        System.setProperty("webdriver.chrome.driver", "automation/src/test/java/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        driver.manage().window().maximize();
        driver.close();
    }
}

//////////////////////////////////////////////// 5.  Сценарии за тесване /////////////////


1*. Във файла "HomePageTestClass.java" дефинираме първи сценарий като тест за зареждане и извеждане на Home Page

2*. Втори тест сценарий построяваме в "RegisterTest.java", чрез който тестваме функционалността на формата за регистрация

3*. Трети тест сценарий построяваме в "LogInTest.java", чрез който тестваме функционалността на формата за Log In, като преди това използваме "LoginTest.java" файла,
 в който дефинираме Login test-a

4*. Четвърти тест сценарий построяваме с "BasePage.java" и "HeaderTest.java", където построяваме тест за проверка на Header-a

5*. Пети тест сценарий построяваме с "BasePage.java" и "NewPostPage.java", където построяваме тест за проверка на функционалността на създаването на Post 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


6. Създаваме "BaseTest.java" за да се запазва screenshot в избрана директория от проекта при "test failure".