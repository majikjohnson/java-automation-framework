package testingninja.framework.webdriver;

public class DriverManager {
    private static ThreadLocal<DriverWrapper> driverWrapper = new ThreadLocal<>();

    public static DriverWrapper getDriverWrapper() {
        return driverWrapper.get();
    }

    static void setDriverWrapper(DriverWrapper wrapper) {
        driverWrapper.set(wrapper);
    }
}
