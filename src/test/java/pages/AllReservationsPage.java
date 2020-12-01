package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class AllReservationsPage extends Page {
    public AllReservationsPage(WebDriver driver) {
        super(driver);
        driver.get(getPath() + "?command=AllReservations");
    }

    public boolean containsReservationWithUserid(String userid) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(userid)) {
                found=true;
            }
        }
        return found;
    }
}
