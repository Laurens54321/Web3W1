package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class PersonOverviewPage extends Page {

    @FindBy(id = "Members")
    WebElement memberTable;

    @FindBy(className = "message")
    WebElement signUpLink;

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Overview");
    }

    public boolean containsUserWithUserid(String userid) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) memberTable.findElements(By.className("userid"));
        System.out.println(listItems.size());
        boolean found=false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().equals(userid)) {
                found=true;
            }
        }
        return found;
    }

    public boolean hasSignUpLink(){
        if (signUpLink != null) return true;
        else return false;
    }
}
