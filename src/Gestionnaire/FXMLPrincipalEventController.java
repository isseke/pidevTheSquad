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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gicke
 */
public class FXMLPrincipalEventController implements Initializable {
     @FXML
    private TableView<Evenement> EvenementTable;
      @FXML
    private TableColumn<Evenement, Integer> fId;

    @FXML
    private TableColumn<Evenement, String> fTheme;

    @FXML
    private TableColumn<Evenement, String> fPrensentation;

    @FXML
    private TableColumn<Evenement, String> fDate;

    @FXML
    private TableColumn<Evenement, String> fLien;

    @FXML
    private Button btnAjouter;
    
   // ObservableList<Evenement>  Listevenement = FXCollections.observableArrayList();
    
    ServiceEvenement evt;
    
    @FXML
    void rafraichir(MouseEvent event) {
        miseAjour();
    }

    @FXML
    private Button testpro;

    @FXML
    void homeClick(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window=(Stage) testpro.getScene().getWindow();

        window.setScene(new Scene(root,1370,700));

    }
     @FXML

    void modifierEven(MouseEvent event) {
            
        try {
            
             ObservableList<Evenement> selection = EvenementTable.getSelectionModel().getSelectedItems();
         
             Stage stage = new Stage();
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/FXMLModifier.fxml"));
            
             Parent root = loader.load();
             
             FXMLModifierController mf = loader.getController();
             
             
             mf.setIl_label(String.valueOf(selection.get(0).getId_evenement()));
             
             mf.setMtheme(selection.get(0).getTheme());
             
             mf.setMlien(selection.get(0).getLien());
             
             mf.setMdate(selection.get(0).getDate());
             
             mf.setMpresentateur(selection.get(0).getPresentateur());
             
             //mf.setM_id_even((selection.get(0).getId_evenement()));
            
             Scene sc = new Scene(root);
             //mc.setM_id_even(new Label("Idende l'Evenment"+selection.get(0).getId_evenement()));
             
             System.out.println(selection.get(0).getId_evenement());
             
             
             
             stage.setScene(sc);
             
             stage.show();
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLPrincipalEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    void suppimerEvent(MouseEvent event) {
        ServiceEvenement sv = new ServiceEvenement();
        
        try {
                    ObservableList<Evenement> selection = EvenementTable.getSelectionModel().getSelectedItems();
                    
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
    @FXML
    void Ajouter(MouseEvent event) {
       
         try {
             Stage stage = new Stage();
             
             Parent root = FXMLLoader.load(getClass().getResource("/Vue/FXMLEnregistrer.fxml"));
             
             Scene sc = new Scene(root);
             stage.setScene(sc);
             
             stage.show();
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLPrincipalEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
        
       
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        miseAjour();
        
        
    } 
    
    public void setListServ(ServiceEvenement evt) {
        this.evt = evt;

        // Add observable list data to the table
        EvenementTable.setItems(evt.Consulter());
    }
    public void miseAjour(){
        fId.setCellValueFactory(cellData -> cellData.getValue().id_even_Property().asObject());
        fTheme.setCellValueFactory(cellData -> cellData.getValue().ThemeProperty());
        fPrensentation.setCellValueFactory(cellData -> cellData.getValue().PresentateurProperty());
        fDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        fLien.setCellValueFactory(cellData -> (ObservableValue<String>) cellData.getValue().LienProperty());
       setListServ(new ServiceEvenement());
       
    }
  
    
    
}
