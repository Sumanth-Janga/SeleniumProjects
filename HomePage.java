package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

public class HomePage {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Amazon"));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String title = driver.getTitle();
        System.out.println("TITLE : "+title);
        String url = driver.getCurrentUrl();
        System.out.println("URL : "+url);
        if(url.toLowerCase().contains("amazon.in")&&title.contains("Amazon")){
            System.out.println("Home Page is Successfull");
        }else{
            System.out.println("Error in Home page");
        }
        driver.quit();
    }
}