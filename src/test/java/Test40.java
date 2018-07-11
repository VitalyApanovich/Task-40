import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test40 {
    private static final String LOGIN = "EugenBorisik";
    private static final String PASS = "qwerty12345";
    private WebDriver driver;

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{HomePage.HOME_MENU},
                new Object[]{HomePage.OFFICE_MENU},
                new Object[]{HomePage.SIGN_OUT_LINK}
        };
    }

    @DataProvider
    public Object[][] alertsTestData() {
        return new Object[][]{
                new Object[]{AlertsPage.ALERT_BUTTON, AlertsPage.ALERT_TEXT, AlertsPage.ALERT_RESULT_TEXT},
                new Object[]{AlertsPage.CONFIRM_BUTTON, AlertsPage.CONFIRM_TEXT, AlertsPage.CONFIRM_RESULT_TEXT},
                new Object[]{AlertsPage.PROMPT_BUTTON, AlertsPage.PROMPT_TEXT, AlertsPage.PROMPT_RESULT_TEXT}
        };
    }

    @BeforeMethod
    public void beforeMethod() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("acceptInsecureCerts", true);
        driver = new ChromeDriver(caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(LoginPage.URL);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testOneTest() {
        LoginPage.login(driver, LOGIN, PASS);
        try {
            Thread.sleep(1000); // explicit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement signOutLink = driver.findElement(HomePage.HOME_MENU);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signOutLink));
        Assert.assertTrue(signOutLink.isDisplayed());
    }

    @Test
    public void testTwoTest() {
        WebElement loginButton = driver.findElement(LoginPage.LOGIN_BUTTON);
        WebElement userNameInput = driver.findElement(LoginPage.USERNAME_INPUT);
        WebElement passwordInput = driver.findElement(LoginPage.PASSWORD_INPUT);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(loginButton));
        Assert.assertTrue(loginButton.isDisplayed());
        userNameInput.sendKeys(LOGIN);
        passwordInput.sendKeys(PASS);
        Assert.assertEquals(userNameInput.getAttribute("value"), LOGIN);
        Assert.assertEquals(passwordInput.getAttribute("value"), PASS);
        driver.findElement(LoginPage.LOGIN_BUTTON).click();
        WebElement signOutLink = driver.findElement(HomePage.HOME_MENU);
        Assert.assertTrue(signOutLink.isDisplayed());
        driver.findElement(HomePage.OFFICE_MENU).click();
        WebElement searchInput = driver.findElement(OfficePage.SEARCH_INPUT);
        new WebDriverWait(driver, 15, 2700).until(ExpectedConditions.visibilityOf(searchInput));
        Assert.assertTrue(searchInput.isDisplayed());
    }

    @Test(dataProvider = "testData")
    public void ddtTest(By locator) {
        LoginPage.login(driver, LOGIN, PASS);
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void iFrameTest() {
        String word1 = "Hello ";
        String word2 = "world!";
        driver.get(IFramePage.I_FRAME_URL);
        driver.switchTo().frame(IFramePage.FRAME_LOCATOR);
        WebElement frameBody = driver.findElement(IFramePage.FRAME_BODY);
        frameBody.clear();
        frameBody.sendKeys(word1);
        driver.switchTo().defaultContent();
        driver.findElement(IFramePage.FRAME_BOLD_BUTTON).click();
        driver.switchTo().frame(IFramePage.FRAME_LOCATOR);
        frameBody.sendKeys(word2);
        String frameText = frameBody.findElement(By.cssSelector("p")).getText();
        String result = frameText.replaceAll("[\uFEFF]", "");
        Assert.assertEquals(result, word1 + word2);
        Assert.assertEquals(frameBody.findElement(By.cssSelector("strong")).getText(), word2);
    }

    @Test(dataProvider = "alertsTestData")
    public void alertsTest(By locator, String expectedMessage, String resultMessage) {
        driver.get(AlertsPage.ALERTS_URL);
        WebElement resultText = driver.findElement(AlertsPage.RESULT_MESSAGE);
        driver.findElement(locator).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), expectedMessage);
        if (locator.equals(AlertsPage.PROMPT_BUTTON)) {
            alert.sendKeys(AlertsPage.PROMPT_MESSAGE);
            resultMessage = resultMessage + AlertsPage.PROMPT_MESSAGE;
        }
        alert.accept();
        Assert.assertEquals(resultText.getText(), resultMessage);
    }
}
