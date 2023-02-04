import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReminderScreen {

    AppiumDriver<MobileElement> driver;

    @iOSXCUITFindBy(id = "Welcome to Reminders")
    private MobileElement welcomeTitle;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Continue\"`]")
    private MobileElement btnContinue;

    @iOSXCUITFindBy(accessibility = "Add List")
    private MobileElement btnAddList;

    @iOSXCUITFindBy(accessibility = "List Name")
    private MobileElement etNewListName;

    @iOSXCUITFindBy(accessibility = "Done")
    private MobileElement btnDone;

    @iOSXCUITFindBy(accessibility = "Empty list")
    private MobileElement itEmptyList;

    @iOSXCUITFindBy(accessibility = "Title")
    private MobileElement etListItem;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"Title\"`]")
    private List<MobileElement> actualList;

    public void acceptWelcomeScreen() {
        try {
            while(welcomeTitle.isDisplayed()) {
                btnContinue.click();
            }
        } catch (Exception ex) {
            System.out.println("Welcome screen element not found");
        }
    }

    public void addNewListAndOpen(String listName) {
        btnAddList.click();
        etNewListName.sendKeys(listName);
        btnDone.click();
        driver.findElement(By.id(listName)).click();
    }

    public void addItemInList (List<String> textToFill) {
        itEmptyList.click();

        for (String item:
             textToFill) {
            etListItem.sendKeys(item + Keys.ENTER);
        }

        btnDone.click();
    }

    public void getItemsInList() {
//        TODO: add all items.getValue() (except null) into List
    }

    public ReminderScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }
}
