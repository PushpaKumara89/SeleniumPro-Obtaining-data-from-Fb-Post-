package sampleapp.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;


public class ViewFactory {
    private static ViewFactory viewFactory;

    private ViewFactory(){}
    public static ViewFactory getInstance(){
        return viewFactory = (viewFactory==null)? new ViewFactory():viewFactory;
    }

    public  URL getUrl(String fileName){
        return getClass().getResource(fileName+".fxml");
    }
    public Parent get(String fileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fileName+".fxml"));
        } catch (RuntimeException | IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
