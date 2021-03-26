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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class ProchainPromotionController implements Initializable {
    ServicePromotion srPro;
    @FXML
    private Button btnpromoAnnee;

    @FXML
    private TextField recherchePromo;
    
    @FXML
    private TableView<Promotion> prochainePromo;

    @FXML
    private TableColumn<Promotion, String> clPromotion;

    @FXML
    private TableColumn<Promotion, String> clRaison;

    @FXML
    private TableColumn<Promotion, String> clDate;

    @FXML
    private TableColumn<Promotion, String> clApprenant;

    @FXML
    void btnRecherchePromo(MouseEvent event) {

    }

    @FXML
    void fctRecherchePromo(MouseEvent event) {
        miseAjourRechercehPromo();

    }
    
     @FXML
    void actualisePromo(MouseEvent event) {
        miseAjourPromo();

    }

    @FXML
    void promoAnnee(MouseEvent event) {
        miseAjourPromoAnne();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        miseAjourPromo();
       
    }    
    
    public void initialiserRecherchePromotion(ServicePromotion srPro){
        this.srPro=  srPro;
        String promotion = recherchePromo.getText();
       
        
       prochainePromo.setItems(srPro.recherchePromotion(promotion));
  
    }
    
    /**
     * affiche les promo par defaut
     * @param srPro 
     */
    public void initialiserPromotion(ServicePromotion srPro){
        this.srPro=  srPro;

       prochainePromo.setItems(srPro.initPromotion());
  
    }
    /**
     * les promo de l'annee
     * @param srPro 
     */
        public void initialiserPromotionAnne(ServicePromotion srPro){
        this.srPro=  srPro;

       prochainePromo.setItems(srPro.promotionAnnee());
  
    }
    
    public void miseAjourRechercehPromo(){
    
        clPromotion.setCellValueFactory(cellData -> cellData.getValue().promotion_property());
       
        clDate.setCellValueFactory(cellData -> cellData.getValue().date_property());
        clRaison.setCellValueFactory(cellData -> cellData.getValue().raison_property());
        clApprenant.setCellValueFactory(cellData -> cellData.getValue().nomApprenant_property());
        initialiserRecherchePromotion(new ServicePromotion());
    }
    
    /**
     * initialiser la promo
     */
      public void miseAjourPromo(){
    
        clPromotion.setCellValueFactory(cellData -> cellData.getValue().promotion_property());
       
        clDate.setCellValueFactory(cellData -> cellData.getValue().date_property());
        clRaison.setCellValueFactory(cellData -> cellData.getValue().raison_property());
        clApprenant.setCellValueFactory(cellData -> cellData.getValue().nomApprenant_property());
        initialiserPromotion(new ServicePromotion());
    }
      
        public void miseAjourPromoAnne(){
    
        clPromotion.setCellValueFactory(cellData -> cellData.getValue().promotion_property());
       
        clDate.setCellValueFactory(cellData -> cellData.getValue().date_property());
        clRaison.setCellValueFactory(cellData -> cellData.getValue().raison_property());
        clApprenant.setCellValueFactory(cellData -> cellData.getValue().nomApprenant_property());
        initialiserPromotionAnne(new ServicePromotion());
    }
    
}
