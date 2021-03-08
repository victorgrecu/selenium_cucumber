package org.training.selenium.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.training.selenium.cucumber.translate.LanguageUtils;

import java.util.List;
import java.util.Map;

public class GoogleTranslateSteps extends SeleniumSteps{
    final static Logger LOGGER = LogManager.getLogger(GoogleTranslateSteps.class);

    @Given("I am on google translate")
    public void iAmOnGoogleTranslate() {
        driver.get("https://translate.google.com/");
        if (!driver.findElements(By.tagName("iframe")).isEmpty()) {
            driver.switchTo().frame(0);
            driver.findElement(By.xpath("//*[@id=\"introAgreeButton\"]")).click();
        }

    }

    @When("I translate from {string} to {string}")
    public void iTranslateFromTo(String lang1, String lang2) {
        driver.get("https://translate.google.com/?sl=" + LanguageUtils.getShortName(lang1) + "&tl=" +
                LanguageUtils.getShortName(lang2) + "&op=translate");
    }

    @Then("I get for:")
    public void iGetFor(List<Map<String, String>> translation) {
        LOGGER.info(translation);
        Map<String, String> row = translation.get(0);
        WebElement source = driver.findElement(By.
                cssSelector("div.AxqVh > div.OPPzxe > c-wiz.rm1UF.UnxENd > span > span > div > textarea"));
        source.clear();
        source.sendKeys(row.get("expression"));
        source.click();
        WebElement translated = driver.findElement(By.cssSelector("c-wiz.P6w8m.BDJ8fb > div.dePhmb > div > div.J0lOec"));
        LOGGER.info(translated.getText());
        Assert.assertEquals(row.get("translation").toLowerCase(), translated.getText().split("\n")[0].trim().toLowerCase());
    }

}


