package testingninja.framework.webdriver;

public class DriverThreadManager {
    private static ThreadLocal<DriverWrapper> driverWrapper = new ThreadLocal<>();

    public static DriverWrapper getDriverWrapper() {
        return driverWrapper.get();
    }

    public static void setDriverWrapper(DriverWrapper wrapper) {
        driverWrapper.set(wrapper);
    }
}
