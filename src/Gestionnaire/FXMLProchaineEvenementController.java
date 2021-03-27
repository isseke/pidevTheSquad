/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.Evenement;
import Services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLProchaineEvenementController extends AnchorPane implements Initializable {
   @FXML
   private TableView<Evenement> prochainEvnt;

    @FXML
    private TableColumn<Evenement, String> ProchainThemeEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainPresentateurEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainDateEvent;

    @FXML
    private TableColumn<Evenement, String> ProchainLienEvent;
     @FXML
    private Button prEvenement;

    @FXML
    private DatePicker dateRechercheEvenement;

    @FXML
    private Button seEvenement;

    @FXML
    private Button btnRecherche;
        @FXML
    private Text labelevt;

    @FXML
    void labelActualiserEvent(MouseEvent event) {
         miseAjourProchainEvent();
    }

    @FXML
    void prochainEvenements(MouseEvent event) {
        miseAjourProchainEvents();

    }

    @FXML
    void rechercheEvenement(MouseEvent event) {
         miseAjourRechercheEvents();
    }

    @FXML
    void semaineEvenement(MouseEvent event) {
        
    }


    @FXML
    ServiceEvenement srvEvt;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
         miseAjourProchainEvent();
        
    }    
    
    public void initialiserProchainEvenement(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
       prochainEvnt.setItems(srvEvt.prochainEvenement());
  
    }
    
    public void initialiserProchainEvenements(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
       prochainEvnt.setItems(srvEvt.prochainEvenements());
  
    }
    
    public void initialiserRechercheEvenement(ServiceEvenement srvEvt){
        this.srvEvt = srvEvt;
        String date_recherche = String.valueOf(dateRechercheEvenement.getValue());
       prochainEvnt.setItems(srvEvt.rechercheEvenements(date_recherche));
  
    }
    
    public void miseAjourProchainEvent(){

        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserProchainEvenement(new ServiceEvenement());
    }
    public void miseAjourProchainEvents(){
        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserProchainEvenements(new ServiceEvenement());
    }
        public void miseAjourRechercheEvents(){
        ProchainThemeEvent.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        ProchainDateEvent.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        ProchainPresentateurEvent.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        
        ProchainLienEvent.setCellValueFactory(cellData -> cellData.getValue().LienProperty());
        initialiserRechercheEvenement(new ServiceEvenement());
    }

    public void pEvenement(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/FXMLProchainEvenement.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
