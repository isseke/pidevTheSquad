/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLModifierPromotionController implements Initializable {
    
        @FXML
    private TextField mpromotion;

    @FXML
    private TextField mraison;

    @FXML
    private ComboBox<?> mprofesseur;

    @FXML
    private ComboBox<?> mapprenant;

    @FXML
    private DatePicker mdate;

    @FXML
    private Button btn_motion_promo;

    @FXML
    private Button annuler_modification_promo;

    @FXML
    private Label mlabel_promo;

    public void setMpromotion(String mpromotion) {
        this.mpromotion.setText(mpromotion);
    }

    public void setMraison(String mraison) {
        this.mraison.setText(mraison);
    }

    public void setMprofesseur(ObservableList mprofesseur) {
        this.mprofesseur.setItems(mprofesseur);
    }

    public void setMapprenant(ObservableList mapprenant) {
        this.mapprenant.setItems(mapprenant);
    }

    public void setMdate(String mdate) {
        this.mdate.setValue(LocalDate.parse(mdate));
    }

    public void setBtn_motion_promo(Button btn_motion_promo) {
        this.btn_motion_promo = btn_motion_promo;
    }

    public void setAnnuler_modification_promo(Button annuler_modification_promo) {
        this.annuler_modification_promo = annuler_modification_promo;
    }

    public void setMlabel_promo(Label mlabel_promo) {
        this.mlabel_promo = mlabel_promo;
    }
    
    
    
    
    

    @FXML
    void annuler_modification_promo(MouseEvent event) {

    }

    @FXML
    void modifier_promo(MouseEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
