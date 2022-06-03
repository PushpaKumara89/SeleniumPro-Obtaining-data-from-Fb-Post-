package sampleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sampleapp.views.ViewFactory;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Image image= new Image("assets/icon.png");
        primaryStage.getIcons().add(image);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Scrap");
        primaryStage.setScene(new Scene(ViewFactory.getInstance().get("TableView")));
        primaryStage.show();
    }
}
