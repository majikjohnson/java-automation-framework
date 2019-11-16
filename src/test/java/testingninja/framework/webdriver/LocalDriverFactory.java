package testingninja.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

class LocalDriverFactory {
    static DriverWrapper createInstance(DriverType type) {
        DriverWrapper driverWrapper;

        switch(type) {
            case CHROME:
                driverWrapper = new DriverWrapper(new ChromeDriver());
                break;
            case FIREFOX:
                driverWrapper = new DriverWrapper(new FirefoxDriver());
                break;
            default:
                driverWrapper = new DriverWrapper(new ChromeDriver());
                break;
        }
        return driverWrapper;
    }
}
