package testingninja.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//https://www.swtestacademy.com/extent-reports-version-3-reporting-testng/
//https://www.swtestacademy.com/extentreports-testng/
public class ExtentManager {
    private static String reportPath;
    private static String reportFileLocation;
    private static String reportFileName;

    public static ExtentReports createInstance() {
        initProperties();
        createReportPath(reportPath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFileLocation);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    private static void initProperties() {
        PropertyLoader propertyLoader = new PropertyLoader(System.getProperty("property.file.name"));
        String name = propertyLoader.getProperty("report.filename");
        String dateStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportFileName =  name + "-" + dateStamp + ".html";
        reportPath = System.getProperty("user.dir") + propertyLoader.getProperty("report.path");
        reportFileLocation = reportPath + "/" + reportFileName;
    }

    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory created: " + path);
            } else {
                System.err.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }
}
