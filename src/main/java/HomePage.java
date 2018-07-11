import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class HomePage {
    static final By HOME_MENU = By.linkText("Home");
    static final By OFFICE_MENU = By.id("officeMenu");
    static final By SIGN_OUT_LINK = By.cssSelector("[title='Sign out']");
}
