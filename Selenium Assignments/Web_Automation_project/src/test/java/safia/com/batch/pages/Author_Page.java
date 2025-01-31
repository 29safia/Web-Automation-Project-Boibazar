package safia.com.batch.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import safia.com.batch.basedrivers.PageDriver;
import safia.com.batch.utilities.Common_Methods;
import safia.com.batch.utilities.Screen_Shots;

import java.io.IOException;

public class Author_Page extends Common_Methods {

        ExtentTest test;
        public Author_Page(ExtentTest test) {
            PageFactory.initElements(PageDriver.getCurrentDriver(), this);
            this.test = test;

        }

    // Locator for the "আরো দেখুন" (Aro Dekhun) button
        @FindBys({
                @FindBy(xpath = "//button[@id='viewMore']")
        })
        WebElement aroDekhunButton;

        //ScreenShot + report

        public void passCase(String message, String aroDekhunSuccess) {
            test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
        }

        @SuppressWarnings("unused")
        public void passCaseWithSC(String message, String scName) throws IOException {
            test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
            String screenShotPath = Screen_Shots.capture(PageDriver.getCurrentDriver(), "" + scName + "");
            String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        }

        // Fail
        @SuppressWarnings("unused")
        public void failCase(String message, String scName) throws IOException {
            test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
            Throwable t = new InterruptedException("Exception");
            test.fail(t);
            String screenShotPath = Screen_Shots.capture(PageDriver.getCurrentDriver(), "" + scName + "");
            String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
            PageDriver.getCurrentDriver().quit();
        }

    // Scroll and click the "আরো দেখুন" button
    public void clickNextPage() throws IOException {
        try {
            test.info("Scrolling down to 'আরো দেখুন' button");
            scrollToElement(aroDekhunButton);
            passCase("Successfully scrolled to 'আরো দেখুন' button", "aro_dekhun_success");
            Thread.sleep(3000);
            //waitForElementToBeClickable(aroDekhunButton, 90);


            test.info("Clicking on 'আরো দেখুন' button");
            //clickUsingJs(aroDekhunButton);
            //waitForElementToBeClickable(aroDekhunButton, 90);
            aroDekhunButton.click();
            passCase("Clicked on 'আরো দেখুন' successfully", "aro_dekhun_success");
            Thread.sleep(3000);

        } catch (Exception e) {
            failCase("Failed to click on 'আরো দেখুন' button", "aro_dekhun_fail");
        }
    }

    }
