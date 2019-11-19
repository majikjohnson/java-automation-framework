package testingninja.framework.utils;

public class ScreenshotManager {

    private static ThreadLocal<ScreenshotHelper> screenshotHelper = new ThreadLocal<>();

    public static ScreenshotHelper getScreenshotHelper() {
        return screenshotHelper.get();
    }

    public static void setScreenshotHelper(ScreenshotHelper helper) {
        screenshotHelper.set(helper);
    }
}
