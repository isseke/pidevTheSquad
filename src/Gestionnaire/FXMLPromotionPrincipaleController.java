/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Modele.Promotion;
import Services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLPromotionPrincipaleController implements Initializable {
       @FXML
    private TableView<Promotion> promotion_table;
    
     @FXML
    private TableColumn<Promotion, Integer> id_promo;

    @FXML
    private TableColumn<Promotion, String> promotion;

    @FXML
    private TableColumn<Promotion, String> apprenant;

    @FXML
    private TableColumn<Promotion, String> professeur;

    @FXML
    private TableColumn<Promotion, String> raison;

    @FXML
    private TableColumn<Promotion, String> date;

    @FXML
    private Button btn_ajoute_prmotion;

    @FXML
    private Button btn_rafraichir_promotion;

    @FXML
    private Button btn_supprimer_promo;

    @FXML
    private Button btn_modifier_promo;
    
    ServicePromotion pro;

    @FXML
    void AjouterPromotion(MouseEvent event) {
        
         try {
                     
             Stage stage = new Stage();
             
             ServicePromotion s= new ServicePromotion();
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLEnregistrerPromo.fxml"));
            
             Parent root = loader.load();
    
             Scene sc = new Scene(root);
           
             FXMLEnregistrerPromoController prc = loader.getController();
             
             prc.setEprofesseur(s.afficheProff());
             prc.setEapprenant(s.afficheApprenant());
                     
                     
             stage.setScene(sc);
             
             stage.show();
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLPrincipalEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
        

    }

    @FXML
    void RachaichirPromo(MouseEvent event) {
        
        miseAjour();

    }

    @FXML
    void modifier_promo(MouseEvent event) {
        try {
            
             ObservableList<Promotion> selection = promotion_table.getSelectionModel().getSelectedItems();
         
             Stage stage = new Stage();
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/FXMLModifierPromotion.fxml"));
            
             Parent root = loader.load();
           
             ServicePromotion s= new ServicePromotion();
            
             
             FXMLModifierPromotionController mf = loader.getController();    
       
             mf.setMpromotion(selection.get(0).getPromotion());
             mf.setMlabel_promo(String.valueOf(selection.get(0).getId_promotion()));
             mf.setMraison(selection.get(0).getRaison());
             mf.setMapprenant(s.afficheApprenant());
             mf.setMprofesseur(s.afficheProff());
             mf.setMdate(selection.get(0).getDate());
                      
             Scene sc = new Scene(root);
    stage.setScene(sc);
             
             stage.show();
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLPrincipalEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    void supprimer_promo(MouseEvent event) {
        ServicePromotion sv = new ServicePromotion();
        
        try {
                    ObservableList<Promotion> selection = promotion_table.getSelectionModel().getSelectedItems();
                    
                    selection.forEach(e ->
                    
                    {
                        sv.supprimer(e);
                        
                       
                    }
                    );
                    
                    miseAjour();
                    

        } catch (Exception e) {
            System.out.println(e);
            
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        miseAjour();
        
    }    
      public void setListServ(ServicePromotion pro) {
        this.pro = pro;

        // Add observable list data to the table
        promotion_table.setItems(pro.Consulter());
    }
      
      public void miseAjour(){
        id_promo.setCellValueFactory(cellData -> cellData.getValue().id_promotion_property().asObject());
        promotion.setCellValueFactory(cellData -> cellData.getValue().promotion_property());
        professeur.setCellValueFactory(cellData -> cellData.getValue().nomProf_property());
        date.setCellValueFactory(cellData -> cellData.getValue().date_property());
        raison.setCellValueFactory(cellData -> cellData.getValue().raison_property());
        apprenant.setCellValueFactory(cellData -> cellData.getValue().nomApprenant_property());
       setListServ(new ServicePromotion());
      }
    
}
