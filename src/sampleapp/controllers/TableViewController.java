package sampleapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sampleapp.model.Post;
import sampleapp.service.operaterFb.FaceBookPagePostAutomate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TableViewController {
    public AnchorPane anpDashContext;

    public Text txtDate;
    public TextField txtSearch;
    public ImageView imgFullScreen;


    Stage stage;
    public TableView<Post> tblPostTableView;

    public TableColumn colCount;
    public TableColumn colPostDataTime;
    public TableColumn colPostBy;
    public TableColumn colMassage;
    public TextField txtUrl;

    Set<Post> posts;

    public void initialize(){
        setDate();
        loadTable("");

        colCount.setCellValueFactory(new PropertyValueFactory<>("no"));
        colPostDataTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPostBy.setCellValueFactory(new PropertyValueFactory<>("post_by"));
        colMassage.setCellValueFactory(new PropertyValueFactory<>("massage"));

        tblPostTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                try {
                    loadWindowURL(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadTable(newValue);
        });

        //....................fullScreen..........................
        imgFullScreen.setOnMouseClicked((e)->{
            onImgFullorExit();
        });
    }

    private void setDate() {
        txtDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss").format(LocalDateTime.now()));
    }

    private void loadWindowURL(Post newPost) throws IOException {

        if (newPost.getImgURL().size()==0){
            new Alert(Alert.AlertType.INFORMATION, "img files not available this........").show();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/UrlView.fxml"));
        Parent parent = fxmlLoader.load();
        UrlViewController controller = fxmlLoader.getController();
        controller.dataTransfer(newPost);
        Image image= new Image("assets/icon.png");
        if (stage == null){
            stage = new Stage();
        }else {
        }
        stage.getIcons().add(image);
        stage.setTitle("Post_URLs");
        stage.setScene(new Scene(parent));
        stage.show();
    }


    private void loadTable(String txt) {
        posts = FaceBookPagePostAutomate.getLoadPost(txt);
        ObservableList<Post> list = FXCollections.observableArrayList();
        for (Post p: posts ) {
            list.add(p);
        }
        tblPostTableView.setItems(list);
    }

    public void onScrap(ActionEvent actionEvent) {
        String fbUrl = txtUrl.getText().toLowerCase();
        if (fbUrl.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Here FB Page Url.....").show();
            return;
        }else if (!fbUrl.contains("www.facebook.com")){
            new Alert(Alert.AlertType.INFORMATION, "This is Not FB page Check Your Url.....").show();
            return;
        }
        txtUrl.setEditable(false);
        try {
            if (FaceBookPagePostAutomate.newPostSave(posts,fbUrl)){
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            loadTable("");
            txtUrl.clear();
            txtUrl.setEditable(true);
            System.gc();
        }
    }

    public void onImgFullorExit() {
        Stage stage = (Stage) anpDashContext.getScene().getWindow();
        if (!stage.isFullScreen()){
            stage.setFullScreen(true);
        }else {
            stage.setFullScreen(false);
        }
    }
}
/* E:\pushpakumara\Wixis\SeleniumPro\src\sampleapp\test.html */
