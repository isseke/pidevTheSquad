/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestionnaire;

import Modele.AdminReclamation;
import Modele.Reclamation;
import Services.AdminReclamationService;

import java.io.FileOutputStream;
import java.io.IOException;


import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import ConnectDB.MyConnection;
import Services.ReclamationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author ASUS
 */
public class FXMLReclamationAdmin implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfTitle ,tfTitle2 ,filterFieldRecl, filterFieldRecl2, filterFieldRecl3;;
    @FXML
    private DatePicker tfDate ,tfDate2;
    @FXML
    private TextArea tfRecl,tfReclAdd ,tfRecl2,tfReclAdd2 ,tfRecl3;
    @FXML
    private TableView<AdminReclamation> tableReclamation ,tableReclamation2 ,tableReclamation3 ;
    @FXML
    private TableColumn<AdminReclamation, String> tabTitle ,tabTitle2 ,tabTitle3 ;
    @FXML
    private TableColumn<AdminReclamation, String> tabDate ,tabDate2 ,tabDate3 ;
    @FXML
    private TableColumn<AdminReclamation, String> tabExp ,tabExp2 ,tabExp3;
    @FXML
    private TableColumn<AdminReclamation, Integer> tabIDU ,tabIDU2 ,tabIDU3;
    @FXML
    private ComboBox<String> comb;
    private static int id ,id2 ,id3;
    private static String exp;
    ObservableList<String> list = FXCollections.observableArrayList("Non-Traitée" ,"En-Cours" ,"Traitée");
    @FXML
    public Button dreturn;
    @FXML
    public Label testrecl;
    @FXML void Select(ActionEvent event){ String s = comb.getSelectionModel().getSelectedItem().toString(); }
    public void setid4(String text) { this.testrecl.setText("" + text); }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<AdminReclamation> AdminReclamation = FXCollections.observableArrayList();
        comb.setItems(list);
        AdminReclamationService ps = new AdminReclamationService();
         for(AdminReclamation c: ps.displayReclamation()) AdminReclamation.add(c);
         tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         tabExp.setCellValueFactory(new PropertyValueFactory<>("exp"));
         tabIDU.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
         tableReclamation.setItems(AdminReclamation);
        try { Notif(); } catch (MalformedURLException e) { e.printStackTrace(); }
        SearchReclamation(); SearchReclamationCorbeille(); SearchReclamationArchive();}


    public void loadData() throws MalformedURLException {
        ObservableList<AdminReclamation> AdminReclamation = FXCollections.observableArrayList();
        comb.setItems(list);
        AdminReclamationService ps = new AdminReclamationService();
        for(AdminReclamation c: ps.displayReclamation()) AdminReclamation.add(c);
        tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabExp.setCellValueFactory(new PropertyValueFactory<>("exp"));
        tabIDU.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
        tableReclamation.setItems(AdminReclamation); SearchReclamation();
    }


    public void loadDataCorbeille() {
        ObservableList<AdminReclamation> Reclamation = FXCollections.observableArrayList();
        AdminReclamationService ps = new AdminReclamationService();
        for (AdminReclamation c : ps.displayRC()) Reclamation.add(c);
        tabTitle2.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabExp2.setCellValueFactory(new PropertyValueFactory<>("exp"));
        tabIDU2.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
        tableReclamation2.setItems(Reclamation);SearchReclamationCorbeille(); }

    public void loadDataArchive() {
        ObservableList<AdminReclamation> Reclamation = FXCollections.observableArrayList();
        AdminReclamationService ps = new AdminReclamationService();
        for (AdminReclamation c : ps.displayRA()) Reclamation.add(c);
        tabTitle3.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate3.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabExp3.setCellValueFactory(new PropertyValueFactory<>("exp"));
        tabIDU3.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
        tableReclamation3.setItems(Reclamation);SearchReclamationArchive(); }


    public void Refrech() throws MalformedURLException { loadData(); loadDataCorbeille(); loadDataArchive();}

    public boolean verifsupp (String s){  boolean v = false; if (s.equals("UCORBEILLE")) v= true;
        if (s.equals("UCORBEILLE2")) v= true; return v;}

    public void alertsupp() {  Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information"); alert.setHeaderText(null);
        alert.setContentText("l'utilisateur a supprimé la reclamation"); alert.showAndWait(); }


    @FXML
    private void answerReclamation(ActionEvent event) throws MalformedURLException {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayReclamation()) RL.add(c);
        AdminReclamation r2 = tableReclamation.getSelectionModel().getSelectedItem();
        String etat = comb.getValue(); String date = tfDate.getValue().toString();
        String recl = tfReclAdd.getText(); String reclmodif = r2.getRecl();
        String exp = "ADMIN"; String msgA = r2.getMsgA(); String msg = r2.getMsg();
        AdminReclamation r = new AdminReclamation(etat, recl, date ,reclmodif ,exp ,msg ,msgA);
        if( verifsupp(msg) == true ) alertsupp(); else {
        String str; reclmodif = r2.getRecl(); recl = tfReclAdd.getText();
        str = reclmodif +"\n" + "------------------------------- "+"ADMIN"+ " - " + date + " ------------------------------ "
         + "\n" + recl ; r.setRecl(str);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT"); alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment Repondre a la reclamation ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) { rs.answerReclamation(r, id);  }  } Refrech();}


    @FXML
    private void modifyReclamation(ActionEvent event) throws MalformedURLException {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayReclamation()) RL.add(c);
        AdminReclamation r2 = tableReclamation.getSelectionModel().getSelectedItem();
        if (r2.getExp().equals("ADMIN")) {
        String etat = comb.getValue();
        String date = tfDate.getValue().toString();
        String recl = r2.getReclmodif();
        String reclmodif = r2.getReclmodif();
        String exp="ADMIN";
        String msgA= r2.getMsgA(); String msg = r2.getMsg();
        AdminReclamation r = new AdminReclamation(etat, recl ,date ,reclmodif ,exp ,msg ,msgA);
            if( verifsupp(msg) == true ) alertsupp(); else {
        String str,newtxt = tfReclAdd.getText();
        str = recl +"\n" + "-------------------------------- "+"ADMIN"+ " - " + date + " ------------------------------- "
        + "\n" + newtxt ; r.setRecl(str) ;  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("MODIFICATION"); alert.setHeaderText(null); alert.setContentText("voulez vous vraiment modifier cette reclamation ? ");
    Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK) { rs.updateReclamation(r, id);  loadData(); } } }
    else { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information"); alert.setHeaderText(null);
            alert.setContentText("aucune reclamation a modifié"); alert.showAndWait(); }Refrech();}


    @FXML
    private void deleteReclamation(ActionEvent event) throws MalformedURLException {
        AdminReclamationService ps = new AdminReclamationService();
      if(!tableReclamation2.getSelectionModel().getSelectedItems().isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Suppresion");
          alert.setHeaderText(null);
          alert.setContentText("voulez vous vraiment supprimer la reclamation definitivement ? ");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
              ps.supprimerReclamation(tableReclamation2.getSelectionModel().getSelectedItems().get(0));
          } loadData();  }
       else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait(); }  Refrech();}

    @FXML
    private void selected(MouseEvent event) {
        AdminReclamation r = tableReclamation.getSelectionModel().getSelectedItem();
        id=r.getId();  comb.setValue(r.getEtat());  tfRecl.setText(r.getRecl()); }

    @FXML
    private void selected2(MouseEvent event) {
        AdminReclamation r = tableReclamation2.getSelectionModel().getSelectedItem();
        id=r.getId(); tfRecl2.setText(r.getRecl()); }

    @FXML
    private void selected3(MouseEvent event) {
        AdminReclamation r = tableReclamation3.getSelectionModel().getSelectedItem();
        id=r.getId();   tfRecl3.setText(r.getRecl()); }



    public String getEmailUser() {
        try {
            String req = "SELECT email FROM reclamation INNER JOIN apprenant ON reclamation.id_user = apprenant.id_apprenant";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) { String e = rs.getString("email"); return e; }
        }catch (SQLException ex) { System.out.println(ex.getMessage()); }
        return null ;}


    public  List<String> getEUL() {
        List<String> TitleList = new ArrayList<>();
        try { String req = "SELECT email FROM reclamation INNER JOIN professeur ON reclamation.id_prof = professeur.id_professeur WHERE (exp <> 'ADMIN')" ;
            Statement st = MyConnection.getInstance().getCnx().createStatement(); ResultSet rs = st.executeQuery(req);
            while (rs.next()) { String A = rs.getString("email");  TitleList.add(A); } }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }

        try { String req = "SELECT email FROM reclamation INNER JOIN apprenant ON reclamation.id_user = apprenant.id_apprenant WHERE (exp <> 'ADMIN')" ;
            Statement st = MyConnection.getInstance().getCnx().createStatement(); ResultSet rs = st.executeQuery(req);
            while (rs.next()) { String A = rs.getString("email");  TitleList.add(A); } }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }
 return TitleList; }


    @FXML
    private void Notif() throws MalformedURLException {
        int NB = 0;  List<String> NomUserL = new ArrayList<>(); NomUserL = getEUL();
        try { String req = "SELECT COUNT(exp) FROM `reclamation` WHERE (exp <> 'ADMIN')";
    Statement st = MyConnection.getInstance().getCnx().createStatement(); ResultSet rs = st.executeQuery(req);
        while (rs.next()) { NB = rs.getInt("COUNT(exp)"); }
    } catch (SQLException ex) { System.out.println(ex.getMessage()); }
        if(NB != 0){ String ListeNU = NomUserL.get(0);
            for(int i=1 ;i < NomUserL.size() ;i++)  ListeNU = ListeNU + " , " + NomUserL.get(i);
        TrayNotification tray =new TrayNotification();
        Image whatsAppImg;
        URL url = Paths.get("./src/iconspicture/msg.bmp").toUri().toURL();
        whatsAppImg = new Image(String.valueOf(url));
        tray.setTitle(NB + " Nouveaux Message");
        tray.setMessage(ListeNU);
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setImage(whatsAppImg);
        tray.showAndDismiss(Duration.seconds(60)); } }


    @FXML
    public void archiverReclamation(ActionEvent event) throws MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT"); alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment archiver la reclamation ? ");
        Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK) {
        AdminReclamation r2 = tableReclamation.getSelectionModel().getSelectedItem();
        id = r2.getId();
        if(r2.getEtat().equals("Traitée")) { String msgA = "AARCHIVE";
        try { String req = "UPDATE `reclamation` SET `msgA`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, msgA);
            int rowsUpdated = pst.executeUpdate(); if (rowsUpdated > 0) { System.out.println("Reclamation Archivée"); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); } }
        else  { Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Information"); alert2.setHeaderText(null);
            alert2.setContentText("la reclamation n'est pas encore traitée "); alert2.showAndWait(); } Refrech();} }


    @FXML
    public void corbeilleReclamation(ActionEvent event) throws MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT"); alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment deplacer la reclamation dans la corbeille ? ");
        Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK) {
        AdminReclamation r = tableReclamation.getSelectionModel().getSelectedItem();
        id = r.getId(); String msgA = "ACORBEILLE";
        try { String req = "UPDATE `reclamation` SET `msgA`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, msgA);
            int rowsUpdated = pst.executeUpdate(); if (rowsUpdated > 0) { System.out.println("Reclamation mis en corbeille"); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); }Refrech(); } }


    @FXML
    public void corbeilleReclamation2(ActionEvent event) throws MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT"); alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment deplacer la reclamation dans la corbeille ?  ");
        Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK) {
        AdminReclamation r3 = tableReclamation3.getSelectionModel().getSelectedItem();
        id3=r3.getId(); String msgA = "ACORBEILLE2";
        try { String req = "UPDATE `reclamation` SET `msgA`=? WHERE id='"+id3+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, msgA);
            int rowsUpdated = pst.executeUpdate(); if (rowsUpdated > 0) { System.out.println("Reclamation mis en corbeille"); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); } Refrech();} }


    @FXML
    public void restoreReclamation(ActionEvent event) throws MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT"); alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment restaurer la reclamation ? ");
        Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK) {
        AdminReclamation r2 = tableReclamation2.getSelectionModel().getSelectedItem();
        String msgA = null; id = r2.getId();
        if(Objects.equals(r2.getMsgA(), "ACORBEILLE")) msgA = "ABR"; else msgA="AARCHIVE";
        try { String req = "UPDATE `reclamation` SET `msgA`=? WHERE id='"+id+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, msgA);
            int rowsUpdated = pst.executeUpdate(); if (rowsUpdated > 0) { System.out.println("Reclamation restaurée"); }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); } Refrech();} }


    @FXML
    public void addArchivedReclamation(ActionEvent event) throws MalformedURLException {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayRA()) RL.add(c);
        if (tableReclamation3.getSelectionModel() != null ) {
            AdminReclamation r2 = tableReclamation3.getSelectionModel().getSelectedItem();
            String msgA= "ABR"; String reclmodif = r2.getRecl(); String recl = tfReclAdd2.getText(); String msg = r2.getMsg();
            String str; String etat = "En-Cours"; String date = tfDate2.getValue().toString(); String exp = r2.getExp();
            AdminReclamation r = new AdminReclamation(etat, recl ,date ,reclmodif ,exp ,msg ,msgA);
            if(verifsupp(msg)) alertsupp(); else {
                str = reclmodif +"\n" + "----------------------------- "+"ADMIN"+ " - " + date + " ---------------------------- "
                        + "\n" + recl ; r.setRecl(str);Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("AJOUT"); alert.setHeaderText(null);
                alert.setContentText("voulez vous vraiment Ajouter la reclamation ? ");
                Optional<ButtonType> action = alert.showAndWait(); if (action.get() == ButtonType.OK)
                { rs.updateReclamation(r, id);  Refrech(); } } } }




    public void SearchReclamation() {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayReclamation()) RL.add(c);

        FilteredList<AdminReclamation> filteredData = new FilteredList<>(RL, b -> true);
        filterFieldRecl.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(recl -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (recl.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<AdminReclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
        tableReclamation.setItems(sortedData);
    }

    public void SearchReclamationCorbeille() {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayRC()) RL.add(c);

        FilteredList<AdminReclamation> filteredData = new FilteredList<>(RL, b -> true);
        filterFieldRecl2.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(recl -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (recl.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<AdminReclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation2.comparatorProperty());
        tableReclamation2.setItems(sortedData);
    }

    public void SearchReclamationArchive() {
        ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
        AdminReclamationService rs = new AdminReclamationService();
        for (AdminReclamation c : rs.displayRA()) RL.add(c);

        FilteredList<AdminReclamation> filteredData = new FilteredList<>(RL, b -> true);
        filterFieldRecl3.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(recl -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (recl.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<AdminReclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation3.comparatorProperty());
        tableReclamation3.setItems(sortedData);
    }

    public void homeClickApprenant(MouseEvent event) throws IOException {
        //  Parent root = FXMLLoader.load(getClass().getResource("/vue/Apprenanthome.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/SideBar.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) dreturn.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));  }


    @FXML
    private void PDF(ActionEvent event) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Liste des reclamations Admin " + ".pdf" ));
            document.open();
            Paragraph ph1 = new Paragraph("                                                           POLYWAYS-LEARNING"+ "\n"+ "\n");
            Paragraph ph2 = new Paragraph("                                              LISTE DES RECLAMATIONS ADMIN : " + "\n"+ "\n");
            Paragraph ph3 = new Paragraph(" ");
            PdfPTable table = new PdfPTable(3);

            PdfPCell cell;
            table.addCell("Title");
            table.addCell("Date");
            table.addCell("Etat");


            ObservableList<AdminReclamation> RL = FXCollections.observableArrayList();
            AdminReclamationService rs = new AdminReclamationService();
            for (AdminReclamation c : rs.displayReclamation()) RL.add(c);
            System.out.println("Reclamation pdf");
            RL.forEach(e -> {
                table.addCell(e.getTitle());
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getDate()));
                table.addCell(e.getEtat()); });


            document.add(ph1);
            document.add(ph2);
            document.add(ph3);
            document.add(table);
            document.addAuthor("Polyways Learning");
        } catch (Exception e) { System.out.println(e); }document.close(); }



}