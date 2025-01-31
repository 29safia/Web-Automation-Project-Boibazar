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

public class Cart_Page extends Common_Methods {

    ExtentTest test;
    public Cart_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;

    }

    // Locator for "Add to Cart" button
    @FindBys({
            @FindBy(xpath = "//img[@class='cart-zoom']")
    })
    WebElement addToCartButton;

    // Locator for "Checkout" button
    @FindBys({
            @FindBy(xpath = "//a[@href='https://www.boibazar.com/billingcart']/div[1]/div[1]")
    })
    WebElement checkoutButton;


    //ScreenShot + report

    public void passCase(String message, String bookAddedToCart) {
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

    // Method to add a book to the cart
    public void addToCart() throws IOException {
        try {
            test.info("Clicking on 'Add to Cart' button");
            waitForElementToBeClickable(addToCartButton, 10); // Wait for the button to be clickable
            addToCartButton.click();
            passCase("Book successfully added to the cart", "book_added_to_cart");
        } catch (Exception e) {
            failCase("Failed to add the book to the cart", "add_to_cart_fail");
            throw e; // Re-throw exception to handle higher up in the test
        }
    }

    // Method to click on the Checkout button
    public void proceedToCheckout() throws IOException {
        try {
            test.info("Clicking on 'Checkout' button");
            waitForElementToBeClickable(checkoutButton, 10); // Wait for the button to be clickable
            checkoutButton.click();
            passCase("Successfully clicked on the 'Checkout' button", "checkout_button_click");
        } catch (Exception e) {
            failCase("Failed to click on the 'Checkout' button", "checkout_button_fail");
            throw e; // Re-throw exception to handle higher up in the test
        }
    }


}
