package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.BaseTestClass;

public class HomePageClass extends BaseTestClass {
    WebDriver driver;

    public HomePageClass(WebDriver driver){
        this.driver = driver;
    }

    public String checkWomenCatLink(){
       WebElement elm = driver.findElement(By.xpath("//a[@title='Women']"));
       return elm.getText();
    }

    public String checkCartLink(){
        WebElement elm = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        return elm.getText();
    }
}

