    package com.selenium.pages;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.By;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import java.time.Duration;
    public class LoginPage {
        public static void main(String[] args){
            WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://www.amazon.com/gp/sign-in.html");
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email_login")));
                WebElement input = driver.findElement(By.id("ap_email_login"));
                String expectedValue = "test@example.com";
                input.sendKeys(expectedValue);
                String actualValue = input.getAttribute("value");
                System.out.println("EnterValue : " + actualValue);
                if (expectedValue.equals(actualValue)) {
                    System.out.println("PASS");
                } else {
                    System.out.println("FAIL");
                }
            driver.quit();
        }
    }
