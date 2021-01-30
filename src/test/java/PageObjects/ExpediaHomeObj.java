package PageObjects;

import Utility.BaseUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.List;

public class ExpediaHomeObj extends BaseUtils {

    WebDriver driver;
    public ExpediaHomeObj(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[@data-stid='button-type-picker-trigger']")
    public WebElement regionSelect;

    @FindBy(how = How.XPATH, using = "//select[@id='site-selector']")
    public WebElement siteSelector;

    @FindBy(how = How.XPATH, using = "//button[text()='Save']")
    public WebElement saveregion;


    @FindBy(how = How.XPATH, using = "//button[starts-with(@aria-label,'Going to')]")
    public WebElement goingTo;

    @FindBy(how = How.XPATH, using = "//input[@name='add-flight-switch']")
    public WebElement selectFlightCheckbox;

    @FindBy(how = How.XPATH, using = "//button[starts-with(@aria-label,'Leaving from')]")
    public WebElement leavingFrom;

    @FindBy(how = How.XPATH, using = "//button[starts-with(@aria-label,'Check-in')]")
    public WebElement checkInDate;

    @FindBy(how = How.XPATH, using = "//span[text()='Done']")
    public WebElement dateDoneBtn;

    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label,'travelers')]")
    public WebElement travelersSelect;

    @FindBy(how = How.XPATH, using = " //input[@id='adult-input-0']")
    public WebElement currentAdultValue;

    @FindBy(how = How.XPATH, using = "//*[contains(@aria-labelledby,'decrease-adults')]")
    public WebElement decAdult;

    @FindBy(how = How.XPATH, using = "//*[contains(@aria-labelledby,'increase-adults')]")
    public WebElement incAdult;

    @FindBy(how = How.XPATH, using = "//input[@id='child-input-0']")
    public WebElement childrenValue;

    @FindBy(how = How.XPATH, using = "//*[contains(@aria-labelledby,'increase-children')]")
    public WebElement incChildren;

    @FindBy(how = How.XPATH, using = "//*[contains(@aria-labelledby,'decrease-children')]")
    public WebElement decChildren;

    @FindBy(how = How.XPATH, using = "//select[@id='child-age-input-0-0']")
    public WebElement childAge;

    @FindBy(how = How.XPATH, using = "//button[@data-testid='guests-done-button']")
    public WebElement guestDone;

    @FindBy(how = How.XPATH, using = "//button[text()='Search']")
    public WebElement searchBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='location-field-origin-menu']/div/ul/li//strong")
    public List<WebElement> originResult;

    @FindBy(how = How.XPATH, using = "//div[@id='location-field-destination-menu']/div/ul/li//strong")
    public List<WebElement> destinationResult;

    @FindBy(how = How.XPATH, using = "//div[@class='uitk-new-date-picker-month']/h2")
    public WebElement datePickerMonth;

    @FindBy(how = How.XPATH, using = "//div[@class='uitk-calendar']/div/button[2]")
    public WebElement nextMonth;

    @FindBy(how = How.XPATH, using = "//table[contains(@class,'date-picker-weeks')]/tbody/tr/td/button")
    public List<WebElement> daySelect;

    @FindBy(how = How.XPATH, using = "//li[@data-stid='property-listing']")
    public List<WebElement> searchResultProperty;


    /**
     * this method will return the date after split in day and year
     * @param date
     * @return object with data (day, Month year)
     */
    public String[] getDateMonth(String date) {
        return date.split("-");
    }

    /**
     * this method will select the travel date and handles  the date picker on web page
     * and selects appropriate date given by user
     * @param date
     * @throws InterruptedException
     */
    public void selectTravelDate(String date) throws InterruptedException {
        boolean isdateSelected = false;
        int i =0;
        String currentMonthSelected = datePickerMonth.getText();
        while (!currentMonthSelected.equalsIgnoreCase(getDateMonth(date)[1].toString())) {

            Click(driver, nextMonth);
            currentMonthSelected = datePickerMonth.getText();
            System.out.print("current + "+ currentMonthSelected + " " +getDateMonth(date)[1].toString());
        }
        List<WebElement> el = daySelect;
        if (!el.isEmpty()) {
            for (WebElement webElement : el) {
                i++;
                if (webElement.getAttribute("data-day").equalsIgnoreCase(getDateMonth(date)[0].toString())) {
                    Click(driver, webElement);//checkin date
                    isdateSelected= true;
                }
                if(isdateSelected){
                    Click(driver, el.get(i));// Checkout date
                    break;
                }
            }
        } else {
            System.out.print("error while choosing date ");
        }

    }

    /**
     * this method will select the city for user
     * Origin city and destination city
     * Will search in list of available option with city code
     * @param driver
     * @param ele
     * @param city
     * @param cityCode
     * @param destination
     * @throws InterruptedException
     * @throws AWTException
     */
    public void selectCity(WebDriver driver, WebElement ele, String city, String cityCode, List<WebElement> destination) throws InterruptedException, AWTException {
        Click(driver, ele);
        sendKeys(driver, ele, city);
        List<WebElement> el = destination;
        if (!el.isEmpty()) {
            for (WebElement webElement : el) {
                if (webElement.getText().contains(cityCode)) {
                    Click(driver, webElement);
                }
            }
        } else {
            System.out.print("No such city found with name " + city);
        }
    }

    /**
     * this method use for clicking multiple times
     * example increasing number of travelers and childrenes
     * @param driver
     * @param currentValue
     * @param numberTobe
     * @param decrease
     * @param increase
     */
    public void clickmultipleTimes(WebDriver driver, String currentValue, String numberTobe, WebElement decrease, WebElement increase) {
        int currentValueInt = Integer.parseInt(currentValue);
        int NumberTobe = Integer.parseInt(numberTobe);
        int numberOfClicks = NumberTobe - currentValueInt; //if difference is +ve then increase number of travelers
        int i = 0;
        if (numberOfClicks > 0) {
            while (i != numberOfClicks) {
                Click(driver, increase);//clicks till time it becomes same as number to be
                i++;
            }
        } else {
            if (numberOfClicks < 0) {
                while (i != numberOfClicks) {// if difference is -Ve decrease number of travelers
                    Click(driver, decrease);
                    i--;
                }

            }
        }
    }
}