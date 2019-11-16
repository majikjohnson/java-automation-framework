package testingninja.contactfinder.tests;

import org.testng.annotations.*;
import testingninja.contactfinder.pageobjects.ContactsPage;
import testingninja.contactfinder.pageobjects.LoginPage;
import testingninja.contactfinder.pageobjects.NavbarPage;
import testingninja.framework.utils.UserTestData;
import testingninja.framework.webdriver.DriverManager;
import testingninja.framework.webdriver.DriverManagerFactory;
import testingninja.framework.webdriver.DriverType;
import testingninja.framework.webdriver.DriverWrapper;

import static org.testng.Assert.*;

public class LoginTests {
    private DriverManager driverManager;
    private DriverWrapper driverWrapper;
    private UserTestData userTestData;

    @BeforeSuite
    public void beforeAll() {
        DriverType browser = DriverType.valueOf(System.getProperty("browser"));
        driverManager = DriverManagerFactory.getDriverManager(browser);
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource("TestData/users.json").getFile();
        userTestData = new UserTestData((file));
    }

    @BeforeTest
    public void beforeEach() {
        driverWrapper = driverManager.getDriverWrapper();
    }

    @AfterTest
    public void afterEach() {
        driverManager.quitWebDriver();
    }

    @Test
    public void shouldLoginSuccessfully() {
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
}
