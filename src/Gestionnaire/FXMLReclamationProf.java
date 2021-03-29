
package Gestionnaire;


import Modele.ReclamationProf;
import Services.ReclamationProfService;


import java.io.FileOutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import ConnectDB.MyConnection;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.notification.TrayNotification;



/**
 *
 * @author ASUS
 */
public class FXMLReclamationProf implements Initializable {

    private Label label;
    @FXML
    public TextField tfTitle, tfTitle2, filterFieldRecl, filterFieldRecl2, filterFieldRecl3;
    @FXML
    private DatePicker tfDate, tfDate2;
    @FXML
    private TextArea tfRecl, tfReclAdd, tfRecl2, tfReclAdd2, tfRecl3;
    @FXML
    private TableView<ReclamationProf> tableReclamation, tableReclamation2, tableReclamation3;
    @FXML
    private TableColumn<ReclamationProf, String> tabTitle, tabTitle2, tabTitle3;
    @FXML
    private TableColumn<ReclamationProf, String> tabDate, tabDate2, tabDate3;
    @FXML
    private TableColumn<ReclamationProf, String> tabEtat, tabEtat2, tabEtat3;
    private static int id, id2, id3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
        ReclamationProfService ps = new ReclamationProfService();
        for (ReclamationProf c : ps.displayReclamation()) ReclamationProf.add(c);
        tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableReclamation.setItems(ReclamationProf);
        Notif();
        SearchReclamation();
        SearchReclamationCorbeille();
        SearchReclamationArchive();
    }

    public void loadDataCorbeille() {
        ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
        ReclamationProfService ps = new ReclamationProfService();
        for (ReclamationProf c : ps.displayRC()) ReclamationProf.add(c);
        tabTitle2.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableReclamation2.setItems(ReclamationProf);
        SearchReclamationCorbeille();
    }

    public void loadDataArchive() {
        ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
        ReclamationProfService ps = new ReclamationProfService();
        for (ReclamationProf c : ps.displayRA()) ReclamationProf.add(c);
        tabTitle3.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate3.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableReclamation3.setItems(ReclamationProf);
        SearchReclamationArchive();
    }

    public void loadData() {
        ObservableList<ReclamationProf> ReclamationProf = FXCollections.observableArrayList();
        ReclamationProfService ps = new ReclamationProfService();
        for (ReclamationProf c : ps.displayReclamation()) ReclamationProf.add(c);
        tabTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableReclamation.setItems(ReclamationProf);
        Notif();
        SearchReclamation();
    }


    public List<String> getTitleReclamation() {
        List<String> TitleList = new ArrayList<>();
        try {
            String req = "SELECT title FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String A = rs.getString("title");
                TitleList.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return TitleList;
    }


    public boolean verifsupp(String s) {
        boolean v = false;
        if (s.equals("ACORBEILLE")) v = true;
        if (s.equals("ACORBEILLE2")) v = true;
        return v;
    }


    public void alertsupp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("l'administrateur a supprimé la reclamation");
        alert.showAndWait();
    }


    @FXML
    public void addReclamation(ActionEvent event) {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayReclamation()) RL.add(c);
        String title = tfTitle.getText();
        String date = tfDate.getValue().toString();
        String recl = "";
        String reclmodif = "";
        String exp = "USERNAME";
        List<String> TitleL = new ArrayList<>();
        TitleL = getTitleReclamation();
        boolean verif = TitleL.contains(title);
        if (verif && tableReclamation.getSelectionModel() != null) {
            ReclamationProf r2 = tableReclamation.getSelectionModel().getSelectedItem();
            String msgA = r2.getMsgA();
            String str;
            reclmodif = r2.getRecl();
            recl = tfReclAdd.getText();
            String msg = r2.getMsg();
            exp = "USERNAME";
            ReclamationProf r = new ReclamationProf(title, date, recl, reclmodif, exp, msg, msgA);
            if (verifsupp(msgA) == true) alertsupp();
            else {
                str = reclmodif + "\n" + "----------------------------- " + "USERNAME" + " - " + date + " ---------------------------- "
                        + "\n" + recl;
                r.setRecl(str);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("AJOUT");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous vraiment Ajouter une reclamation ? ");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    rs.updateReclamation(r, id);
                    loadData();
                }
            }
        } else {
            String str2;
            recl = tfReclAdd.getText();
            String msg = "BR";
            String msgA = "ABR";
            ReclamationProf r = new ReclamationProf(title, date, recl, reclmodif, exp, msg, msgA);
            str2 = "----------------------------- " + "USERNAME" + " - " + date + " ---------------------------- " + "\n" + recl;
            r.setRecl(str2);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("AJOUT");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment Ajouter une reclamation ? ");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                rs.ajouterReclamation(r);
            }
        }
        Refrech();
    }


    @FXML
    public void modifyReclamation(ActionEvent event) {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayReclamation()) RL.add(c);
        ReclamationProf r2 = tableReclamation.getSelectionModel().getSelectedItem();
        if (r2.getExp().equals("USERNAME")) {
            String title = tfTitle.getText();
            String date = tfDate.getValue().toString();
            String recl = r2.getReclmodif();
            String reclmodif = r2.getReclmodif();
            String exp = "USERNAME";
            String msg = r2.getMsg();
            String msgA = r2.getMsgA();
            ReclamationProf r = new ReclamationProf(title, date, recl, reclmodif, exp, msg, msgA);
            if (verifsupp(msgA) == true) alertsupp();
            else {
                String str, newtxt = tfReclAdd.getText();
                str = recl + "\n" + "----------------------------- " + "USERNAME" + " - " + date + " ---------------------------- "
                        + "\n" + newtxt;
                r.setRecl(str);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("MODIFICATION");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous vraiment modifier cette reclamation ? ");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    rs.updateReclamation(r, id);
                    loadData();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("aucun reclamation a modifié");
            alert.showAndWait();
        }
        Refrech();
    }


    @FXML
    public void deleteReclamation(ActionEvent event) {
        ReclamationProfService ps = new ReclamationProfService();
        if (!tableReclamation2.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppresion");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment supprimer définitivement la reclamation ? ");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ps.supprimerReclamation(tableReclamation2.getSelectionModel().getSelectedItems().get(0));
            }
            loadData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
        }
        Refrech();
    }

    @FXML
    public void selected(MouseEvent event) {
        ReclamationProf r = tableReclamation.getSelectionModel().getSelectedItem();
        id = r.getId();
        tfTitle.setText(r.getTitle());
        tfRecl.setText(r.getRecl());
    }

    @FXML
    public void selected2(MouseEvent event) {
        ReclamationProf r = tableReclamation2.getSelectionModel().getSelectedItem();
        id2 = r.getId();
        tfRecl2.setText(r.getRecl());
        SearchReclamation();
    }

    @FXML
    public void selected3(MouseEvent event) {
        ReclamationProf r = tableReclamation3.getSelectionModel().getSelectedItem();
        id3 = r.getId();
        tfTitle2.setText(r.getTitle());
        tfRecl3.setText(r.getRecl());
    }


    public void Refrech() {
        loadData();
        loadDataArchive();
        loadDataCorbeille();
    }


    @FXML
    public void Notif() {
        int NB = 0;
        try {
            String req = "SELECT COUNT(exp) FROM `reclamation` WHERE (exp = 'ADMIN' AND msg='BR')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                NB = rs.getInt("COUNT(exp)");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (NB != 0) {
            TrayNotification tray = new TrayNotification();
            Image whatsAppImg;
            whatsAppImg = new Image("Tests/msg.bmp");
            tray.setTitle(NB + " Nouveaux Message");
            tray.setMessage(" Nouveaux Message ");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
          //  tray.setAnimation(Animations.POPUP);
            tray.setImage(whatsAppImg);
            tray.showAndDismiss(Duration.seconds(60));
        }
    }


    @FXML
    public void archiverReclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment archiver la reclamation ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ReclamationProf r2 = tableReclamation.getSelectionModel().getSelectedItem();
            id = r2.getId();
            if (r2.getEtat().equals("Traitée")) {
                String msg = "UARCHIVE";
                try {
                    String req = "UPDATE `reclamation` SET `msg`=? WHERE id='" + id + "'";
                    PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
                    pst.setString(1, msg);
                    int rowsUpdated = pst.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Reclamation Archivée");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Information");
                alert2.setHeaderText(null);
                alert2.setContentText("la reclamation n'est pas encore traitée ");
                alert2.showAndWait();
            }
        }
        Refrech();
    }


    @FXML
    public void corbeilleReclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment deplacer la reclamation dans la corbeille ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ReclamationProf r = tableReclamation.getSelectionModel().getSelectedItem();
            id = r.getId();
            String msg = "UCORBEILLE";
            try {
                String req = "UPDATE `reclamation` SET `msg`=? WHERE id='" + id + "'";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
                pst.setString(1, msg);
                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Reclamation mis en corbeille");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Refrech();
    }


    @FXML
    public void corbeilleReclamation2(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment deplacer la reclamation dans la corbeille ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ReclamationProf r3 = tableReclamation3.getSelectionModel().getSelectedItem();
            id3 = r3.getId();
            String msg = "UCORBEILLE2";
            try {
                String req = "UPDATE `reclamation` SET `msg`=? WHERE id='" + id3 + "'";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
                pst.setString(1, msg);
                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Reclamation mis en corbeille");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            Refrech();
        }
    }


    @FXML
    public void restoreReclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AJOUT");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment restaurer la reclamation ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ReclamationProf r2 = tableReclamation2.getSelectionModel().getSelectedItem();
            String msg = null;
            id = r2.getId();
            if (Objects.equals(r2.getMsg(), "UCORBEILLE")) msg = "BR";
            else msg = "UARCHIVE";
            try {
                String req = "UPDATE `reclamation` SET `msg`=? WHERE id='" + id + "'";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
                pst.setString(1, msg);
                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Reclamation restaurée");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            Refrech();
        }
    }


    @FXML
    public void addArchivedReclamation(ActionEvent event) {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayRA()) RL.add(c);
        if (tableReclamation3.getSelectionModel() != null) {
            ReclamationProf r2 = tableReclamation3.getSelectionModel().getSelectedItem();
            String msgA = r2.getMsgA();
            String reclmodif = r2.getRecl();
            String recl = tfReclAdd2.getText();
            String msg = "BR";
            String str;
            String title = tfTitle2.getText();
            String date = tfDate2.getValue().toString();
            String exp = r2.getExp();
            ReclamationProf r = new ReclamationProf(title, date, recl, reclmodif, exp, msg, msgA);
            if (verifsupp(msgA) == true) alertsupp();
            else {
                str = reclmodif + "\n" + "----------------------------- " + "USERNAME" + " - " + date + " ---------------------------- "
                        + "\n" + recl;
                r.setRecl(str);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("AJOUT");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous vraiment Ajouter une reclamation ? ");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    rs.updateReclamation(r, id);
                }
            }
        }
        Refrech();
    }


    public void SearchReclamation() {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayReclamation()) RL.add(c);

        FilteredList<ReclamationProf> filteredData = new FilteredList<>(RL, b -> true);
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
        SortedList<ReclamationProf> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
        tableReclamation.setItems(sortedData);
    }

    public void SearchReclamationCorbeille() {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayRC()) RL.add(c);

        FilteredList<ReclamationProf> filteredData = new FilteredList<>(RL, b -> true);
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
        SortedList<ReclamationProf> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation2.comparatorProperty());
        tableReclamation2.setItems(sortedData);
    }

    public void SearchReclamationArchive() {
        ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
        ReclamationProfService rs = new ReclamationProfService();
        for (ReclamationProf c : rs.displayRA()) RL.add(c);

        FilteredList<ReclamationProf> filteredData = new FilteredList<>(RL, b -> true);
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
        SortedList<ReclamationProf> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation3.comparatorProperty());
        tableReclamation3.setItems(sortedData);
    }


   @FXML
    private void PDF(ActionEvent event) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("PolywaysLearning");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);

            PdfPCell cell;
            table.addCell("Title : ");
            table.addCell("Date : ");
            table.addCell("Etat : ");

            ObservableList<ReclamationProf> RL = FXCollections.observableArrayList();
            ReclamationProfService rs = new ReclamationProfService();
            for (ReclamationProf c : rs.displayRA()) RL.add(c);
            System.out.println("Reclamation pdf");
            RL.forEach(e -> {
                table.addCell(e.getTitle());
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                //table.addCell(String.valueOf(e.getDate()));
                table.addCell(e.getEtat()); });

            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("PolyWays Learning");
        } catch (Exception e) { System.out.println(e); }document.close(); }


}
