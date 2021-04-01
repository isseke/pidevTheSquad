/* USER */




import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
/* public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea tfDate;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> tabTitle;
    @FXML
    private TableColumn<Reclamation, String> tabDate;
    @FXML
    private TableColumn<Reclamation, String> tabEtat;
    @FXML
    private Button EXIT;
    private static int id;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 ObservableList<Reclamation> Reclamation = FXCollections.observableArrayList();
                 ReclamationService ps = new ReclamationService();
         for(Reclamation c: ps.displayReclamation())
             Reclamation.add(c);
         tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         tableReclamation.setItems(Reclamation); }


    public void loadData(){
            ObservableList<Reclamation> Reclamation = FXCollections.observableArrayList();
            ReclamationService ps = new ReclamationService();
         for(Reclamation c: ps.displayReclamation())
             Reclamation.add(c);
         tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         tableReclamation.setItems(Reclamation);
    }

    public void directEXIT(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("BackFXML.fxml"));
            Parent root= loader.load();
           // BackFXMLController r= loader.getController();
            EXIT.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); }
    }


    @FXML
    private void addReclamation(ActionEvent event) {
        String title=tfTitle.getText();
        String date=tfDate.getText();
        int id_apprenant=1;
        Reclamation r= new Reclamation(title,date,id_apprenant);
            ReclamationService ps = new ReclamationService();
            ps.ajouterReclamation(r);
            loadData(); }

    @FXML
    private void modifyReclamation(ActionEvent event) {
            ReclamationService rs = new ReclamationService();
            Reclamation u = new Reclamation();
            u.setTitle(tfTitle.getText());
            u.setDate(tfDate.getText());
            rs.updateReclamation(u,id);
            loadData();
    }

    

    @FXML
    private void deleteReclamation(ActionEvent event) {
        
        ReclamationService ps = new ReclamationService();
        
      if(!tableReclamation.getSelectionModel().getSelectedItems().isEmpty()) {
          ps.supprimerReclamation(tableReclamation.getSelectionModel().getSelectedItems().get(0));
            loadData(); }
        
      else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait(); }
    }

    @FXML
    private void selected(MouseEvent event) {
        Reclamation r = tableReclamation.getSelectionModel().getSelectedItem();
        id=r.getId();
        tfTitle.setText(r.getTitle());
        tfDate.setText(r.getDate()); }
}

*/





