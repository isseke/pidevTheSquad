/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevthesquad;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

/**
 *
 * @author gicke
 */
public class PidevTheSquad extends Application{

  

    @Override
    public void start(Stage stage) throws Exception {

        // URL url = Paths.get("./src/iconspicture/img.png").toUri().toURL();
        // Parent root = FXMLLoader.load(url);



        Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));

        Image image=new Image(getClass().getResourceAsStream("/iconspicture/img.png"));
        stage.getIcons().add(image);

        Scene scene = new Scene(root);
        stage.setTitle("Polyways Learning ");
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);


    }
    
    
}
