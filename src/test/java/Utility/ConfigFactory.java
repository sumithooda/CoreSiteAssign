package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads property file and holds methods to get specific property
 *
 * @author Sumit
 * @version 1.0
 */
public class ConfigFactory {

    private Properties properties;
    private final String propertyFilePath = Constants.APP_PROPERTY_PATH;

    public ConfigFactory() {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("application.properties not found at " + propertyFilePath);
        }
    }

    /**
     * Gets browser name on which tests will be executed by framework
     *
     * @return
     * @throws Exception
     */
    public String getBrowser() throws Exception {
        String browser = properties.getProperty("Browser");
        if (browser != null) {
            return browser;
        } else {
            throw new RuntimeException("Browser name is not specified in the property file");
        }
    }

    /**
     * Gets URL of the application
     *
     * @return
     */
    public String getApplicationUrl() {
        String applicationUrl = properties.getProperty("ApplicationUrl");
        if (applicationUrl != null) {
            return applicationUrl;
        } else {
            throw new RuntimeException("Application Url is not specified in the property file");
        }
    }

    /**
     * Gets URL for the remote execution (i.e. selenium grid)
     *
     * @return
     */
    public String getRemoteUrl() {
        String applicationUrl = properties.getProperty("DockerGridURI");
        if (applicationUrl != null) {
            return applicationUrl;
        } else {
            throw new RuntimeException("DockerGridURI is not specified in the property file");
        }
    }

    /**
     * Gets browser driver version to download it on run time.
     *
     * @return
     * @throws Exception
     */
    public String getDriverVersion() throws Exception {
        String browser = properties.getProperty("Browser");
        String driverName;

        switch (browser.toLowerCase()) {
            case "chrome":
                driverName = "ChromeDriverVersion";
                break;
            case "firefox":
                driverName = "FirefoxDriverVersion";
                break;
            case "internetexplorer":
                driverName = "IeDriverVersion";
                break;
            case "edge":
                driverName = "EdgeDriverVersion";
                break;
            default:
                throw new Exception("Unsupported browser: " + browser);
        }
        String driverVersion = properties.getProperty(driverName);
        return driverVersion;
    }

    /**
     * Gets environment name on which tests are to be executed
     *
     * @return
     */
    public String getEnvironment() {
        String environmentName = properties.getProperty("Environment");

        if (environmentName != null) {
            return environmentName;
        } else {
            throw new RuntimeException("Execution Environment is not specified in the property file");
        }
    }

    /**
     * Gets value of implicit wait that to be applied for complete test execution
     *
     * @return
     */
    public String getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("ImplicitWaitInMS");

        if (implicitlyWait != null) {
            return implicitlyWait;
        } else {
            throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
        }
    }

}
