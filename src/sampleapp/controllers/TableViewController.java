package sampleapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sampleapp.dto.PostDTO;
import sampleapp.service.MyTread;
import sampleapp.service.operaterFb.FaceBookPagePostAutomate;
import sampleapp.service.quartz.JobObject;
import sampleapp.views.ViewFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TableViewController {
    public Text txtSqu;
    int from = 0, to =0;
    int itemPerPage=8;

    public Pagination pngPagination;
    SimpleDateFormat format;
    String timeString;

    public AnchorPane anpDashContext;

    public Text txtDate;
    public TextField txtSearch;
    public ImageView imgFullScreen;


    Stage stage;
    public TableView<PostDTO> tblPostTableView;

    public TableColumn<PostDTO,Integer> colCount;
    public TableColumn<PostDTO,String> colPostDataTime;
    public TableColumn<PostDTO,String> colPostBy;
    public TableColumn<PostDTO,String> colMassage;
    public TextField txtUrl;

    String txt="";

    public void initialize(){
        setDate();
        loadTable();

        colCount.setCellValueFactory(new PropertyValueFactory<>("no"));
        colPostDataTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPostBy.setCellValueFactory(new PropertyValueFactory<>("post_by"));
        colMassage.setCellValueFactory(new PropertyValueFactory<>("massage"));


        tblPostTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                try {
                    loadWindowURL(newValue);
                } catch (IOException e) {e.printStackTrace();}
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            txt=newValue;
            loadTable();
        });
    }

    private void setDate() {
        format=new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss a");

        MyTread timeCounter = new MyTread(){
            @Override
            public void run() {
                while (!getExit()){
                    timeString = format.format(Calendar.getInstance().getTime());
                    txtDate.setText(timeString);
                    txtSqu.setText(JobObject.siqS);
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                }
            }
        };

        timeCounter.start();
    }

    private void loadWindowURL(PostDTO newPostDTO) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.getInstance().getUrl("UrlView"));
        Parent parent = fxmlLoader.load();
        UrlViewController controller = fxmlLoader.getController();
        controller.dataTransfer(newPostDTO);
        Image image= new Image("assets/icon.png");
        if (stage == null){
            stage = new Stage();
        }
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.setTitle("Post_URLs");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private Node createTable(int pageIndex){
        from = pageIndex * itemPerPage;
        to = itemPerPage;
        try {
            tblPostTableView.setItems(FXCollections.observableArrayList(
                    FaceBookPagePostAutomate.getLoadPost(txt, from,to)
            ));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return tblPostTableView;
    }

    private void loadTable() {
        int pageCount=0, row =0;
        try {
            row = FaceBookPagePostAutomate.getPostCount(txt);
        } catch (SQLException | ClassNotFoundException throwables) {throwables.printStackTrace();}
        if(row%itemPerPage==0){
            pageCount = row/itemPerPage;
        }else {
            pageCount = (row/itemPerPage)+1;
        }

        pngPagination.setPageCount(pageCount);
        pngPagination.setPageFactory(this::createTable);
    }

    public void onScrap(ActionEvent actionEvent) {
        String fbUrl = txtUrl.getText().toLowerCase();
       /* if (fbUrl.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Here FB Page Url.....").show();
            return;
        }else if (!fbUrl.contains("www.facebook.com")){
            new Alert(Alert.AlertType.INFORMATION, "This is Not FB page Check Your Url.....").show();
            return;
        }*/
        txtUrl.setEditable(false);
        try {
            if (FaceBookPagePostAutomate.newPostSave(fbUrl)){
                new Alert(Alert.AlertType.INFORMATION,"Complete").show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            loadTable();
            txtUrl.clear();
            txtUrl.setEditable(true);
            System.gc();
        }
    }

    public void onImgFullorExit() {
        Stage stage = (Stage) anpDashContext.getScene().getWindow();
        if (!stage.isFullScreen()){
            stage.setFullScreen(true);
            Image img = new Image("assets/exitFullS.png");
            imgFullScreen.setImage(img);
        }else {
            stage.setFullScreen(false);
            Image img = new Image("assets/fullS.png");
            imgFullScreen.setImage(img);
        }
    }

    public void onSetTime(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"ok").show();
    }
}
/* E:\pushpakumara\Wixis\SeleniumPro\src\sampleapp\test.html */
