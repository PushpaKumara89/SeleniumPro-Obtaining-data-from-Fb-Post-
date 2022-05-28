package sampleapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FaceBookPagePostAutomate {
    private Set<Post> previousPost =null;

    public FaceBookPagePostAutomate() {
        loadPreviousPost();
    }

    private void loadPreviousPost() {
        try {
            previousPost = new LinkedHashSet<>();
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("select * from post");
            ResultSet rst = stm.executeQuery();
            while (rst.next()){
                previousPost.add(new Post(rst.getString(2),rst.getString(3)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Set<Post> getLoadPost() {
        return previousPost;
    }
    //-----------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter here url : ");

        Set<Post> loadedPost = new FaceBookPagePostAutomate().getLoadPost();

        By x_massages = By.xpath("//div[@data-ad-preview='message']");
        By x_imgUrl = By.xpath("//div[@class='do00u71z ni8dbmo4 stjgntxs l9j0dhe7']/div");
        By x_timeDate = By.xpath("//span[@class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql lr9zc1uh a8c37x1j fe6kdd0r mau55g9w c8b282yb keod5gw0 nxhoafnm aigsh9s9 d9wwppkn mdeji52x e9vueds3 j5wam9gi b1v8xokw m9osqain hzawbc8m']/span");

        System.setProperty("webdriver.chrome.driver", "E:\\pushpakumara\\Wixis\\SeleniumPro\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions().addArguments("--headless");//Chrome browser hide ..................
        WebDriver driver =new ChromeDriver(options);

        String url = input.nextLine();
        driver.get(url);//.....................................https://www.facebook.com/augustorycorp

        LinkedList<Post> posts =new LinkedList<>();

        Thread.sleep(5000);
        List<WebElement> text = driver.findElements(x_massages);
        List<WebElement> imgUrl = driver.findElements(x_imgUrl);
        List<WebElement> date = driver.findElements(x_timeDate);
        Thread.sleep(500);


        if (text.size()!=imgUrl.size())return;

        for (int i = 0; i < imgUrl.size(); i++) {
            List<WebElement> img = imgUrl.get(i).findElements(By.tagName("img"));
            String[] imgs = new String[img.size()];
            for (int j = 0; j < img.size(); j++) {
                imgs[j]=img.get(j).getAttribute("src");
            }


            if (loadedPost.add(new Post(date.get(i).getText(),text.get(i).getText(),imgs))){
                posts.add(new Post(date.get(i).getText(),text.get(i).getText(),imgs));
            }

        }
        for (Post p: posts) {
            String[] time = p.getTime().split("\n");
            System.out.println(p.getTime());
            String postTime = DataAndTime.getDateFormat(time[0]);
            try {
                PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO post values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stm1.setObject(1,null);
                stm1.setObject(2,postTime);
                stm1.setObject(3,p.getMassage());
                Thread.sleep(100);
                boolean isSave = stm1.executeUpdate() > 0;
                if (isSave) System.out.println("success");
                ResultSet key = stm1.getGeneratedKeys();
                if (key.next()){
                    int anInt = key.getInt(1);
                    System.out.println(anInt);
                    addPost_url(anInt,p.getImgURL());

                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        driver.quit();
    }

    private static void addPost_url(int anInt, String[] imgURL) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < imgURL.length; i++) {
            PreparedStatement stm2 = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO post_url VALUES(?,?,?)");
            stm2.setObject(1,null);
            stm2.setObject(2,anInt);
            stm2.setObject(3,imgURL[i]);
            System.out.println(stm2.executeUpdate()>0);
        }
    }
}
