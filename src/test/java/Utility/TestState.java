package Utility;

/**
 * Exposes pages and driver factory with the single instance.Also this can be used to stored the temporary data while test execution to be used in later steps
 *
 * @author Sumit
 * @version 1.0
 */
public class TestState {

    private WebdriverFactory webdriverFactory;
    private PageObjectFactory pageObjectFactory;
    public String newEmployee;
    public String editEmployee;
    public String deleteEmployee;
    public String updateMail;


    public TestState() throws Exception {
        webdriverFactory = new WebdriverFactory();
        pageObjectFactory = new PageObjectFactory(webdriverFactory.getDriver());
    }

    /**
     * Gets instance of WebdriverFactory class
     *
     * @return
     */
    public WebdriverFactory getWebdriverFactory() {
        return webdriverFactory;
    }

    /**
     * Gets instance of PageObjectFactory class
     *
     * @return
     */
    public PageObjectFactory getPageObjectFactory() {
        return pageObjectFactory;
    }
}
