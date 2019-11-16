package testingninja.contactfinder.pageobjects;

import testingninja.framework.webdriver.DriverWrapper;

abstract class BasePage {
    DriverWrapper driverWrapper;

    BasePage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }
}
