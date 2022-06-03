package sampleapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class Post {
    private int no;
    private String time;
    private String post_by;
    private String massage;
    private ArrayList<String> imgURL;

    public Post() {
    }

    public Post(String time, String post_by, String massage, ArrayList<String> imgURL) {
        this.setTime(time);
        this.setPost_by(post_by);
        this.setMassage(massage);
        this.setImgURL(imgURL);
    }

    public Post(int i, String time, String post_by, String massage, ArrayList<String> imgURL) {
        this.setNo(i);
        this.setTime(time);
        this.setPost_by(post_by);
        this.setMassage(massage);
        this.setImgURL(imgURL);
    }

    @Override
    public String toString() {
        return "Post{" +
                "time='" + time + '\'' +
                ", post_by='" + post_by + '\'' +
                ", massage='" + massage + '\'' +
                ", imgURL=" + imgURL +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getPost_by().equals(post.getPost_by()) && getMassage().equals(post.getMassage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPost_by(), getMassage());
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
