/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Services.ServiceEvenement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLEnregistrerEventController implements Initializable {
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

    @FXML
    void bt(ActionEvent event) {

    }

    @FXML
    void fAnnuler(MouseEvent event) {

    }

    @FXML
    void fEnregistrer(MouseEvent event) {
        String lien = elien.getText();
        String theme = etheme.getText();
        String presentateur = epresentateur.getText();
        String date = edate.getValue().toString();
        
        System.out.println(date);
        
        Evenement evt = new Evenement(lien, theme, presentateur, date);
        
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
