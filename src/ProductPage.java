
package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ProductPage {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.get("https://www.amazon.in/");
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
            searchBox.sendKeys("one plus5g phone");
            searchBox.submit();
            wait.until(ExpectedConditions.urlContains("s?k="));
            By productLocator = By.xpath("//a[.//h2//span[contains(., 'N6x | 4GB+128GB')]]");
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
            Set<String> oldWindows = driver.getWindowHandles();
            productLink.click();

            try {
                wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
                for (String window : driver.getWindowHandles()) {
                    if (!oldWindows.contains(window)) {
                        driver.switchTo().window(window);
                        break;
                    }
                }
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("No new tab detected; checking the current tab.");
            }
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
            WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
            String title = productTitle.getText().trim();
            System.out.println("Product Title: " + title);
            if (!title.isEmpty()) {
                System.out.println("PASS: Product title found.");
            } else {
                System.out.println("FAIL: Product details missing.");
            }
            By cartLocator = By.id("add-to-cart-button");
            WebElement cart = wait.until(d -> {

                List<WebElement> buttons = d.findElements(cartLocator);

                for (WebElement button : buttons) {

                    if (button.isDisplayed() && button.isEnabled()) {
                        return button;
                    }
                }
                return null;
            });
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cart);
            wait.until(ExpectedConditions.elementToBeClickable(cart));
            cart.click();
            System.out.println("Add to Cart button clicked!");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sw-atc-confirmation")));

            System.out.println("PASS: Product successfully added to cart!");

        } finally {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
