package sampleapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import sampleapp.dto.PostDTO;
import sampleapp.dto.UrlDetails;

import java.util.ArrayList;

public class UrlViewController {
    public TableView tblUrl;
    public TableColumn colUrl;
    public Text txtPostBy;

    public void initialize(){
        colUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
    }


    public void dataTransfer(PostDTO newPostDTO) {
        txtPostBy.setText(newPostDTO.getPost_by()+" page");
        ObservableList<UrlDetails> list = FXCollections.observableArrayList();
        ArrayList<String> imgURL = newPostDTO.getImgURL();
        for (String s:imgURL ) {
            list.add(new UrlDetails(s));
        }
        tblUrl.setItems(list);
    }


}
