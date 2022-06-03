package sampleapp.entty;

import java.util.ArrayList;

public class PostFB {
    private int id;
    private String time;
    private String post_by;
    private String massage;
    private ArrayList<String> imgURL;

    public PostFB() {
    }

    public PostFB(String time, String post_by, String massage, ArrayList<String> imgURL) {
        this.setTime(time);
        this.setPost_by(post_by);
        this.setMassage(massage);
        this.setImgURL(imgURL);
    }

    public int getNo() {
        return id;
    }

    public void setNo(int no) {
        this.id = no;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPost_by() {
        return post_by;
    }

    public void setPost_by(String post_by) {
        this.post_by = post_by;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public ArrayList<String> getImgURL() {
        return imgURL;
    }

    public void setImgURL(ArrayList<String> imgURL) {
        this.imgURL = imgURL;
    }
}
