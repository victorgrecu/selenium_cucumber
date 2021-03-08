package org.training.selenium.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.selenium.cucumber.selenium.DriverUtils;

import java.util.List;


public class GoogleSearchSteps extends SeleniumSteps {
    final static Logger LOGGER = LogManager.getLogger(GoogleSearchSteps.class);


    @Given("I am on google search page")
    public void iAmOnGoogleSearchPage() {
        googleSearchPage.loadGoogleSearchPage();
    }

    @When("I search after {string}")
    public void iSearchAfter(String searchText) {
        LOGGER.info("Searching after {}",searchText);
       googleSearchPage.search(searchText);

    }

    @Then("I get results")
    public void iGetResults() {

        List<String> results = googleSearchPage.getSearchResults();
        StringBuilder sb = new StringBuilder();
        results.forEach(result-> sb.append(result).append("\n"));
        LOGGER.info("Results returned are \n {}",sb.toString());
    }

    @After("@selenium")
    public void getScreenshot(Scenario scenario){
        String path = DriverUtils.extracted(driver,scenario.getName()+"_"+scenario.getLine()+"_"+"evidence.png");
        scenario.log("<a href ='"+path+"'>Evidence</>");
    }

}
