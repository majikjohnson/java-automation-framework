package testingninja.contactfinder.pageobjects;

import org.openqa.selenium.By;
import testingninja.framework.webdriver.DriverWrapper;

public class ContactsPage extends BasePage {
    private final By heading = By.cssSelector("[data-testid=\"form-title\"]");

    public ContactsPage(DriverWrapper driverWrapper) {
        super(driverWrapper);
        driverWrapper.waitForElementVisible(heading, defaultWaitSeconds);
    }

    public String getHeading() {
        return driverWrapper.getText(heading);
    }
}
