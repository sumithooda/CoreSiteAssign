package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Holds fuctions which are dealing with WebDriver object
 *
 * @author Sumit
 * @version 1.0
 */
public class WebdriverFactory {

    private WebDriver driver;
    ConfigFactory obj = new ConfigFactory();
    private String environment;
    private String browser;
    private String driverVersion;

    public WebdriverFactory() throws Exception {
        this.browser = obj.getBrowser();
        this.environment = obj.getEnvironment();
        this.driverVersion = obj.getDriverVersion();
    }

    /**
     * if driver instance is not present then calls createDriver method
     *
     * @return WebDriver object
     * @throws Exception
     */
    public WebDriver getDriver() throws Exception {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    /**
     * invokes creating driver method according to the specified environment value in property file
     *
     * @return WebDriver object
     * @throws Exception
     */
    private WebDriver createDriver() throws Exception {
        switch (environment) {
            case "local":
                driver = createLocalDriver();
                break;
            case "remote":
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    /**
     * creates driver instance for the remotely available browser
     *
     * @return
     * @throws Exception
     */
    private RemoteWebDriver createRemoteDriver() throws Exception {
        String dockerURI = obj.getRemoteUrl();

        RemoteWebDriver driver;
        DesiredCapabilities capability;

        switch (browser.toLowerCase()) {
            case "firefox":
                capability = DesiredCapabilities.firefox();
                break;
            case "chrome":
                capability = DesiredCapabilities.chrome();
                break;
            case "internetexplorer":
                capability = DesiredCapabilities.internetExplorer();
                break;
            case "edge":
                capability = DesiredCapabilities.edge();
                break;
            default:
                throw new Exception("Unsupported browser: " + browser);
        }

        driver = new RemoteWebDriver(new URL(dockerURI), capability);

        return driver;
    }

    /**
     * creates driver instance according to browser name and also driver version given into property file.if the driver version is given as "latest" then will download latest driver
     *
     * @return
     * @throws Exception
     */
    private WebDriver createLocalDriver() throws Exception {

        switch (browser.toLowerCase()) {
            case "firefox":
                if (!driverVersion.equalsIgnoreCase("latest")) {
                    WebDriverManager.firefoxdriver().version(driverVersion).setup();
                } else {
                    WebDriverManager.firefoxdriver().setup();
                }
                driver = new FirefoxDriver();
                return driver;
            case "chrome":
                if (!driverVersion.equalsIgnoreCase("latest")) {
                    WebDriverManager.chromedriver().version(driverVersion).setup();
                } else {
                    WebDriverManager.chromedriver().setup();
                }
                driver = new ChromeDriver();
                return driver;
            case "internetexplorer":
                if (!driverVersion.equalsIgnoreCase("latest")) {
                    WebDriverManager.iedriver().version(driverVersion).setup();
                } else {
                    WebDriverManager.iedriver().setup();
                }
                driver = new InternetExplorerDriver();
                return driver;
            case "edge":
                if (!driverVersion.equalsIgnoreCase("latest")) {
                    WebDriverManager.edgedriver().version(driverVersion).setup();
                } else {
                    WebDriverManager.edgedriver().setup();
                }
                driver = new EdgeDriver();
                return driver;
            default:
                throw new Exception("Unsupported browser: " + browser);
        }
    }

    /**
     * this function will close the current instance browser driver
     *
     * @param driver
     */
    public void closeDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}
