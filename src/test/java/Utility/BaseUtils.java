package Utility;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds generic action methods that can be used across framework
 * @author Sumit
 * @version 1.0
 */
public class BaseUtils {

    public void impWait(WebDriver driver, WebElement ele, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
    /**
     * This function add a explicit wait and also highlight the web-element before clicking it
     *
     * @param driver
     * @param ele
     */
    public void Click(WebDriver driver, WebElement ele) {
        impWait(driver,ele,20);
        highlightElement(driver, ele);
        ele.click();
    }

    /**
     * This function force click on the web-element using javascriptExecutor
     *
     * @param driver
     * @param ele
     */
    public void jsClick(WebDriver driver, WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(ele));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    /**
     * This function selects the web-element from dropdown by given visible text
     *
     * @param driver
     * @param ele
     * @param visibleText
     */
    public void selectFromDropdown(WebDriver driver, WebElement ele, String visibleText) {
        Select select = new Select(ele);
        select.selectByVisibleText(visibleText);
    }

    /**
     * This function return current selected text  from dropdown
     *
     * @param driver
     * @param ele

     */
    public String  getCurrentText(WebDriver driver, WebElement ele) {
        Select select = new Select(ele);
        return select.getFirstSelectedOption().getText();
    }



    /**
     * This function capture screenshot during test execution and store it on pre-defined destination
     *
     * @param driver
     * @param scenario
     * @return it returns destination path of the screenshot file
     * @throws Exception
     */
    public String captureScreenShot(WebDriver driver, Scenario scenario) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = Constants.SCREENSHOTS_PATH + scenario.getName().replaceAll(" ", "") + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(src, new File(destination));
        return destination;
    }

    /**
     * Overloaded capture screenshot for result page
     * @param driver
     * @param fileName
     * @return
     * @throws Exception
     */
    public String captureScreenShot(WebDriver driver, String fileName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = Constants.SCREENSHOTS_PATH + fileName + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(src, new File(destination));
        return destination;
    }

    /**
     * This function highlight the web-element and can be used within other action functions
     *
     * @param driver
     * @param ele2
     */
    public void highlightElement(WebDriver driver, WebElement ele2) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele2);
    }

    /**
     * This function fetches the current page title from the browser during the test execution
     *
     * @param driver
     * @return it returns page title
     */
    public String getCurrentPageTitle(WebDriver driver) {
        String pageTitle = driver.getTitle();
        return pageTitle;
    }

    /**
     * This function handles mouse-hover (AJAX) action to click on the web-element within other web-element
     *
     * @param driver
     * @param ele1
     * @param ele2
     */
    public void moveToElement(WebDriver driver, WebElement ele1, WebElement ele2) {
        Actions act = new Actions(driver);
        act.moveToElement(ele1).moveToElement(ele2).click().build().perform();
    }

    /**
     * This function fill given text into the given text-field web-element
     *
     * @param driver
     * @param ele
     * @param inputString
     */
    public void sendKeys(WebDriver driver, WebElement ele, String inputString) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        highlightElement(driver, ele);
        ele.sendKeys(inputString);
    }

}