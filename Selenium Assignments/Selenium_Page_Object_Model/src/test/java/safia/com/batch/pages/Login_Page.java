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


public class Login_Page extends Common_Methods {

    ExtentTest test;

    public Login_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;

    }

    //Locators
    @FindBys({
            @FindBy(xpath = "//input[@name='username']"),
            @FindBy(xpath = "//input[@placeholder='Username']")
    })
    WebElement username;

    @FindBys({
            @FindBy(xpath="//input[@name='password']")
    })
    WebElement password;

    @FindBys({
            @FindBy(xpath="//button[@type='submit']")
    })
    WebElement login_button;

    //ScreenShot + report
    public void passCase(String message) {
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


    public void login() throws IOException {
        try{
            test.info("please enter username");
            if(username.isDisplayed()){
                username.sendKeys("Admin");
                passCase("You have successfully entered the username");
                Thread.sleep(5000);

                try{
                    test.info("please enter password");
                    if(password.isDisplayed()){
                        password.sendKeys("admin123");
                        passCase("You have successfully entered the password");
                        Thread.sleep(5000);

                        try{
                            test.info("please click on login button");
                            if(login_button.isDisplayed()){
                                login_button.click();
                                Thread.sleep(5000);
                                passCaseWithSC("You successfully loged in.", "login_success");
                            }

                        } catch (Exception e) {
                            failCase("Login Button is not locateable. Please check the log", "login_button_fail");


                        }
                    }
                } catch (Exception e) {
                    failCase("password is not locateable. Please check the log", "password_fail");

                }

            }

        } catch (Exception e) {
            failCase("username is not locateable. Please check the log", "user_name_fail");

        }
    }


}
