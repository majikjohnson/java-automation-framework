package testingninja.contactfinder.tests;

import org.testng.annotations.*;
import testingninja.contactfinder.data.UsersTestData;
import testingninja.contactfinder.pageobjects.ContactsPage;
import testingninja.contactfinder.pageobjects.LoginPage;
import testingninja.contactfinder.pageobjects.NavbarPage;
import testingninja.framework.webdriver.DriverThreadManager;
import testingninja.framework.webdriver.DriverWrapper;

import static org.testng.Assert.*;

public class LoginTests extends BaseTest {
    private UsersTestData userTestData;

    @BeforeClass
    public void initTestData() {
        userTestData = new UsersTestData("TestData/users.json");
    }

    @Test
    public void shouldLoginSuccessfully() {
        DriverWrapper driverWrapper = DriverThreadManager.getDriverWrapper();

        String email = userTestData.getEmail("validUser");
        String password = userTestData.getDecryptedPassword("validUser");

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
        DriverWrapper driverWrapper = DriverThreadManager.getDriverWrapper();

        String email = userTestData.getEmail("invalidPassword");
        String password = userTestData.getPassword("invalidPassword");

        LoginPage loginPage = new LoginPage(driverWrapper);
        loginPage.open();
        loginPage.invalidLogin(email, password);
        String errorMessage = loginPage.getLoginErrorMessage();

        assertEquals(errorMessage, "Incorrect passwor");
    }
}
