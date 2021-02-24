package org.training.selenium.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.training.selenium.cucumber.selenium.DriverUtils;

import java.util.List;


public class GoogleSearchSteps {
    final static Logger LOGGER = LogManager.getLogger(DriverUtils.class);
    private WebDriver driver = DriverUtils.getDriver();

    @Given("I am on google search page")
    public void iAmOnGoogleSearchPage() {
        driver.get("https://www.google.com/");
        if(!driver.findElements(By.tagName("iframe")).isEmpty()){
            driver.switchTo().frame(0);
            driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]")).click();
        }

    }

    @When("I search after {string}")
    public void iSearchAfter(String searchText) {
        LOGGER.info("Searching after {}",searchText);
        WebElement p= driver.findElement(By.name("q"));
        p.sendKeys(searchText);
        WebDriverWait w = new WebDriverWait(driver, 5);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();

    }

    @Then("I get results")
    public void iGetResults() {

        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"rso\"]/div/div/div/div[1]/a/h3/span"));
        LOGGER.info("There were {} results on the first page",results.size());
        StringBuilder sb = new StringBuilder();
        results.forEach(result-> sb.append(result.getText()).append("\n"));
        LOGGER.info("Results returned are \n {}",sb.toString());
    }

    @After("@selenium")
    public void getScreenshot(Scenario scenario){
        String path = DriverUtils.extracted(driver,scenario.getName()+"_"+scenario.getLine()+"_"+"evidence.png");
        scenario.log("<a href ='"+path+"'>Evidence</>");
    }

}
