import org.openqa.selenium.By;

public class AlertsPage {
    static final By PROMPT_BUTTON = By.cssSelector("[onclick='jsPrompt()']");
    static final String PROMPT_TEXT = "I am a JS prompt";
    static final String PROMPT_RESULT_TEXT = "You entered: ";
    static final String PROMPT_MESSAGE = "Hello world!";
    static final By CONFIRM_BUTTON = By.cssSelector("[onclick='jsConfirm()']");
    static final String CONFIRM_TEXT = "I am a JS Confirm";
    static final String CONFIRM_RESULT_TEXT = "You clicked: Ok";
    static final By ALERT_BUTTON = By.cssSelector("[onclick='jsAlert()']");
    static final String ALERT_TEXT = "I am a JS Alert";
    static final String ALERT_RESULT_TEXT = "You successfuly clicked an alert";
    static final By RESULT_MESSAGE = By.id("result");
    static final String ALERTS_URL = "https://the-internet.herokuapp.com/javascript_alerts";

}
