package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class PageBase {
    public static Logger logger = Logger.getLogger(PageBase.class);
    public static WebDriver driver;
    public Actions action;
    public WebDriverWait wait;
    Select select;
    JavascriptExecutor js;


    public PageBase(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;
    }


    public boolean clickButton(By element) {

        try {
            js.executeScript("arguments[0].click();", driver.findElement(element));
            return true;
        } catch (Exception e) {
            logger.error("Element is clicked By java Script" + element + e);
            return false;
        }
    }

    public boolean isDisplay(By element) {

        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (Exception e) {
            logger.error("Element is displayed" + element + e);
            return false;
        }

    }

    public void selectByVisibaleText(By element, String Value) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(Value);
    }


    public boolean clickOnButton(By element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            driver.findElement(element).click();
            return true;

        } catch (Exception e) {
            logger.error("Element is clicked" + element + e);
            return false;
        }


    }

    public boolean sendText(By element, String value) {
        try {
            driver.findElement(element).sendKeys(value);
            return true;
        } catch (Exception e) {
            logger.error("Element Send Text" + element + e);
            return false;
        }
    }

    public boolean sendKeys(By element, String value) {
        try {
            driver.findElement(element).sendKeys(value);
            return true;

        } catch (Exception e) {
            logger.error("Element Send Keys" + element + e);
            return false;
        }
    }

    public boolean clearText(By element) {
        try {
            driver.findElement(element).clear();
            return true;
        } catch (Exception e) {
            logger.error("Clear Text" + element + e);
            return false;
        }
    }

    public String getText(By element) {

        return driver.findElement(element).getText();
    }

    public void hoverMouse(By element) {
        try {
            action.moveToElement(driver.findElement(element)).build().perform();

        } catch (Exception e) {
            logger.error("Hover Mouse" + element + e);
        }
    }

    public boolean scrollViewElement(By element) {

        try {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
            return true;
        } catch (Exception e) {
            logger.error("Scroll View Element" + element + e);
            return false;
        }
    }


    public int generateEmail() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(999999);
        return randomInt;
    }


}
