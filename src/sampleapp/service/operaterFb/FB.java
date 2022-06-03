package sampleapp.service.operaterFb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sampleapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class FB {
    //-------------------------------declared type1 elements------------------------------------------------------
    private static By x_massages_type1 = By.xpath("//div[@data-ad-preview='message']");
    private static By x_imgUrl_type1 = By.xpath("//div[@class='do00u71z ni8dbmo4 stjgntxs l9j0dhe7']/div");
    private static By x_timeDate_type1 = By.xpath("//span[@class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql lr9zc1uh a8c37x1j fe6kdd0r mau55g9w c8b282yb keod5gw0 nxhoafnm aigsh9s9 d9wwppkn mdeji52x e9vueds3 j5wam9gi b1v8xokw m9osqain hzawbc8m']/span/span/span/a/span");
    private static By x_videoUrl1 = By.xpath("//div[@class='k4urcfbm hwddc3l5 datstx6m']");

    //-------------------------------declared type2 elements------------------------------------------------------
    private static By x_massages_type2 = By.xpath("//div[@class='_5pbx userContent _3576']");
    private static By x_imgUrl_type2 = By.xpath("//div[@class='mtm']/div");
    private static By x_timeDate_type2 = By.xpath("//div[@class='_5pcp _5lel _2jyu _232_']/span[@class='z_c3pyo1brp']");
    private static By x_videoUrl2 = By.xpath("//div[@class='mtm']/div");

    //-------------------------------execute selected page type---------------------------------------------------
    public static ArrayList<Post> getWebElements(WebDriver driver) {

        WebElementsFB elementsT1 = new WebElementsFB(driver.getTitle(),
                driver.findElements(x_massages_type1),
                driver.findElements(x_imgUrl_type1),
                driver.findElements(x_timeDate_type1),
                driver.findElements(x_videoUrl1)
                );
        WebElementsFB elementsT2 = new WebElementsFB(driver.getTitle(),
                driver.findElements(x_massages_type2),
                driver.findElements(x_imgUrl_type2),
                driver.findElements(x_timeDate_type2),
                driver.findElements(x_videoUrl2)
        );
        ArrayList<Post> posts=null;
        if (elementsT1.is_verified()){
            posts = getList(elementsT1,driver.getTitle());

        }if(elementsT2.is_verified()){
            posts = getList(elementsT2,driver.getTitle());
        }
        return posts;
    }

    //---------------------------------get Cra
    private static ArrayList<Post> getList(WebElementsFB element, String title) {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < element.getText().size(); i++) {
            List<WebElement> img = element.getImgUrl().get(i).findElements(By.tagName("img"));
            ArrayList<String> imgs = new ArrayList<>();
            for (int j = 0; j < img.size(); j++) {
                imgs.add(img.get(j).getAttribute("src"));
            }
            String postTime = DataAndTimeFB.getDateFormat(element.getDate().get(i).getText());
            posts.add(new Post(postTime,title, element.getText().get(i).getText(),imgs));
        }
        return posts;
    }
}
