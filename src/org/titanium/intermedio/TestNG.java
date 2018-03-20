package org.titanium.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNG {
    String baseUrl = "http://newtours.demoaut.com/index.php";
    WebDriver driver;
    String expectedResult = "";
    String actualResult= "";
    String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";

    @BeforeTest
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void verifyHomePageTitle(){
        expectedResult = "Welcome: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult, "Title is not equals");
    }

    @AfterMethod
    public void goBackToHomePage() throws InterruptedException {
        driver.findElement(By.linkText("Home")).click();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public  void register(){
        driver.findElement(By.linkText("REGISTER")).click();
        expectedResult = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult, "Title is not equals");
    }

    @Test(priority = 0, enabled = false)
    public  void support(){
        driver.findElement(By.linkText("SUPPORT")).click();
        expectedResult = "Under Construction: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult, "Title is not equals");
    }
}
