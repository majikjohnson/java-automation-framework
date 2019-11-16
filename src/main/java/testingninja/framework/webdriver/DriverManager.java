package testingninja.framework.webdriver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {
    WebDriver driver;
    private DriverWrapper driverWrapper;
    private static final int DEFAULT_TIMEOUT = 10;

    protected abstract void createWebDriver();

    private void setup() {
        if(driver == null) createWebDriver();
        if(driverWrapper == null) driverWrapper = new DriverWrapper(driver);
    }

    public WebDriver getWebDriver() {
        setup();
        return driver;
    }

    public DriverWrapper getDriverWrapper() {
        setup();
        return driverWrapper;
    }

    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    void setTimeout() {
        setTimeout(DEFAULT_TIMEOUT);
    }

    private void setTimeout(int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }


}
