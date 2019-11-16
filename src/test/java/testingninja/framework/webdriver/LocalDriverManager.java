package testingninja.framework.webdriver;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private static ThreadLocal<DriverWrapper> driverWrapper = new ThreadLocal<DriverWrapper>();

    public static DriverWrapper getDriverWrapper() {
        return driverWrapper.get();
    }

    static void setDriverWrapper(DriverWrapper wrapper) {
        driverWrapper.set(wrapper);
    }
}
