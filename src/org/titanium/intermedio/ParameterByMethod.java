package org.titanium.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ParameterByMethod {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
        driver.get("https://google.com");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "SearchProvider")
    public void testMethodA(String tester, String search) throws InterruptedException{
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        System.out.println("Welcome -> " + tester + " your search key is -> " + search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> " + testValue + " and is equal to -> " + search);
        searchText.clear();

        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    @Test(dataProvider = "SearchProvider")
    public void testMethodB(String search) throws InterruptedException{
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> " + testValue + " and is equal to -> " + search);
        searchText.clear();

        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataProvider(Method m){
        if(m.getName().equals("testMethodA")){
            return new Object[][]{
                    {"Manuel", "Google"},
                    {"Victor", "Yahoo"},
                    {"Luisa", "Facebook"},
                    {"Laura", "Amazon"}
            };
        }else{
            return new Object[][]{
                    {"Mexico"},
                    {"China"},
                    {"Rusia"}
            };
        }
    }
}
