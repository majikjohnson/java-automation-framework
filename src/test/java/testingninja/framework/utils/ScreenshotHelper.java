package testingninja.framework.utils;

import testingninja.framework.webdriver.DriverWrapper;

import java.io.File;

public class ScreenshotHelper {
    private String screenshotLocation;

    public void captureScreenshot(DriverWrapper driverWrapper) {
        File screenshot = driverWrapper.takeScreenshot();
        screenshotLocation = screenshot.getPath();
    }

    public String getScreenshotLocation() {
        return screenshotLocation;
    }
}
