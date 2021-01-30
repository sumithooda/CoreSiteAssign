package StepDefinitions;

import Utility.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class GenericSteps extends BaseUtils{

    private WebDriver driver;
    ConfigFactory obj=new ConfigFactory();
    BaseUtils base =  new BaseUtils();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;

    public GenericSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }



    @After
    public void tearDown(Scenario scenario) throws Exception {
        if(scenario.isFailed()){
            base.captureScreenShot(webdriverFactory.getDriver(),scenario);
        }
        if(webdriverFactory.getDriver()!=null) {
            webdriverFactory.closeDriver(webdriverFactory.getDriver());
        }

    }

@Given("Browser is opened and user is navigated to website with url {string} with page title {string}")
public void browserIsOpenedAndUserIsNavigatedToWebsiteWithUrlWithPageTitle(String appUrl, String title)  throws Exception {
        webdriverFactory.getDriver().get(appUrl);
        webdriverFactory.getDriver().manage().window().maximize();
        webdriverFactory.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(obj.getImplicitlyWait()), TimeUnit.MILLISECONDS);
        String currentPage=getCurrentPageTitle(webdriverFactory.getDriver());
        Assert.assertEquals(currentPage,title,"Search page is not opened properly");
    }

}
