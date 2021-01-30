package StepDefinitions;

import Utility.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ExpediaHomeSteps extends BaseUtils {

    ConfigFactory obj = new ConfigFactory();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;

    public ExpediaHomeSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }

    @And("{string} version of website is open")
    public void versionOfWebsiteIsOpen(String siteName) throws Exception {
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().regionSelect);
        if (!getCurrentText(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().siteSelector).equalsIgnoreCase(siteName)) {
            selectFromDropdown(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().siteSelector, siteName);
        }
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().saveregion);
    }

    @When("user choose destination city as {string} with code {string}")
    public void userChooseDestinationCityAsWithCode(String destinationCity, String cityCode) throws Exception {
        pageObjectFactory.getExpediaHomeObj().selectCity(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().goingTo,
                destinationCity, cityCode, pageObjectFactory.getExpediaHomeObj().destinationResult);
    }

    @And("user selects add flight option")
    public void userSelectsAddFlightOption() throws Exception {
        moveToElement(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().selectFlightCheckbox, pageObjectFactory.getExpediaHomeObj().selectFlightCheckbox);
    }

    @And("user choose origin city as {string} with code {string}")
    public void userChooseOriginCityAsWithCode(String origincity, String cityCode) throws Exception {
        pageObjectFactory.getExpediaHomeObj().selectCity(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().leavingFrom,
                origincity, cityCode, pageObjectFactory.getExpediaHomeObj().originResult);
    }


    @And("user selects Checkin date as {string}")
    public void userSelectsCheckinDateAs(String date) throws Exception {
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().checkInDate);
        pageObjectFactory.getExpediaHomeObj().selectTravelDate(date);
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().dateDoneBtn);
    }

    @And("user selects traveler as {string} adult and {string} child of age {string}")
    public void userSelectsTravelerAsAdultAndChildOfAge(String adultCount, String childCount, String childAge) throws Exception {
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().travelersSelect);
        pageObjectFactory.getExpediaHomeObj().clickmultipleTimes(webdriverFactory.getDriver(),
                pageObjectFactory.getExpediaHomeObj().currentAdultValue.getAttribute("value"),
                adultCount, pageObjectFactory.getExpediaHomeObj().decAdult,
                pageObjectFactory.getExpediaHomeObj().incAdult); //clicks multiple times for making appropriate number number of travelers

        pageObjectFactory.getExpediaHomeObj().clickmultipleTimes(webdriverFactory.getDriver(),
                pageObjectFactory.getExpediaHomeObj().childrenValue.getAttribute("value"),
                childCount, pageObjectFactory.getExpediaHomeObj().decChildren,
                pageObjectFactory.getExpediaHomeObj().incChildren);//clicks multiple times for making appropriate number number of childrens

        selectFromDropdown(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().childAge, childAge);

        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().guestDone);

    }

    @And("user clicks on search button")
    public void userClicksOnSearchButton() throws Exception {
        Click(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().searchBtn);
    }

    @Then("result page contains options with {string} as destination")
    public void resultPageContainsOptionsWithAsDestination(String arg0) throws Exception {
        impWait(webdriverFactory.getDriver(), pageObjectFactory.getExpediaHomeObj().searchBtn,30);
        captureScreenShot(webdriverFactory.getDriver(),"PropertyResult ");
        if(pageObjectFactory.getExpediaHomeObj().searchResultProperty.size()==0){
            System.out.print("No property with given details");
        }
    }

}