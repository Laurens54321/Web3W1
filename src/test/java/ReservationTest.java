import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Servlet";

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laure\\Documents\\school\\Web 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Register");
    }

    @After
    public void clean() {
        driver.quit();
    }




    @Test
    public void test_login_Incorrect_Credentials() {
        driver.get(path+"?command=Profile");

        logIn("admin", "p");

        String title = driver.getTitle();
        assertEquals("Sign In",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Password Incorrect", errorMsg.getText());


    }

    @Test
    public void test_Login_Nonexisting_Credentials() {
        driver.get(path+"?command=Profile");

        logIn("nonexistinguser", "p");

        String title = driver.getTitle();
        assertEquals("Sign In",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Username not found", errorMsg.getText());
    }

    @Test
    public void test_login_Correct_Credentials() {
        driver.get(path+"?command=Profile");

        logIn("admin", "t");

        String title = driver.getTitle();
        assertEquals("Profile",title);

    }

    @Test
    public void test_Reservation_Incomplete0_Registration() {
        driver.get(path+"?command=Profile");

        logIn("admin", "t");

        fillOutField("field", "6");
        WebElement button=driver.findElement(By.id("makeReservation"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Reservation must have an start date.", errorMsg.getText());
    }

    @Test
    public void test_Reservation_Incomplete1_Registration() {
        driver.get(path+"?command=Profile");

        logIn("admin", "t");

        String s = LocalDateTime.now().format(dateTimeFormatter);

        WebElement field=driver.findElement(By.id("startTime"));
        field.clear();
        field.sendKeys(s);
        field.sendKeys("\n");


        WebElement button=driver.findElement(By.id("makeReservation"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Reservation must have an end date.", errorMsg.getText());
    }

    /*
        Test is useless because it cannot input properly

    @Test
    public void test_Reservation_Incomplete2_Registration() {
        driver.get(path+"?command=Profile");

        logIn("admin", "t");

        String s = LocalDateTime.now().format(dateTimeFormatterformatter);
        fillOutTimeField("startTime", s);


        String ss = LocalDateTime.now().minusHours(12).format(dateTimeFormatterformatter);
        fillOutTimeField("endTime", ss);

        fillOutField("field", "6");
        WebElement button=driver.findElement(By.id("makeReservation"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("End date must be after start date", errorMsg.getText());
    }

     */


    @Test
    public void test_Reservation_Complete_Registration() {
        driver.get(path+"?command=Profile");

        logIn("admin", "t");

        String s = LocalDateTime.now().format(dateTimeFormatter);
        fillOutTimeField("startTime", s);

        String ss = LocalDateTime.now().plusHours(2).format(timeFormatter);
        fillOutField("endTime", ss);


        fillOutField("field", "6");


        WebElement button=driver.findElement(By.id("makeReservation"));
        button.click();

        String title = driver.getTitle();
        assertEquals(title, "Overview");

        List<WebElement> tds = driver.findElements(By.className("reservations"));


        ArrayList<String> string = new ArrayList<>();
        string.add("admin");
        string.add(LocalDateTime.now().format(timeFormatter).strip());
        assertTrue(tableContainsText(tds, string));
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void fillOutTimeField(String name, String value){
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
        field.sendKeys("\n");
    }

    private void logIn(String userid, String password) {
        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("logIn"));
        button.click();
    }

    private boolean tableContainsText (List<WebElement> elements, ArrayList<String> text) {
        int tests = 0;
        for (WebElement e : elements) {
            List<WebElement> tds = e.findElements(By.cssSelector("td"));
            for (WebElement td:tds  ) {
                if (text.contains(td.getText().strip())) tests += 1;
            }
            System.out.println("Matches: " + tests);
            if (tests == text.size()) return true;
            else tests = 0;
        }
        return false;
    }
}
