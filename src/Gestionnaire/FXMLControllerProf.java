/* PROFESSEIR */



import Entities.ReclamationProf;
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
import javafx.stage.Stage;


/*public class FXMLControllerProf implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea tfDate;
    @FXML
    private TableView<ReclamationProf> tableReclamationProf;
    @FXML
    private TableColumn<ReclamationProf, String> tabTitle;
    @FXML
    private TableColumn<ReclamationProf, String> tabDate;
    @FXML
    private TableColumn<ReclamationProf, String> tabEtat;
    @FXML
    private Button EXIT;
    private static int id;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
                 ReclamationServiceProf ps = new ReclamationServiceProf();
         for(ReclamationProf c: ps.displayReclamation())
             ReclamationProf.add(c);
         tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         tabDate.setCellValueFactory(new PropertyValueFactory<>("description"));
         tabEtat.setCellValueFactory(new PropertyValueFactory<>("answer"));
         tableReclamationProf.setItems(ReclamationProf);
    }    
    
    public void loadDataProf(){
                         ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
                 ReclamationServiceProf ps = new ReclamationServiceProf();
         for(ReclamationProf c: ps.displayReclamation())
             ReclamationProf.add(c);
         tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         tabDate.setCellValueFactory(new PropertyValueFactory<>("description"));
         tabEtat.setCellValueFactory(new PropertyValueFactory<>("answer"));
         tableReclamationProf.setItems(ReclamationProf);
    }

    public void directEXIT(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("BackFXML.fxml"));
            Parent root= loader.load();
         //   BackFXMLController r= loader.getController();
            EXIT.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); }
    }

    @FXML
    private void addReclamationProf(ActionEvent event) {
        String title=tfTitle.getText();
        String description=tfDate.getText();
        int id_professeur=1;
        ReclamationProf r= new ReclamationProf(title,description,id_professeur);
            ReclamationServiceProf ps = new ReclamationServiceProf();
            ps.ajouterReclamation(r);
            loadDataProf(); }

    @FXML
    private void modifyReclamationProf(ActionEvent event) {
            ReclamationServiceProf rs = new ReclamationServiceProf();
            ReclamationProf u = new ReclamationProf();
            u.setTitle(tfTitle.getText());
            u.setDescription(tfDate.getText());
            rs.updateReclamation(u,id);
            loadDataProf();
    }

    

    @FXML
    private void deleteReclamationProf(ActionEvent event) {
        
        ReclamationServiceProf ps = new ReclamationServiceProf();
        
      if(!tableReclamationProf.getSelectionModel().getSelectedItems().isEmpty()) {
          ps.supprimerReclamation(tableReclamationProf.getSelectionModel().getSelectedItems().get(0));
            loadDataProf(); }
        
      else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait(); }
    }

    @FXML
    private void selected(MouseEvent event) {
        ReclamationProf r = tableReclamationProf.getSelectionModel().getSelectedItem();
        id=r.getId();
        tfTitle.setText(r.getTitle());
        tfDate.setText(r.getDescription()); }
}
*/