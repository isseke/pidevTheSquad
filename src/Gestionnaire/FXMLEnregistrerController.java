/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Services.ServiceEvenement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLEnregistrerController implements Initializable {
        @FXML
    private TextField etheme;

    @FXML
    private TextField elien;

    @FXML
    private TextField epresentateur;

    @FXML
    private Button btnenregistrer;

    @FXML
    private Button btnannuler;

    @FXML
    private DatePicker edate;

    private FileChooser fileChooser = new FileChooser();

    @FXML
    void bt(ActionEvent event) {

    }

    @FXML
    void fAnnuler(MouseEvent event) {
        Stage stage = (Stage)btnannuler.getScene().getWindow();
        stage.close();

    }

    @FXML
    private Button parcourir;
    @FXML
    private TextField tfParcourir;

    @FXML
    private ImageView imageView;

    private Image image;
    private FileInputStream fileImageInputStream;
    private  File file;


    @FXML
    void choisirFichier(MouseEvent event) {

         file = fileChooser.showOpenDialog(parcourir.getScene().getWindow());
        tfParcourir.setText(file.getAbsolutePath());
        try {
            fileImageInputStream = new FileInputStream(file);

            image = new Image(file.toURI().toString());

            imageView.setImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fEnregistrer(MouseEvent event) {
        String lien = elien.getText();
        String theme = etheme.getText();
        String presentateur = epresentateur.getText();
        String date = edate.getValue().toString();

        try {
            fileImageInputStream = new FileInputStream(file);

            image = new Image(file.toURI().toString());

            imageView.setImage(image);


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(date);
        
        Evenement evt = new Evenement(lien, theme, presentateur, date,fileImageInputStream);
        
        ServiceEvenement service = new ServiceEvenement();
        try{
            service.ajouter(evt);
            //JDialog jDialog = new J

        }

        catch (Exception e){
            System.out.println(e.getMessage());

        }

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

}
