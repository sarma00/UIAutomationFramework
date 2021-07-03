package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTestClass {
    FileInputStream fis;
    public static WebDriver driver;
    public static Properties appProp;

    public void beginTest(){

        ExtentReportClass.startTest();
    }
    public void setAppPropertyFile(){
        try{
            fis = new FileInputStream("resources/config.properties");
            appProp = new Properties();
            appProp.load(fis);
        }catch(FileNotFoundException fnfE){
            System.out.println("application property file not found - " + fnfE);
        }catch(IOException iE){
            System.out.println("Error reading application property file  - "+iE);
        }
    }


    public void setUpTest(){
        setAppPropertyFile();
        String browser = appProp.getProperty("browser");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "drivers/ie.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("No Browser specified by the user, using Chrome by default");
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.get(appProp.getProperty("appURL"));
    }

    public void takeScreenShot(String testName) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("failedScreenShots/"+testName+".jpg");
        FileHandler.copy(SrcFile, DestFile);

    }

    public void endTest(){
        driver.quit();
        ExtentReportClass.endTest();
    }

}
