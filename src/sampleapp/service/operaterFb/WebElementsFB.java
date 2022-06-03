package sampleapp.service.operaterFb;

import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementsFB {
    private String post_by;
    private List<WebElement> text;
    private List<WebElement> imgUrl;
    private List<WebElement> date;
    private List<WebElement> videoUrl;

    public WebElementsFB() {
    }

    public WebElementsFB(String post_by, List<WebElement> text, List<WebElement> imgUrl, List<WebElement> date, List<WebElement> videoUrl) {
        this.post_by = post_by;
        this.text = text;
        this.imgUrl = imgUrl;
        this.date = date;
        this.setVideoUrl(videoUrl);
    }

    public boolean is_verified(){
        if(text.isEmpty() && imgUrl.isEmpty() && date.isEmpty())return false;
        return true;
    }
    public String getPost_by() {
        return post_by;
    }

    public void setPost_by(String post_by) {
        this.post_by = post_by;
    }

    public List<WebElement> getText() {
        return text;
    }

    public void setText(List<WebElement> text) {
        this.text = text;
    }

    public List<WebElement> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<WebElement> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<WebElement> getDate() {
        return date;
    }

    public void setDate(List<WebElement> date) {
        this.date = date;
    }

    public List<WebElement> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(List<WebElement> videoUrl) {
        this.videoUrl = videoUrl;
    }
}
