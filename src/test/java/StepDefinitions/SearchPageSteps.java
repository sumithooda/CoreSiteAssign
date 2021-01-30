package StepDefinitions;

import Utility.*;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchPageSteps extends BaseUtils {

    ConfigFactory obj = new ConfigFactory();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;


    public SearchPageSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }

    @When("user try  searching  {string} on web page")
    public void userTrySearching(String textToSearch) throws Exception {
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getSearchPageObj().searchbox, textToSearch);
        pageObjectFactory.getSearchPageObj().searchbox.submit();
    }


    @Then("user should be navigated to search result page with {string}")
    public void userShouldBeNavigatedToSearchResultPageWith(String expectedTitle) throws Exception {
        Assert.assertEquals(getCurrentPageTitle(webdriverFactory.getDriver()), expectedTitle, "User is unable to open add employee section");
        captureScreenShot(webdriverFactory.getDriver(), expectedTitle);
    }



}
