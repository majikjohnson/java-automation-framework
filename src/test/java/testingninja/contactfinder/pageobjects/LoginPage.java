package testingninja.contactfinder.pageobjects;

import org.openqa.selenium.By;
import testingninja.framework.webdriver.DriverWrapper;

public class LoginPage extends BasePage {
    private String url = "https://shrouded-river-59487.herokuapp.com/login";
    private By emailTextBox = By.cssSelector("[data-testid=\"login-email\"]");
    private By passwordTextBox = By.cssSelector("[data-testid=\"login-password\"]");
    private By loginButton = By.cssSelector("[data-testid=\"login-submit\"]");

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
