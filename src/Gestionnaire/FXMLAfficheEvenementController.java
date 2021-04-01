/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import com.sun.javafx.application.HostServicesDelegate;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLAfficheEvenementController implements Initializable {
    
    
    @FXML
    private Label aTheme;
    @FXML
    private ImageView aImage;
    @FXML
    private Hyperlink hyperLien;

  

    public ImageView getaImage() {
        return aImage;
    }

    public void setaImage(Image mage) {
        this.aImage.setImage(mage);
    }
    
    

    @FXML
    private Label aDate;

    @FXML
    private Label aPresentateur;

    @FXML
    private Label aLen;

    public Label getaTheme() {
        return aTheme;
    }

    public void setHyperLien(String hyperLien) {
        //this.hyperLien = new Hyperlink(hyperLien);
        this.hyperLien.setText(hyperLien);
    }
    
    
    

    public void setaTheme(String aTheme) {
        this.aTheme.setText(aTheme);
    }

    public Label getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate.setText(aDate);
    }

    public Label getaPresentateur() {
        return aPresentateur;
    }

    public void setaPresentateur(String aPresentateur) {
        this.aPresentateur.setText(aPresentateur);
    }

    public Label getaLen() {
        return aLen;
    }

    public void setaLen(String aLen) {
        this.aLen.setText(aLen);
    }
      @FXML
    void ouvrirFenetre(MouseEvent event) {
        
        try {
            Desktop.getDesktop().browse(new URL(hyperLien.getText()).toURI());
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLAfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(FXMLAfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
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
