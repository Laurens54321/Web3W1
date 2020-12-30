package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class YourReservationsPage extends Page{
    public YourReservationsPage(WebDriver driver) {
        super(driver);
        driver.get(getPath() + "?command=YourReservations");
    }

    public boolean containsReservationWithName(String userid) {
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
