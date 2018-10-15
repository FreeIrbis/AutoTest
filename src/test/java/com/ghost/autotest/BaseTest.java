package com.ghost.autotest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    BrowserEnum browserEnum;
    @Parameters("browser")
    @BeforeTest
    protected WebDriver getDriver(String browser) {
        if(browser.equals("chrome")) {
            System.setProperty(browserEnum.GOOGLE.nameDriver,browserEnum.GOOGLE.urlDriver);
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox")) {
            System.setProperty(browserEnum.FIREFOX.nameDriver,browserEnum.FIREFOX.urlDriver);
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @AfterTest
    protected void tearDown() {
        if(driver != null)
            driver.quit();
    }
}
