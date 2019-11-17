package testingninja.contactfinder.tests;

import org.testng.SkipException;
import org.testng.annotations.*;
import testingninja.contactfinder.dso.UserTestData;
import testingninja.contactfinder.pageobjects.ContactsPage;
import testingninja.contactfinder.pageobjects.LoginPage;
import testingninja.contactfinder.pageobjects.NavbarPage;
import testingninja.framework.webdriver.*;

import static org.testng.Assert.*;

public class LoginTests {
    private UserTestData userTestData;

    @BeforeSuite
    public void beforeAll() {
            userTestData = new UserTestData("TestData/users.json");
    }

    @Test
    public void shouldLoginSuccessfully() {
        DriverWrapper driverWrapper = DriverManager.getDriverWrapper();

        String email = userTestData.getEmail(0);
        String password = userTestData.getPassword(0);

        LoginPage loginPage = new LoginPage(driverWrapper);
        loginPage.open();

        ContactsPage contactsPage = loginPage.login(email, password);
        String contactsPageHeading = contactsPage.getHeading();

        NavbarPage navbar = new NavbarPage(driverWrapper);
        String navbarGreeting = navbar.getUserGreeting();

        assertEquals(contactsPageHeading, "Add Contact");
        assertEquals(navbarGreeting, "Hello Matt |");
    }

    @Test
    public void shouldShowIncorrectPasswordError() {
        DriverWrapper driverWrapper = DriverManager.getDriverWrapper();

        String email = userTestData.getEmail(0);
        String password = "BADPASSWORD";

        LoginPage loginPage = new LoginPage(driverWrapper);
        loginPage.open();
        loginPage.invalidLogin(email, password);
        String errorMessage = loginPage.getLoginErrorMessage();

        assertEquals(errorMessage, "Incorrect password");
    }
}
