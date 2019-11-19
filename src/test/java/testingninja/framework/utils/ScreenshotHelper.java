package testingninja.framework.utils;

import org.apache.commons.io.FileUtils;
import testingninja.framework.webdriver.DriverWrapper;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    private String screenshotPath;

    public void captureScreenshot(DriverWrapper driverWrapper, String screenshotName) throws IOException {
        String dateStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = driverWrapper.takeScreenshot();
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateStamp + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        screenshotPath = destination;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }
}
