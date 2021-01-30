package Utility;

import PageObjects.*;
import org.openqa.selenium.WebDriver;

/**
 * Holds instance of all the page classes and make sure only single object is present of particular page for the execution
 *
 * @author Sumit
 * @version 1.0
 */
public class PageObjectFactory {

    private WebDriver driver;
    private SearchPageObj searchPageObj;
    private ExpediaHomeObj expediaHomeObj;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets instance of SearchPage class and if not present then creates one
     * @return
     */
    public SearchPageObj getSearchPageObj() {
        return (searchPageObj == null) ? searchPageObj = new SearchPageObj(driver) : searchPageObj;
    }

    /**
     * Gets instance of SearchPage class and if not present then creates one
     * @return
     */
    public ExpediaHomeObj getExpediaHomeObj() {
        return (expediaHomeObj == null) ? expediaHomeObj = new ExpediaHomeObj(driver) : expediaHomeObj;
    }


}
