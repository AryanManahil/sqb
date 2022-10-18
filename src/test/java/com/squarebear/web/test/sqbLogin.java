package com.squarebear.web.test;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class sqbLogin extends FluentTestNg {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        driver.get("http://3.26.192.52:8080/login");

        //String BeforeUrl = driver.findElement(By.linkText("https://app.squarebearonline.com.au/login")).getText();

        // identify element
        WebElement p=driver.findElement(By.name("username"));
        //enter text with sendKeys() then apply submit()
        p.sendKeys("niaz@sqbear.com.au");
        WebElement p1=driver.findElement(By.name("password"));
        p1.sendKeys("Password1");
        WebElement s =driver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div[2]/div/form/div[4]/button"));
        s.click();

        String appTitle=driver.getTitle();
        String expTitle="Square Bear v1.0-0.8";

        assertThat(appTitle).isEqualTo(expTitle);
        System.out.println("Verification Successfull");
        driver.close();
        System.exit(0);


    }
}
