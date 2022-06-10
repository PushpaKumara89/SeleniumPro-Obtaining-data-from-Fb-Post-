package sampleapp.service.operaterFb;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sampleapp.dto.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class FB {
    //-------------------------------declared type1 elements------------------------------------------------------
    private static final By x_massages_type1 = By.xpath("//div[@data-ad-preview='message']");
    private static final By x_imgUrl_type1 = By.xpath("//div[@class='do00u71z ni8dbmo4 stjgntxs l9j0dhe7']/div");
    private static final By x_timeDate_type1 = By.xpath("//span[@class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql lr9zc1uh a8c37x1j fe6kdd0r mau55g9w c8b282yb keod5gw0 nxhoafnm aigsh9s9 d9wwppkn mdeji52x e9vueds3 j5wam9gi b1v8xokw m9osqain hzawbc8m']/span/span/span/a/span");
    private static final By x_videoUrl1 = By.xpath("//div[@class='k4urcfbm hwddc3l5 datstx6m']");
    private static final By x_see_more_type1 = By.xpath("//div[@class='cxmmr5t8 oygrvhab hcukyx3x c1et5uql o9v6fnle ii04i59q']/div/div[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gpro0wi8 oo9gr5id lrazzd5p']");


    //-------------------------------declared type2 elements------------------------------------------------------
    private static final By x_massages_type2 = By.xpath("//div[@class='_5pbx userContent _3576']");
    private static final By x_imgUrl_type2 = By.xpath("//div[@class='mtm']/div");
    private static final By x_timeDate_type2 = By.xpath("//div[@class='_5pcp _5lel _2jyu _232_']/span[@class='z_c3pyo1brp']");
    private static final By x_videoUrl2 = By.xpath("//div[@class='mtm']/div");
    private static final By x_see_more_type2 = By.xpath("//a[@class='see_more_link']");

    //-------------------------------execute selected page type---------------------------------------------------
    public static ArrayList<PostDTO> getWebElements(WebDriver driver) {

        WebElementsFB elementsT1 = new WebElementsFB(driver.getTitle(),
                driver.findElements(x_massages_type1),
                driver.findElements(x_imgUrl_type1),
                driver.findElements(x_timeDate_type1)
                );
        WebElementsFB elementsT2 = new WebElementsFB(driver.getTitle(),
                driver.findElements(x_massages_type2),
                driver.findElements(x_imgUrl_type2),
                driver.findElements(x_timeDate_type2)
        );
        ArrayList<PostDTO> postDTOS =null;
        if (elementsT1.is_verified()){
            List<WebElement> see_more_type1 = driver.findElements(x_see_more_type1);
            clickSee_More(see_more_type1,driver);
            postDTOS = getList(elementsT1,driver.getTitle());

        }if(elementsT2.is_verified()){
            List<WebElement> see_more_type2 = driver.findElements(x_see_more_type2);
            clickSee_More(see_more_type2,driver);
            postDTOS = getList(elementsT2,driver.getTitle());
        }
        return postDTOS;
    }

    //----------------------------------------see more button click by javascript---------------------------------------
    private static void clickSee_More(List<WebElement> elements, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < elements.size(); i++) {
            js.executeScript("arguments[0].click();",elements.get(i));
        }
    }

    //---------------------------------get Cra
    private static ArrayList<PostDTO> getList(WebElementsFB element, String title) {
        int elementsSize = 0;
        elementsSize = Math.min(element.getText().size(), 5);
        /*
        elementsSize =element.getText().size()>5 ? 5:element.getText().size();

        if (element.getText().size()>5){
            elementsSize=5;
        }else {
            elementsSize=element.getText().size();
        }*/
        ArrayList<PostDTO> postDTOS = new ArrayList<>();
        for (int i = 0; i < elementsSize; i++) {
            List<WebElement> img = element.getImgUrl().get(i).findElements(By.tagName("img"));
            ArrayList<String> imgs = new ArrayList<>();
            for (int j = 0; j < img.size(); j++) {
                imgs.add(img.get(j).getAttribute("src"));
            }
            String postTime = DataAndTimeFB.getDateFormat(element.getDate().get(i).getText());
            postDTOS.add(new PostDTO(postTime,title, element.getText().get(i).getText(),imgs));
        }
        return postDTOS;
    }
}
