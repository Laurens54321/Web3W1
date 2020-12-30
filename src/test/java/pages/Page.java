package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {
    String path = "http://localhost:8080/Servlet";
    WebDriver driver;

    @FindBy(className = "alert-danger")
    WebElement errorMessagesField;

    @FindBy(className = "message")
    WebElement messageField;

    @FindBy(linkText = "Home")
    WebElement navHome;

    @FindBy(linkText = "Overview")
    WebElement navOverview;

    @FindBy(linkText = "Profile")
    WebElement navProfile;

    @FindBy(linkText = "Register Test")
    WebElement navTest;

    @FindBy(linkText = "Your Reservations")
    WebElement navYourReservations;

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

    public boolean hasstatusMessage(String message){
        WebElement messageDiv = driver.findElement(By.className("message"));
        return messageDiv.findElement(By.cssSelector("p")).getText().equals(message);
    }

    public HomePage navigateToHome(){
        navHome.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public RegisterPage navigateToRegister(){
        driver.get("localhost:8080/Servlet?command=Register");
        return PageFactory.initElements(driver, RegisterPage.class);
    }

}
