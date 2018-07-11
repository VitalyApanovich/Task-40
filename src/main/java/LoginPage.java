import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LoginPage {
    static final String URL = "https://192.168.100.26/";
    static final By USERNAME_INPUT = By.name("Username");
    static final By PASSWORD_INPUT = By.cssSelector("input[ type ='password']");
    static final By LOGIN_BUTTON = By.xpath(".//*[@id='SubmitButton']");

    static void login(WebDriver driver, String login, String pass) {
        driver.findElement(LoginPage.USERNAME_INPUT).sendKeys(login);
        driver.findElement(LoginPage.PASSWORD_INPUT).sendKeys(pass);
        driver.findElement(LoginPage.LOGIN_BUTTON).click();
    }

}
