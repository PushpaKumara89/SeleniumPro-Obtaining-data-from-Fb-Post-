package sampleapp.dto;

import java.util.ArrayList;
import java.util.Objects;

public class PostDTO {
    private int no;
    private String time;
    private String post_by;
    private String massage;
    private ArrayList<String> imgURL;

    public PostDTO() {
    }

    public PostDTO(String time, String post_by, String massage, ArrayList<String> imgURL) {
        this.setTime(time);
        this.setPost_by(post_by);
        this.setMassage(massage);
        this.setImgURL(imgURL);
    }

    public PostDTO(int i, String time, String post_by, String massage, ArrayList<String> imgURL) {
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
        if (!(o instanceof PostDTO)) return false;
        PostDTO postDTO = (PostDTO) o;
        return getPost_by().equals(postDTO.getPost_by()) && getMassage().equals(postDTO.getMassage());
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
