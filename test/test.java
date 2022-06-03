import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\pushpakumara\\Wixis\\SeleniumPro\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions().addArguments("--headless");//Chrome browser hide ..................
        WebDriver driver =new ChromeDriver();

        driver.get("https://www.facebook.com/aswadduma");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState")
                .equals("complete"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='see_more_link']"));
        System.out.println("ok");

        for (int i = 0; i < elements.size(); i++) {
            js.executeScript("arguments[0].scrollIntoView(true);",elements.get(i));
            elements.get(i).click();
        }

            /*js.executeScript("arguments[0].scrollIntoView(true);",elements.get(0));
            elements.get(0).click();
        js.executeScript("arguments[0].scrollIntoView(true);",elements.get(1));
        elements.get(1).click();*/



Thread.sleep(7000);

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
