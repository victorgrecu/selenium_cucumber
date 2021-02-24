package org.training.selenium.examples;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* This is a basic example showing the main functionalities of Selenium WebDriver using java language.
* Selenium is a library that allows browser manipulation.
 * There are several browsers supported: chrome, firefox, edge.
 * All of them implement a common interface called WebDriver
 * There are also special drivers that allows special functionalities, like TakeScreenshot - for screenshots
 * In this example you may Selenium functionality in a nutshell:
 * open a browser - chrome in this example
 * taking actions on the browser - like maximising the window
 * switching to an iFrame
 * identify element or elements from the page
 * take actions elements like click(), submit() or sendKeys()
 * get text for an web element
 * take a screenshot
 * using to types of waits - implicit and explicit
 * more details about Selenium at https://www.selenium.dev/
 */
public class FirstExample {
    public static void main(String[] args) {
        //set the path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //now we have a chrome browser open
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //an implicit wait of 5 seconds have been set
        driver.manage().window().maximize();
        //go to google.com
        driver.get("https://www.google.com/");
        //sorting out the cookie agreement
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]")).click();

        //identify the search text box
        WebElement p=driver.findElement(By.name("q"));
        //enter text with sendKeys() then apply submit()
        p.sendKeys("Selenium Java");
        // Explicit wait condition for search results
        WebDriverWait w = new WebDriverWait(driver, 5);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        //retrieving results
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"rso\"]/div/div/div/div[1]/a/h3/span"));
        //printing in console the results
        for(WebElement result:results){
            System.out.println(result.getText());
        }

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to take the screenshot
        byte[] screenshot=scrShot.getScreenshotAs(OutputType.BYTES);
        //Save the screenshots as a file
        try {
            Files.createDirectories(Paths.get("target","screenshots"));
            Files.write(Paths.get("target","screenshots","evidence.png"), screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
