package testingninja.contactfinder.tests;

import org.testng.annotations.*;
import testingninja.contactfinder.pageobjects.ContactsPage;
import testingninja.contactfinder.pageobjects.LoginPage;
import testingninja.contactfinder.pageobjects.NavbarPage;
import testingninja.framework.utils.UserTestData;
import testingninja.framework.webdriver.*;

import static org.testng.Assert.*;

public class LoginTests {
    //private DriverManager driverManager;
    //private DriverWrapper driverWrapper;
    DriverType browser;
    private UserTestData userTestData;

    @BeforeSuite
    public void beforeAll() {
        this.browser = DriverType.valueOf(System.getProperty("browser"));
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource("TestData/users.json").getFile();
        userTestData = new UserTestData((file));
    }

    @Test
    public void shouldLoginSuccessfully() {
        DriverWrapper driverWrapper = LocalDriverManager.getDriverWrapper();

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
        DriverWrapper driverWrapper = LocalDriverManager.getDriverWrapper();

        String email = userTestData.getEmail(0);
        String password = "BADPASSWORD";

        LoginPage loginPage = new LoginPage(driverWrapper);
        loginPage.open();
        loginPage.invalidLogin(email, password);
        String errorMessage = loginPage.getLoginErrorMessage();

        assertEquals(errorMessage, "Incorrect password");

    }
}
