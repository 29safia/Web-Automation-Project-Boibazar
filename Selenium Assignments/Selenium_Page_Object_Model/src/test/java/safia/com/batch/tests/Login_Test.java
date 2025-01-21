package safia.com.batch.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import safia.com.batch.basedrivers.PageDriver;
import safia.com.batch.pages.Login_Page;
import safia.com.batch.utilities.Common_Methods;
import safia.com.batch.utilities.Extent_Factory;
import java.io.IOException;

public class Login_Test extends Common_Methods {

    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void openUrl() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();
        extent = Extent_Factory.getInstance();
        parentTest = extent.createTest("<p style=\"color:red; font-size:13px\"><b>Orange HRM</b></p>").assignAuthor("Tester").assignDevice("Windows");
    }

    @Test
    public void orangehrm_Login() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:red; font-size:13px\"><b>Orange HRM</b></p>");
        Login_Page loginPage = new Login_Page(childTest);
        loginPage.login();
    }

    @AfterClass
    public void report(){
        extent.flush();
    }
}
