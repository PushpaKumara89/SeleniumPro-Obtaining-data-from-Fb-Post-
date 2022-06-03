package sampleapp.service.operaterFb;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.FbPostBo;
import sampleapp.dto.PostDTO;

import java.sql.*;
import java.util.*;

public class FaceBookPagePostAutomate {
    private static Set<PostDTO> previousPostDTO =null;
    private static FbPostBo bo = (FbPostBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_POSTS);

    public static Set<PostDTO> getLoadPost(String txt) throws SQLException, ClassNotFoundException {
        previousPostDTO=new LinkedHashSet<>();
        ArrayList<PostDTO> allPost = bo.getAllPost("");
        for (int i = 0; i < allPost.size(); i++) {
            PostDTO dto=allPost.get(i);
            dto.setNo(i+1);
            previousPostDTO.add(dto);
        }
        return previousPostDTO;
    }
    //-----------------------------------------------------------------

    public static boolean newPostSave(Set<PostDTO> loadPostDTO, String url) throws InterruptedException {

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

        LinkedList<PostDTO> postDTOS =new LinkedList<>();
        ArrayList<PostDTO> webElements = FB.getWebElements(driver);

        for (int i = 0; i < webElements.size(); i++) {
            if (loadPostDTO.add(webElements.get(i))){
                postDTOS.add(webElements.get(i));
            }
        }

        for (PostDTO p: postDTOS) {
            System.out.println(p);

            try {
                System.out.println(bo.savePost(p));
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return false;
        /* driver.quit();*/
    }

}
