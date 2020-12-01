package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page {
    String path = "http://localhost:8080/Servlet";
    WebDriver driver;

    @FindBy(className = "alert-danger")
    WebElement errorMessagesField;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public boolean hasErrorMessage(String string){
        for (WebElement webel : errorMessagesField.findElements(By.tagName("li"))) {
            if (webel.getText().equals(string)) return true;
        }
        return false;
    }

}
