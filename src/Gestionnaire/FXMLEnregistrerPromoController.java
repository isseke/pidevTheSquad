/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Promotion;
import Services.ServicePromotion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLEnregistrerPromoController implements Initializable {
    
    @FXML
    private ComboBox<?> eprofesseur;

    @FXML
    private ComboBox<?> eapprenant;

    @FXML
    private TextField epromotion;

    @FXML
    private TextField eraison;

    @FXML
    private DatePicker edatepro;

    @FXML
    private Button btn_enregistrer_pro;

    @FXML
    private Button annuler_enre_promo;

    @FXML
    void annuler_enreg(MouseEvent event) {
        
        Stage stage = (Stage) annuler_enre_promo.getScene().getWindow();
        stage.close();

    }

    @FXML
    void enregistrer_promo(MouseEvent event) {
        String promotion = epromotion.getText();
        String raison = eraison.getText();
        String date = edatepro.getValue().toString();
        String aprenant = eapprenant.getValue().toString();
        String prof = eprofesseur.getValue().toString();
        
        String [] profSplit = prof.split("  ");
        String [] apprenantSplet = aprenant.split("  ");
        String profNom = profSplit[0];
        String profprenNom = profSplit[1];
        String apprenantNom = apprenantSplet[0];
        String apprenantpreNom = apprenantSplet[1];
        
        System.out.println(profNom+"\n");
        System.out.println(profprenNom+"\n");
        System.out.println(apprenantNom+"\n");
        System.out.println(apprenantpreNom+"\n");
        
        Promotion p = new Promotion(promotion, date, raison, apprenantNom, apprenantpreNom, profNom, profprenNom);
        
        ServicePromotion service = new ServicePromotion();
        
        try{
             service.ajouter(p);
            //JDialog jDialog = new J

        }

        catch (Exception e){
            System.out.println(e.getMessage());

        }


    }

    public void setEprofesseur(ObservableList eprofesseur) {
        this.eprofesseur.setItems(eprofesseur);
    }

    public void setEapprenant(ObservableList eapprenant) {
        this.eapprenant.setItems(eapprenant);
    }

    public void setEpromotion(TextField epromotion) {
        this.epromotion = epromotion;
    }

    public void setEraison(TextField eraison) {
        this.eraison = eraison;
    }

    public void setEdatepro(DatePicker edatepro) {
        this.edatepro = edatepro;
    }

    public void setBtn_enregistrer_pro(Button btn_enregistrer_pro) {
        this.btn_enregistrer_pro = btn_enregistrer_pro;
    }

    public void setAnnuler_enre_promo(Button annuler_enre_promo) {
        this.annuler_enre_promo = annuler_enre_promo;
    }

        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
