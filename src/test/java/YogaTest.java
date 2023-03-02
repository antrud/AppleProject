import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class YogaTest {
    private IOSDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("device", "iPhone 13 Pro Max");
        capabilities.setCapability("os_version", "15");
        capabilities.setCapability("project", "First TestNG iOS Project");
        capabilities.setCapability("build", "browserstack-build-1");
        capabilities.setCapability("name", "first_test");
        capabilities.setCapability("browserstack.debug", true);
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("app", "bs://13802cf9dbba3cb405e073cd27e918203ee9ea8f");

//        driver = new AppiumDriver<>(
//                new URL("http://antonr_RdnaX2:6HxNtVmkBJPSUS8ng2UJ@hub-cloud.browserstack.com/wd/hub"), capabilities
//        );

//        driver = new IOSDriver<IOSElement>(new URL("http://antonr_RdnaX2:6HxNtVmkBJPSUS8ng2UJ@hub-cloud.browserstack.com/wd/hub"), capabilities);
        driver = new IOSDriver (
                new URL("http://antonr_RdnaX2:6HxNtVmkBJPSUS8ng2UJ@hub-cloud.browserstack.com/wd/hub"), capabilities
        );

    }

    @Test
    public void loginWithInvalidCredentials() throws InterruptedException {
        String email = "123@testtest.com";
        String password = "123456";

        YogaScreen screen = new YogaScreen(driver);
        screen.goToHasAccount();
        screen.addCredentials(email, password);
        screen.clickLogin();

        Assert.assertTrue(screen.isAlertShown());
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
