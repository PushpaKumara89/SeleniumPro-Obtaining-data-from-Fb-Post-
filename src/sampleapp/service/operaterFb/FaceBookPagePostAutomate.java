package sampleapp.service.operaterFb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.FbPostBo;
import sampleapp.dto.PostDTO;

import java.sql.*;
import java.time.Duration;
import java.util.*;

public class FaceBookPagePostAutomate {
    private static Set<PostDTO> previousPostDTO =null;
    private static FbPostBo bo = (FbPostBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_POSTS);

    public static Set<PostDTO> getLoadPost(String txt, int from, int to) throws SQLException, ClassNotFoundException {
        previousPostDTO=new LinkedHashSet<>();
        ArrayList<PostDTO> allPost = bo.getAllForPaginate(txt,from,to);
        for (int i = 0; i < allPost.size(); i++) {
            PostDTO dto=allPost.get(i);
            dto.setNo(i+1);
            previousPostDTO.add(dto);
        }
        return previousPostDTO;
    }
    public static int getPostCount(String txt) throws SQLException, ClassNotFoundException {
        return bo.getPostCount(txt);
    }
    //------------------------------------------------------------------------------------------------------------------

    public static boolean newPostSave(String url) throws InterruptedException {
        ArrayList<PostDTO> list =null;
        try {
            list = bo.getAllPost("");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Set<PostDTO> loadPostDTO =new HashSet<>();
        for (PostDTO p:list) {
            loadPostDTO.add(p);
        }
        //===============================================================================================================
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions().addArguments("--headless");//Chrome browser hide ..................
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);//.......https://www.facebook.com/aswadduma.............https://www.facebook.com/Sampathbankplc.................https://www.facebook.com/augustorycorp
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 3000);");

        driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState")
                .equals("complete"));//waiting 30s for page rendering...................................................



        Runnable run = ()->{
            for (int i = 0; i <=100 ; i++) {
                System.out.print("=");
                try {Thread.sleep(65);} catch (InterruptedException e) {}
            }
            System.out.println("Completed");
        };

        Thread t = new Thread(run);t.start();

        //--------------------------if see more-------------------------------------------------------------------------
        /*List<WebElement> see_more_type1 = driver.findElements(By.xpath("//div[@class='cxmmr5t8 oygrvhab hcukyx3x c1et5uql o9v6fnle ii04i59q']/div/div[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gpro0wi8 oo9gr5id lrazzd5p']"));
        List<WebElement> see_more_type2 = driver.findElements(By.xpath("//a[@class='see_more_link']"));


        if (!see_more_type1.isEmpty()){
            scrollingDown(see_more_type1,js);
        }else if (!see_more_type2.isEmpty()) {
            scrollingDown(see_more_type2,js);
        }*/

        LinkedList<PostDTO> postDTOS =new LinkedList<>();
        ArrayList<PostDTO> webElements = FB.getWebElements(driver);


        for (int i = 0; i < webElements.size(); i++) {
            if (loadPostDTO.add(webElements.get(i))){
                postDTOS.add(webElements.get(i));
            }
        }
//-----------------------------Duplicate Rejected new Post Added-------------------------
        for (PostDTO p: postDTOS) {
            try {
                System.out.println(bo.savePost(p));
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        Thread.sleep(3000);
        /*driver.quit();*/
        return false;

    }
}
