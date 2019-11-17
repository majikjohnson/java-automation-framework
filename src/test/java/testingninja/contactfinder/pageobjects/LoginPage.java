package testingninja.contactfinder.pageobjects;

import org.openqa.selenium.By;
import testingninja.framework.webdriver.DriverWrapper;

public class LoginPage extends BasePage {
    private final String url = "https://shrouded-river-59487.herokuapp.com/login";
    private final By heading = By.cssSelector("[data-testid=\"login-page\"]");
    private final By emailTextBox = By.cssSelector("[data-testid=\"login-email\"]");
    private final By passwordTextBox = By.cssSelector("[data-testid=\"login-password\"]");
    private final By loginButton = By.cssSelector("[data-testid=\"login-submit\"]");
    private final By loginErrorMessage = By.cssSelector("[data-testid=\"error-alert\"]");

    public LoginPage(DriverWrapper driver) {
        super(driver);
    }

    public void open() {
        driverWrapper.open(url);
    }

    public void setEmail(String email) {
        driverWrapper.type(emailTextBox, email);
    }

    public void setPassword(String password) {
        driverWrapper.type(passwordTextBox, password);
    }

    public void clickLoginButton() {
        driverWrapper.click(loginButton);
    }

    public String getLoginErrorMessage() {
        driverWrapper.waitForElementVisible(loginErrorMessage);
        return driverWrapper.getText(loginErrorMessage);
    }

    public ContactsPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return new ContactsPage(driverWrapper);
    }

    public LoginPage invalidLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return this;
    }
}
