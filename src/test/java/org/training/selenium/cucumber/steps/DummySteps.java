package org.training.selenium.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.selenium.examples.FirstExampleRefined;

public class DummySteps {
    final static Logger LOGGER = LogManager.getLogger(DummySteps.class);
    @Given("there is context")
    public void thereIsContext() {
        LOGGER.info("Simple cucumber step - There is a context");

    }

    @When("an event happens")
    public void anEventHappens() {
        LOGGER.info("Simple cucumber step - An event happens");

    }

    @Then("there is an outcome")
    public void thereIsAnOutcome() {
        LOGGER.info("Simple cucumber step - There is an outcome");

    }

    @Given("you are using a search engine")
    public void youAreUsingASearchEngine() {
        LOGGER.info("Simple cucumber step - You are using a search engine");

    }

    @When("you search after {string}")
    public void youSearchAfter(String searchText) {
        LOGGER.info("Cucumber step with parameter - You search after {}", searchText);
    }

    @Then("you get a lot of results")
    public void youGetALotOfResults() {
    }

    @Before
    public void someActionThatNeedsToBeExecutedBeforeEachScenario(){
        LOGGER.info("This before method is executed before each scenario");
    }

    @After
    public void someActionThatNeedsToBeExecutedAfterEachScenario(){
        LOGGER.info("This before method is executed after each scenario");
    }

    @Before
    public void beforeWithScenario(Scenario scenario){
        LOGGER.info("You may know the scenario on which you are in a hook. Now you executing  {}",scenario.getName());
    }

    @After
    public void afterWithScenario(Scenario scenario){
        LOGGER.info("You may know the scenario on which you are in a hook. You have executed {}" +
                " and it was {}",scenario.getName(),scenario.getStatus());
    }

    @Before("@parameters")
    public void taggedBefore(){
        LOGGER.info("This before will be executed just for the scenarios tagged with @parameters");
    }

    @Then("you get a lot of results for {string} , {string} and {string}")
    public void youGetALotOfResultsForAnd(String result1, String result2, String results3) {
        LOGGER.info("You may have scenarios with multiple parameters like here {},{} and {}",
                result1, result2,results3);
    }

    @And("at least {int} results for {string}")
    public void atLeastResultsFor(int expectedNumberOfResults, String  result) {
        LOGGER.info("Parameters can be of any type");
        LOGGER.info("Here is a step with an integer parameter {} and a string parameter {}",expectedNumberOfResults,result);
    }
}
