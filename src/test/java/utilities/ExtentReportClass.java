package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportClass {
    public static ExtentTest test;
    public static ExtentReports report;

    public static void startTest(){
        DateFormat format = new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
        String timeStamp = format.format(new Date());
        try{
            report = new ExtentReports("testReports/testReport_"+timeStamp+".html");
        }catch(Exception Ex){
            Ex.printStackTrace();
        }

        test = report.startTest("Automation Practice");
    }
    public static void testPassed(){
        test.log(LogStatus.PASS,"Test Passed!");
    }

    public static void testFailed(){
        test.log(LogStatus.FAIL,"Test Failed!");
    }

    public static void testInfo(String info){
        test.log(LogStatus.INFO,info);
    }

    public static void endTest(){
        report.endTest(test);
        report.flush();
    }

}
