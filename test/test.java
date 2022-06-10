import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

    }
}
/*
33 mins
  ·
//div[@class='i09qtzwb n7fi1qx3 datstx6m pmk7jnqg j9ispegn kr520xx4 k4urcfbm']/div
  https://www.facebook.com/aswadduma
success
23
true
25 May at 17:18
  ·
success
24
true
24 May at 12:13
  ·
success
25
true

"25 December 2021\n" +
                "  ·";

10 February
  ·

*/
