package testingninja.contactfinder.pageobjects;

import org.openqa.selenium.By;
import testingninja.framework.webdriver.DriverWrapper;

public class NavbarPage extends BasePage {
    private final By userGreeting = By.cssSelector("[data-testid=\"user-greeting\"]");
    private final String headingPattern = "Hello [^|]* \\|";
    private final int waitSeconds = 3;

    public NavbarPage(DriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public String getUserGreeting() {
        driverWrapper.waitForTextPatternPresent(userGreeting, headingPattern, waitSeconds);
        return driverWrapper.getText(userGreeting);
    }
}
