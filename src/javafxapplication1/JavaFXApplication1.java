/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Liron
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Catching exceptions is for communists
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Black Cat Jack");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Black_Cat.gif")));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
        stage.setResizable(false);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
