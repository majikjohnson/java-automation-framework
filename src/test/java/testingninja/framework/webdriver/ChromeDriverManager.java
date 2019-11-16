package testingninja.framework.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {
    @Override
    protected void createWebDriver() {
        driver = new ChromeDriver();
    }
}
