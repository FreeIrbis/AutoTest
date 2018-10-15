package com.ghost.autotest;

import com.ghost.autotest.BaseTest;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivetedAccountTest extends BaseTest {

    @Test
    public void testRegistration() {
        driver.get("http://localhost:8080/registration");
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Ghost");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Walking");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("ghost.walkink.developer@gmail.com");
        WebElement confirmEmail = driver.findElement(By.id("confirmEmail"));
        confirmEmail.sendKeys("ghost.walkink.developer@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Skyrim312");
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        confirmPassword.sendKeys("Skyrim312");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.click();
        WebElement recaptcha = driver.findElement(By.className("recaptcha-checkbox-borderAnimation"));
        recaptcha.click();
        WebElement btnSuccess = driver.findElement(By.className("btn btn-success"));
        btnSuccess.click();
    }

}
