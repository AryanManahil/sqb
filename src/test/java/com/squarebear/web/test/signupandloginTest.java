package com.squarebear.web.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Test

public class signupandloginTest {
    public String baseUrl = "http://3.26.192.52:8080/login";
    String driverPath = "C:\\geckodriver-v0.31.0-win64\\geckodriver.exe";
    public WebDriver driver ;


    @Test
    public void verifySignupShouldSucceed() {
        System.out.println("launching firefox browser");
        System.setProperty("webdriver.gecko.driver", driverPath);

        driver = new FirefoxDriver();

        driver.get(baseUrl);


        WebElement signup =driver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div[2]/div/p[2]/a"));
        signup.click();

        String currentUrl = "http://3.26.192.52:8080/signup";

        WebElement name=driver.findElement(By.name("name"));
        name.sendKeys("chanchal@sqbear.com.au");

        WebElement username=driver.findElement(By.name("username"));
        username.sendKeys("chanchal@sqbear.com.au");

        WebElement password=driver.findElement(By.name("password"));
        password.sendKeys("123");

        WebElement cpassword=driver.findElement(By.name("cpassword"));
        cpassword.sendKeys("123");

        WebElement signupf = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div[2]/div/form/div[5]/button/span[1]"));
        signupf.click();

//        WebElement message = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div[2]/div/div/div/p"));
//
//        Boolean checkButtonPresence = message.isDisplayed();
//        assertThat(checkButtonPresence).isTrue();



        String finalUrl = "http://3.26.192.52:8080/signup";

        String expectedTitle = "Square Bear v1.0-0.8";
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);

        driver.close();

        System.out.println("Test Passed!");
    }

    @Test
    public void verifyLoginShouldSucceed() {
        System.out.println("launching firefox browser");
        //System.setProperty("webdriver.gecko.driver", driverPath);
        //driver = new FirefoxDriver();
        driver.get(baseUrl);
        // identify element
        //String loginUrl = "http://3.26.192.52:8080/project";
        String loginUrls = driver.getCurrentUrl();

        WebElement p=driver.findElement(By.name("username"));
        //enter text with sendKeys() then apply submit()
        p.sendKeys("chanchal@sqbear.com.au");
        WebElement p1=driver.findElement(By.name("password"));
        p1.sendKeys("123");
        WebElement s =driver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div[2]/div/form/div[4]/button"));
        s.click();

        String appTitle=driver.getTitle();
        String expTitle="Square Bear v1.0-0.8";

        assertThat(appTitle).isEqualTo(expTitle);
        System.out.println("Verification Successfull");

        String expectedUrl = "http://3.26.192.52:8080/project";
        assertThat(loginUrls).isNotEqualTo(expectedUrl);

//        WebElement newProject = driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[10]/span[1]"));
//        Boolean checkButtonPresence = newProject.isDisplayed();
//        assertThat(checkButtonPresence).isTrue();
        driver.close();
    }
}
