package sampleapp.service.operaterFb;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sampleapp.db.DBConnection;
import sampleapp.model.Post;

import java.sql.*;
import java.util.*;

public class FaceBookPagePostAutomate {
    private static Set<Post> previousPost =null;

    public static Set<Post> getLoadPost(String txt) {
        previousPost = new LinkedHashSet<>();
        try {
            PreparedStatement pst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM post WHERE post_by LIKE '%"+txt+"%' OR massage LIKE '%"+txt+"%'");
            ResultSet rst = pst.executeQuery();
            for (int i = 0; rst.next(); i++) {
                PreparedStatement pst1 = DBConnection.getInstance().getConnection().prepareStatement("SELECT url FROM post_url WHERE post_id=?");
                pst1.setObject(1,rst.getInt(1));
                ResultSet rst1 = pst1.executeQuery();
                ArrayList<String> list = new ArrayList<>();
                while (rst1.next()){
                    list.add(rst1.getString(1));
                }
                previousPost.add(new Post((i+1),rst.getString(2),rst.getString(3),rst.getString(4),list));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return previousPost;
    }
    //-----------------------------------------------------------------

    public static boolean newPostSave(Set<Post> loadPost,String url) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "E:\\pushpakumara\\Wixis\\SeleniumPro\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions().addArguments("--headless");//Chrome browser hide ..................
        WebDriver driver =new ChromeDriver(options);

        driver.get(url);//.......https://www.facebook.com/aswadduma.............https://www.facebook.com/Sampathbankplc.................https://www.facebook.com/augustorycorp

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 1500);");

        Runnable run = ()->{
            for (int i = 0; i <=100 ; i++) {
                System.out.print("=");
                try {Thread.sleep(65);} catch (InterruptedException e) {}
            }
            System.out.println("Completed");
        };
        Thread t = new Thread(run);t.start();
        Thread.sleep(7000);


        //--------------------------if see more .................................................
        List<WebElement> see_more_type1 = driver.findElements(By.xpath("//div[@class='cxmmr5t8 oygrvhab hcukyx3x c1et5uql o9v6fnle ii04i59q']/div[@style='text-align: start;']/div[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gpro0wi8 oo9gr5id lrazzd5p' and @role='button']"));
        List<WebElement> see_More_type2 = driver.findElements(By.xpath("//a[@class='see_more_link']"));


        if (!see_more_type1.isEmpty()){
            for (int i = 0; i < see_more_type1.size(); i++) {
                try {
                    Thread.sleep(300);
                    see_more_type1.get(i).click();
                    jse.executeScript("window.scrollBy(0, 300);");
                }catch (ElementClickInterceptedException e){
                    System.out.println(e);
                }
            }
        }else if (!see_More_type2.isEmpty()) {
            for (int i = 0; i < see_More_type2.size(); i++) {
                try {
                    Thread.sleep(300);
                    see_More_type2.get(i).click();
                    jse.executeScript("window.scrollBy(0, 500);");
                } catch (ElementClickInterceptedException e) {
                    System.out.println(e);
                }
            }
        }
        //--------------------------if see more .................................................

        LinkedList<Post> posts =new LinkedList<>();
        ArrayList<Post> webElements = FB.getWebElements(driver);

        for (int i = 0; i < webElements.size(); i++) {
            if (loadPost.add(webElements.get(i))){
                posts.add(webElements.get(i));
            }
        }

        for (Post p: posts) {
            System.out.println(p);

            try {
                System.out.println(addPost(p));
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
        /* driver.quit();*/
    }

    private static boolean addPost(Post p) throws SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement stm1 = connection.prepareStatement("INSERT INTO post values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm1.setObject(1, null);
            stm1.setObject(2, p.getTime());
            stm1.setObject(3, p.getPost_by());
            stm1.setObject(4, p.getMassage());
            Thread.sleep(100);
            boolean isSave = stm1.executeUpdate() > 0;
            System.out.println(isSave+"-------------------------");
            if (isSave){
                ResultSet key = stm1.getGeneratedKeys();
                if (key.next()) {
                    int post_id = key.getInt(1);
                    boolean isAdd = addPost_url(post_id, p.getImgURL());
                    if (isAdd) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    private static boolean addPost_url(int post_id, ArrayList<String> imgURL) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < imgURL.size(); i++) {
            PreparedStatement stm2 = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO post_url VALUES(?,?,?)");
            stm2.setObject(1,null);
            stm2.setObject(2,post_id);
            stm2.setObject(3,imgURL.get(i));
            boolean isAdd = stm2.executeUpdate()>0;
            if(!isAdd)return false;
        }
        return true;
    }

}
