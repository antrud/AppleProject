import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class YogaScreen {
    IOSDriver driver;


    @iOSXCUITFindBy(id = "get_started.have_an_account_btn")
    private MobileElement btnHasAccount;

    @iOSXCUITFindBy(id = "ic_email")
    private MobileElement etEmail;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSecureTextField[`value == \"Password\"`]")
    private MobileElement etPassword;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"LOGIN\"`]")
    private MobileElement btnLogin;

    @iOSXCUITFindBy(id = "Email or password is wrong")
    private MobileElement alertError;

    public void goToHasAccount () {
        btnHasAccount.click();
    }

    public void addCredentials(String email, String password) {
        etEmail.sendKeys(email);
        etPassword.sendKeys(password);
    }


    public void clickLogin() {
        btnLogin.click();
    }

    public boolean isAlertShown() {
        return alertError.isDisplayed();
    }

    public YogaScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);

    }
}
