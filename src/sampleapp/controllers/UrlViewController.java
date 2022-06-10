package sampleapp.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sampleapp.dto.PostDTO;


import java.util.ArrayList;

public class UrlViewController {
    public Text txtPostBy;
    public ImageView imgPostImages;
    public TextArea txtPostContent;
    public Label txtCountImages;

    public Button btnNext;
    public Button btnPrevious;

    Image img = null;
    ArrayList<String> imgURL;
    int currentViewImgIndex = 0;

    public void initialize(){
        btnNext.setDisable(true);
        btnPrevious.setDisable(true);
    }

    public void dataTransfer(PostDTO newPostDTO) {
        txtPostBy.setText(newPostDTO.getPost_by()+" page");
        txtPostContent.setText(newPostDTO.getMassage());
        imgURL= newPostDTO.getImgURL();
        if (imgURL.size()>1){
            btnPrevious.setDisable(false);
            btnNext.setDisable(false);
        }
        if(imgURL.size()>0){
            img=new Image(imgURL.get(currentViewImgIndex));
            imgPostImages.setImage(img);
        }

        txtCountImages.setText(String.valueOf(currentViewImgIndex+1));
    }


    public void btnNextOnAction(ActionEvent actionEvent) {
        if ((imgURL.size()-1)>currentViewImgIndex){
            img = new Image(imgURL.get(++currentViewImgIndex));
            imgPostImages.setImage(img);
            txtCountImages.setText(String.valueOf(currentViewImgIndex+1));
        }
    }

    public void btnPreviousOnAction(ActionEvent actionEvent) {
        if (0<currentViewImgIndex){
            img = new Image(imgURL.get(--currentViewImgIndex));
            imgPostImages.setImage(img);
            txtCountImages.setText(String.valueOf(currentViewImgIndex+1));
        }
    }
}
