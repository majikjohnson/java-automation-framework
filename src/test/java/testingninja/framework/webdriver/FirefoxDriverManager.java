package testingninja.framework.webdriver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {
    @Override
    protected void createWebDriver() {
        driver = new FirefoxDriver();
    }
}
