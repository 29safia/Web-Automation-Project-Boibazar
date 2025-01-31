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

public class Book_Details_Page extends Common_Methods {

    ExtentTest test;
    public Book_Details_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;

    }

    // Locator for selecting a book (example: first book)
    @FindBys({
            @FindBy(xpath = "//a[@href='https://www.boibazar.com/book/ai-golpota-hasir']")

    })
            WebElement Book;

    // Locator for book title on the details page
    @FindBys({
    @FindBy(xpath = "(//p[contains(text(),'এই গল্পটা হাসির')])") // Selects the first book dynamically

    })
           WebElement bookTitle;


    //ScreenShot + report

    public void passCase(String message, String uponnashBook) {
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

    // Clicking on the first book to view details
    public void clickOnBook() throws IOException {
        try {
            test.info("Clicking on the book to view details");
            //scrollToElement(firstBook);
            waitForElementToBeClickable(Book, 90);
            Book.click();
            passCase("Click on the book successfully","uponnash_book");




           // clickUsingJs(firstBook);
           // passCaseWithSC("Clicked on the first book successfully", "book_click_success");
        } catch (Exception e) {
            failCase("Failed to click on the book", "book_click_fail");
        }
    }

    // Verify book details page is loaded
  //  public boolean isBookDetailsPageDisplayed() {
      //  try {
        //    waitForElementToBeVisible(bookTitle, 90); // Wait for the book title to be visible
         //   return bookTitle.isDisplayed();
      //  } catch (Exception e) {
       //     test.fail("Book details page not loaded properly");
        //    return false;
      //  }
   // }
}
