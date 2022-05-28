package sampleapp;

import java.util.Arrays;
import java.util.Objects;

public class Post {
    private String time;
    private String massage;
    private String[] imgURL;

    public Post() {
    }

    public Post(String time, String massage) {
        this.setTime(time);
        this.setMassage(massage);
    }
    public Post(String time, String massage, String[] imgURL) {
        this.setTime(time);
        this.setMassage(massage);
        this.setImgURL(imgURL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return o.hashCode()==this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMassage());
    }

    @Override
    public String toString() {
        return "Post{" +
                "time='" + time + '\'' +
                ", massage='" + massage + '\'' +
                ", imgURL=" + Arrays.toString(imgURL) +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String[] getImgURL() {
        return imgURL;
    }

    public void setImgURL(String[] imgURL) {
        this.imgURL = imgURL;
    }
}
