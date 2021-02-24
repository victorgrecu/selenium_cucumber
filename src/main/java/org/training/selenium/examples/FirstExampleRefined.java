package org.training.selenium.examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;



/**
 * This is the same functionality as in FirstExample.java, but taking in consideration coding best practices
 * The main method was split in smaller methods with suggestive names
 * This will will make code more readable, easier to maintain and reusable
 * A quality code should be self-explanatory
 * Where there is a business logic use logs to explain logic and to help understanding of the application
 * Where possible parameterize the methods - like googleSearch(WebDriver driver, String searchText)
 * */
public class FirstExampleRefined {
    final static Logger LOGGER = LogManager.getLogger(FirstExampleRefined.class);
    public static void main(String[] args) {
        LOGGER.info("Simple search in google.com using Selenium");
        WebDriver driver = getChromeWebDriver();
        navigateToGoogleSearch(driver);
        googleSearch(driver,"Selenium Java");
        driver.quit();
    }

    private static void googleSearch(WebDriver driver, String searchText) {
        LOGGER.info("Searching after {}",searchText);
        WebElement p= driver.findElement(By.name("q"));
        p.sendKeys(searchText);
        WebDriverWait w = new WebDriverWait(driver, 5);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"rso\"]/div/div/div/div[1]/a/h3/span"));
        LOGGER.info("There were {} results on the first page",results.size());
        StringBuilder sb = new StringBuilder();
        results.forEach(result-> sb.append(result.getText()).append("\n"));
        LOGGER.info("Results returned are \n {}",sb.toString());
        extracted(driver,"evidence.png");
    }

    private static void navigateToGoogleSearch(WebDriver driver) {
        driver.get("https://www.google.com/");
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]")).click();
    }

    private static void extracted(WebDriver driver, String screenshotName) {

        TakesScreenshot scrShot = (TakesScreenshot)driver;
        byte[] screenshot=scrShot.getScreenshotAs(OutputType.BYTES);
        try {
            Files.createDirectories(Paths.get("target","screenshots"));
            Path p = Paths.get("target","screenshots",screenshotName);
            Files.write(Paths.get("target","screenshots",screenshotName), screenshot);
            LOGGER.debug("Evidence saved to {}",p.toString());
        } catch (IOException e) {
            throw new RuntimeException("Could not save screenshot", e);
        }
    }

    private static WebDriver getChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
