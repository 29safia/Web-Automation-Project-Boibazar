package com.batch.browserCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Browser_Command_Examples {

    String url ="https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
    WebDriver driver;
    @BeforeSuite
    public void startChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
@Test
    public void getCurrentUrl() throws InterruptedException {
        driver.get(url);
        Thread.sleep(5000);
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

        if(url.equals(currentURL)){
            System.out.println("Current URL is equal to the mentioned URL");
        }
        else{
            System.out.println("Current URL is not equal to the mentioned URL");
        }

    }

    @Test
    public void getPageSource() throws InterruptedException {
        System.out.println(driver.getPageSource());
    }
    
    @AfterSuite
    public void closeChromeBrowser() {
        driver.close();

    }
}
