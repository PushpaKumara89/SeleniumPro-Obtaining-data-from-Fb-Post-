package sampleapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GoogleAutomate {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "E:\\pushpakumara\\Wixis\\SeleniumPro\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions().addArguments("--headless");

        WebDriver driver =new ChromeDriver();

        driver.get("https://www.google.com/");

        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@jsaction='paste:puy29d;']")).sendKeys("gabba");//search input

        System.out.println(driver.getTitle());
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[2]/ul[1]/div/ul/li"));//searched items
        System.out.println(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            String st = elements.get(i).getText();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(st);
            if (/*st.contains("Blake")*/3==i){
                elements.get(i).click();//click the selected item
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"hdtb-msb\"]/div[1]/div/div[2]/a")).click();//click image btn
        List<WebElement> imgGoogle = driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div"));// get image outer element (this is loop)
        System.out.println(imgGoogle.size());
        for (int i = 0; i < 5; i++) {//iterate imgGoogle list Object
            String st = imgGoogle.get(i).findElement(By.tagName("img")).getAttribute("src");//get inner details by img tag
            System.out.println(st);
            //........Download img
            try {
                URL url = new URL(st);
                BufferedImage saveImg = ImageIO.read(url);
                ImageIO.write(saveImg,"jpg",new File("googleImg"+i+".jpg"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
