/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevthesquad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        //URL url = Paths.get("./src/vue/FXMLTeste.fxml").toUri().toURL();
       // Parent root = FXMLLoader.load(url);

        Parent root = FXMLLoader.load(getClass().getResource("/vue/FXMLTeste.fxml"));
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();
    }
    
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);

    }
    
    
}
