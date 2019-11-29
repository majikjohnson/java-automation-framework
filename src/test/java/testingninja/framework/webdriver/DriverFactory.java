package testingninja.framework.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static DriverWrapper createInstance(DriverType type) {
        DriverWrapper driverWrapper;

        switch(type) {
            case CHROME:
                ChromeOptions chromeOptions = getChromeOptions();
                driverWrapper = new DriverWrapper(new ChromeDriver(chromeOptions));
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driverWrapper = new DriverWrapper(new FirefoxDriver(firefoxOptions));
                break;
            default:
                driverWrapper = new DriverWrapper(new ChromeDriver());
                break;
        }
        return driverWrapper;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1280");
        options.addArguments("--height=800");
        return options;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1280,800");
        return options;
    }
}
