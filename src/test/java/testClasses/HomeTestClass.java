package testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageClasses.HomePageClass;
import utilities.BaseTestClass;
import utilities.ExtentReportClass;

public class HomeTestClass {
    BaseTestClass baseTestClass = new BaseTestClass();

    @BeforeSuite
    public void startTest() {
        baseTestClass.beginTest();
        baseTestClass.setUpTest();
    }

    @Test
    public void checkWoemnCategoryLink() {
        ExtentReportClass.testInfo("Starting test Cases - Women Category Test");
        String actual = new HomePageClass(BaseTestClass.driver).checkWomenCatLink();
        ExtentReportClass.testInfo("String extracted from script - " + actual);
        try {
            Assert.assertEquals(actual, "WOMEN");
            ExtentReportClass.testPassed();
        } catch (AssertionError Ex) {

            ExtentReportClass.testInfo("Category name mismatch , Actual - " + actual + " Expected -  WOMEN");
            ExtentReportClass.testFailed();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            baseTestClass.takeScreenShot("checkCategoryLink");
        } catch (Exception Ex) {
            ExtentReportClass.testInfo("Error taking screenshot - " + Ex.getMessage());
        }
    }

    @Test
    public void checkCartLink() {
        ExtentReportClass.testInfo("Starting test Cases - Checkout Test");
        String actual = new HomePageClass(BaseTestClass.driver).checkCartLink();
        ExtentReportClass.testInfo("String extracted from script - " + actual);
        try {
            Assert.assertEquals(actual, "Cart (empty)");
            ExtentReportClass.testPassed();
        } catch (AssertionError Ex) {
            ExtentReportClass.testInfo("Category name mismatch , Actual - " + actual + " Expected -  Cart");
            ExtentReportClass.testFailed();
        }

        try {
            baseTestClass.takeScreenShot("checkCategoryLink");
        } catch (Exception Ex) {
            ExtentReportClass.testInfo("Error taking screenshot - " + Ex.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        baseTestClass.endTest();
    }
}
