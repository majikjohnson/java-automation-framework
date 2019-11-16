package testingninja.contactfinder.pageobjects;

import org.openqa.selenium.By;
import testingninja.framework.webdriver.DriverWrapper;

public class NavbarPage extends BasePage {
    private By userGreeting = By.cssSelector("[data-testid=\"user-greeting\"]");
    private static final String HEADING_PATTERN = "Hello [^|]* \\|";

    public NavbarPage(DriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public String getUserGreeting() {
        driverWrapper.waitForTextPatternPresent(userGreeting, HEADING_PATTERN);
        return driverWrapper.getText(userGreeting);
    }
}
