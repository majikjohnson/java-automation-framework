package testingninja.framework.webdriver;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import testingninja.framework.utils.ScreenshotHelper;
import testingninja.framework.utils.ScreenshotManager;

import java.io.IOException;

public class DriverListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            DriverType browser = DriverType.valueOf(System.getProperty("browser"));
            DriverWrapper driverWrapper = DriverFactory.createInstance(browser);
            DriverManager.setDriverWrapper(driverWrapper);
            ScreenshotManager.setScreenshotHelper(new ScreenshotHelper());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            DriverWrapper driverWrapper = DriverManager.getDriverWrapper();
            if(testResult.getStatus() == ITestResult.FAILURE) {
                ScreenshotHelper screenshotHelper = ScreenshotManager.getScreenshotHelper();
                try {
                    screenshotHelper.captureScreenshot(driverWrapper, testResult.getMethod().getMethodName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (driverWrapper != null) {
                driverWrapper.quit();
            }
        }
    }
}
