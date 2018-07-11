import com.gargoylesoftware.htmlunit.html.HtmlListing;
import org.openqa.selenium.By;

class IFramePage {
    static final String I_FRAME_URL = "https://the-internet.herokuapp.com/iframe";
    static final String FRAME_LOCATOR = "mce_0_ifr";
    static final By FRAME_BODY = By.id("tinymce");
    static final By FRAME_BOLD_BUTTON = By.id("mceu_3");
}
