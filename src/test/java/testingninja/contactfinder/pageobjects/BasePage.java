package testingninja.contactfinder.pageobjects;

import testingninja.framework.webdriver.DriverWrapper;

abstract class BasePage {
    DriverWrapper driverWrapper;
    static int defaultWaitSeconds = 5;

    BasePage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }
}
