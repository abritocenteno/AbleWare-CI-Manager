/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import java.awt.geom.Rectangle2D;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author abel
 */
public class Voorraadsysteem extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        Parent root = FXMLLoader.load(getClass().getResource("MainWindowFXML.fxml"));
        //  stage.getIcons().add(new Image("file:Zlogo.png"));
        stage.setTitle("CaribIntertrans | AbleWare");
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/voorraadsysteem/voorraadsysteem.css");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
