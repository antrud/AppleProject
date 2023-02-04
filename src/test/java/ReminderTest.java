import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReminderTest {

    private AppiumDriver<MobileElement> driver;
    private DesiredCapabilities desiredCapabilities;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14 Pro Max");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "com.apple.reminders");
        desiredCapabilities.setCapability("appium:autoAcceptAlerts", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void someTest() throws InterruptedException {
        String listName = "My iOS Capabilities";

        ReminderScreen screen = new ReminderScreen(driver);
        screen.acceptWelcomeScreen();
        screen.addNewListAndOpen(listName);

        Map<String, Object> itemsInList = desiredCapabilities.asMap();
        List<String> myList = convertMapToList(itemsInList);

        screen.addItemInList(myList);

        String listTitle = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"My iOS Capabilities\"]")).getText();
        List<MobileElement> actualListItems = driver.findElements(By.xpath("(//XCUIElementTypeTextField[@name=\"Title\"])"));

        Assert.assertEquals(listTitle, listName);
        Assert.assertTrue(Objects.equals(myList, extractTextFromElements(actualListItems)));

    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {

        driver.quit();
    }

    public List<String> convertMapToList(Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            list.add(entry.getKey() + "=" + entry.getValue());
        }

        return list;
    }

    public List<String> extractTextFromElements(List<MobileElement> listOfElements) {
        List<String> textList = new ArrayList<>();
        for (MobileElement item:
             listOfElements) {
            if(item.getText() != "Title") {
                textList.add(item.getText());
            }
        }

        return textList;
    }
}
