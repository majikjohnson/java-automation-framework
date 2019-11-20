package testingninja.framework.utils;

import org.apache.commons.io.FileUtils;
import testingninja.framework.webdriver.DriverWrapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    private String screenshotLocation;

    public void captureScreenshot(DriverWrapper driverWrapper, String screenshotName, String screenshotPath) throws IOException {
        String dateStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File tmpScreenshot = driverWrapper.takeScreenshot();
        screenshotLocation = screenshotPath + screenshotName + dateStamp + ".png";
        File finalScreenshot = new File(screenshotLocation);
        FileUtils.copyFile(tmpScreenshot, finalScreenshot);
    }

    public String getScreenshotLocation() {
        return screenshotLocation;
    }
}
