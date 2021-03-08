package org.training.selenium.cucumber.steps;

import org.openqa.selenium.WebDriver;
import org.training.selenium.cucumber.pages.GoogleSearchPage;
import org.training.selenium.cucumber.selenium.DriverUtils;

public class SeleniumSteps {
    protected WebDriver driver = DriverUtils.getDriver();
    protected GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);


}
