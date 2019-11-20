package testingninja.framework.listeners;

import testingninja.contactfinder.tests.BaseTest;
import testingninja.framework.utils.ExtentManager;
import testingninja.framework.utils.ScreenshotHelper;
import testingninja.framework.utils.ScreenshotManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class TestListener extends BaseTest implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Report started");
        extent = ExtentManager.createInstance();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Report finished"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed"));
        test.get().pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed"));
        ScreenshotHelper screenshotHelper = ScreenshotManager.getScreenshotHelper();
        try {
            //http://extentreports.com/docs/versions/3/java/#insert-screenshots
            test.get().fail(result.getThrowable()).addScreenCaptureFromPath(screenshotHelper.getScreenshotLocation());
        } catch (IOException e) {
            e.printStackTrace();
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped"));
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
