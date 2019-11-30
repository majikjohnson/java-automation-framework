package testingninja.framework.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static DriverWrapper createInstance(DriverType type) {
        DriverWrapper driverWrapper;

        switch (type) {
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driverWrapper = new DriverWrapper(new EdgeDriver());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driverWrapper = new DriverWrapper(new FirefoxDriver(firefoxOptions));
                break;
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = getChromeOptions();
                driverWrapper = new DriverWrapper(new ChromeDriver(chromeOptions));
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
