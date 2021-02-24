package org.training.selenium.cucumber.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.training.selenium.cucumber.selenium.DriverUtils;

@RunWith(Cucumber.class)
    @CucumberOptions(plugin = { "html:target/cucumber/results.html", "json:results.json" },
    features = "classpath:features",
    glue = "org.training.selenium.cucumber.steps"
            ,tags = "@exercise"
    )
    public class RunCucumberTest {
    final static Logger LOGGER = LogManager.getLogger(RunCucumberTest.class);


    @BeforeClass
    public static void pseudoBeforeSuite(){
        LOGGER.info("Cucumber doesn't have a before suite hook. Every scenario should be independent " +
                "and not rely on hooks." +
                "This is a way to simulate a before suit hook. It should be used wisely");
    }
    @AfterClass
    public static void closeDriver(){
        DriverUtils.closeDriver();
    }
    }


