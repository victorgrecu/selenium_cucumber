package org.training.selenium.cucumber.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage extends BasePage {
    final static Logger LOGGER = LogManager.getLogger(GoogleSearchPage.class);
    @FindBy(tagName = "iframe")
    private List<WebElement> iframes;
    private WebElement introAgreeButton;
    @FindBy(name = "q")
    private WebElement searchTextBox;
    @FindBy(xpath = "//*[@id=\"rso\"]/div/div/div/div[1]/a/h3/span")
    private List<WebElement> searchResults;


    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public void loadGoogleSearchPage() {
        driver.get("https://www.google.com/");
        if (!iframes.isEmpty()) {
            driver.switchTo().frame(0);
            introAgreeButton.click();
        }
    }

    public void search(String searchText) {
        LOGGER.info("Searching after {}", searchText);
        searchTextBox.sendKeys(searchText);
        WebDriverWait w = new WebDriverWait(driver, 5);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        searchTextBox.submit();
    }

    public List<String> getSearchResults() {
        LOGGER.info("There were {} results on the first page", searchResults.size());
        return searchResults.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
