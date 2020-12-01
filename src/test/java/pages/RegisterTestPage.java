package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterTestPage extends Page {

    @FindBy(id = "date")
    WebElement dateField;

    public RegisterTestPage(WebDriver driver) {
        super(driver);
        driver.get(getPath() + "?command=RegisterTestPage");
    }



}
