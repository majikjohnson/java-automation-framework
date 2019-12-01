package testingninja.framework.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class DriverWrapper {
    private final int MAX_RETRIES = 5;
    private WebDriver driver;

    DriverWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        if (System.getProperty("browser").contains("IEXPLORER")) {
            driver.manage().window().maximize();
            driver.navigate().to(url);
        } else {
            driver.get(url);
        }
    }

    public void quit() {
        driver.quit();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void waitForElementVisible(By locator, int waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForTextPatternPresent(By locator, String pattern, int waitSeconds) {
        //https://trickyautomationworld.blogspot.com/2018/02/selenium-wrapper-automation_76.html
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds); //waitSeconds is the polling interval
        int retry = 0;
        boolean matchFound = false;

        while (retry < MAX_RETRIES && !matchFound) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            if (text.matches(pattern)) {
                matchFound = true;
            }
            retry++;
        }

        if (!matchFound) {
            throw new NotFoundException("Unable to find text matching pattern: " + pattern);
        }
    }

    public File takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.FILE);
    }
}
