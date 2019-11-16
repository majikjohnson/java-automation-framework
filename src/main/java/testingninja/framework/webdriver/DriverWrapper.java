package testingninja.framework.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWrapper {
    private final int DEFAULT_TIMEOUT_SECONDS = 5;
    private final int RETRY_WAIT_SECONDS = 1;
    private final int MAX_RETRIES = 3;
    private WebDriver driver;
    private int timeout;

    DriverWrapper(WebDriver driver) {
        this.driver = driver;
        this.timeout = DEFAULT_TIMEOUT_SECONDS;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void click(By selector) {
        driver.findElement(selector).click();
    }

    public void type(By selector, String text) {
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }

    public String getText(By selector) {
        return driver.findElement(selector).getText();
    }

    public void waitForElementVisible(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void waitForTextPatternPresent(By selector, String pattern) {
        WebDriverWait wait = new WebDriverWait(driver, RETRY_WAIT_SECONDS);

        int retry = 0;
        boolean matchFound = false;

        while (retry < MAX_RETRIES && !matchFound) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
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
}
