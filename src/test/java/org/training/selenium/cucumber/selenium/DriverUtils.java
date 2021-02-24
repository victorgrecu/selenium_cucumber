package org.training.selenium.cucumber.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DriverUtils {
    final static Logger LOGGER = LogManager.getLogger(DriverUtils.class);
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver ==null){
            driver = getChromeWebDriver();
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }

    }

    public static String extracted(WebDriver driver, String screenshotName) {

        TakesScreenshot scrShot = (TakesScreenshot)driver;
        byte[] screenshot=scrShot.getScreenshotAs(OutputType.BYTES);
        try {
            Files.createDirectories(Paths.get("target","screenshots"));
            Path p = Paths.get("target","screenshots",screenshotName);
            Files.write(Paths.get("target","screenshots",screenshotName), screenshot);
            LOGGER.debug("Evidence saved to {}",p.toString());
            return p.toString();
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
