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

public class Home_Page extends Common_Methods {

    ExtentTest test;
    public Home_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;

    }

    // Locators for (Authors) menu
    @FindBys({
            @FindBy(xpath = "//a[contains(text(),'লেখক')]")

    })
         WebElement lekhokMenu;

    // Locator for (Anisul Hoque) in dropdown
@FindBys({

        @FindBy(xpath = "//a[@href='https://www.boibazar.com/author-books/Anisul-Hoque']")

})
         WebElement anisulHaqueOption;


    //ScreenShot + report

    public void passCase(String message, String anisulHaque) {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
    }

    @SuppressWarnings("unused")
    public void passCaseWithSC(String message, String scName) throws IOException {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
        String screenShotPath = Screen_Shots.capture(PageDriver.getCurrentDriver(), " " + scName + " ");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    // Fail
    @SuppressWarnings("unused")
    public void failCase(String message, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        String screenShotPath = Screen_Shots.capture(PageDriver.getCurrentDriver(), " " + scName + " ");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }

    // Hover over "লেখক" and click "আনিসুল হক"
    public void selectAuthor() throws IOException {
        try {

            // Hover over "লেখক"
            test.info("Hovering over 'লেখক' menu");
            hoverOverElement(lekhokMenu);
            passCase("Select 'লেখক' successfully", "anisul_haque");
            Thread.sleep(3000);

            // Click on "আনিসুল হক"
            try{
                test.info("Clicking on 'আনিসুল হক'");
                if(anisulHaqueOption.isDisplayed()){
                    waitForElementToBeClickable(anisulHaqueOption, 70); // Call the utility method
                    anisulHaqueOption.click();
                   // Thread.sleep(3000);
                    passCase("You successfully clicked in.", "anisul_haque");
                }

            } catch (Exception e) {
                failCase("Failed to select the author 'আনিসুল হক'", "author_selection_fail");


            }

        } catch (Exception e) {
            failCase("Failed to select the author 'আনিসুল হক'", "author_selection_fail");
        }
    }

}




