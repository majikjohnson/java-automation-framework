package testingninja.framework.listeners;

import testingninja.framework.utils.PropertyLoader;
import testingninja.framework.utils.ScreenshotHelper;
import testingninja.framework.utils.ScreenshotManager;
import testingninja.framework.webdriver.DriverFactory;
import testingninja.framework.webdriver.DriverManager;
import testingninja.framework.webdriver.DriverType;
import testingninja.framework.webdriver.DriverWrapper;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import java.io.IOException;

public class InvocationListener implements IInvokedMethodListener {
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
                    String screenshotName = testResult.getMethod().getMethodName();
                    PropertyLoader propertyLoader = new PropertyLoader(System.getProperty("property.file.name"));
                    String screenshotPath = System.getProperty("user.dir") + propertyLoader.getProperty("report.screenshots.path");
                    screenshotHelper.captureScreenshot(driverWrapper, screenshotName, screenshotPath);
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
