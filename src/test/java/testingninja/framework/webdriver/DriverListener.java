package testingninja.framework.webdriver;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class DriverListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            DriverType browser = DriverType.valueOf(System.getProperty("browser"));
            DriverWrapper driverWrapper = LocalDriverFactory.createInstance(browser);
            LocalDriverManager.setDriverWrapper(driverWrapper);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            DriverWrapper driverWrapper = LocalDriverManager.getDriverWrapper();
            if (driverWrapper != null) {
                driverWrapper.quit();
            }
        }
    }
}
