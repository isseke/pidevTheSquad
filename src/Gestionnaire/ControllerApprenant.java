package Gestionnaire;

import Modele.ApprenantEntity;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerApprenant implements Initializable {
    @FXML
    public TextField tfid;


    @FXML
    public TextField tfnom;

    @FXML
    public TextField tfprenom;

    @FXML
    public TextField tfphoto;

    @FXML
    public TextField filterField;


    @FXML
    public TextField tfemail;
    @FXML
    public TextField tfpassword;


    @FXML
    public Button insertButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button ListButton;
    @FXML
    public Button testpro;

    @FXML
    public TableView<ApprenantEntity> tvappr;

    @FXML
    public TableColumn<ApprenantEntity, Integer> colid;

    @FXML
    public TableColumn<ApprenantEntity, String> colnom;

    @FXML
    public TableColumn<ApprenantEntity, String> colprenom;

    @FXML
    public TableColumn<ApprenantEntity, String> colphoto;

    @FXML
    public TableColumn<ApprenantEntity, String> colemail;
    @FXML
    public TableColumn<ApprenantEntity, String> colpassword;

    @FXML
    public ImageView imageTactor;
    String imagePath = null;
    String status = "True";


    @FXML
    public void insertButtonAdmin() {
        if(EmailTest(tfemail.getText())==true)
        {

        String query = "insert into apprenant (nom,prenom,photo,email,password,status) values ('" + tfnom.getText() + "','" + tfprenom.getText() + "','" + imagePath + "','" + tfemail.getText() + "','" + tfpassword.getText() + "','" + status + "')";


        executeQuery(query);
        showApprenant();}
        else {
            String title = "NOOO sir";
            String message = "You've successfully Entred Wrong Mail Format    ";
            NotificationType notification = NotificationType.WARNING;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();

        }
    }

    @FXML
    public void insertButton() {

        // String query = "insert into professeur values (nom='"+tfnom.getText()+"',prenom='"+tfprenom.getText()+"',photo='"+tfphoto.getText()+"',email='"+tfemail.getText()+",password='"+tfpassword.getText()+"',specialite='"+tfspecialite.getText()+"',profil='"+tfprofil.getText()+"')";
        String query = "insert into apprenant (nom,prenom,photo,email,password) values ('" + tfnom.getText() + "','" + tfprenom.getText() + "','" + imagePath + "','" + tfemail.getText() + "','" + tfpassword.getText() + "')";


        executeQuery(query);
        showApprenant();
    }

    @FXML
    public void Listbouton() {

        showApprenant();
    }


    @FXML
    public void homeClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));
        //  URL url = Paths.get("./src/sample/Views/SideBar.fxml").toUri().toURL();
        // Parent root = FXMLLoader.load(url);

        Stage window = (Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));


    }


    @FXML
    public void updateButton() {
        String query = "UPDATE apprenant SET nom='" + tfnom.getText() + "',prenom='" + tfprenom.getText() + "',photo='" + imagePath + "',email='" + tfemail.getText() + "',password='" + tfpassword.getText() + "' WHERE id_apprenant =" + tfid.getText() + "";

        executeQuery(query);
        showApprenant();
    }

    @FXML
    public void deleteButton() {
        String query = "DELETE FROM apprenant WHERE id_apprenant =" + tfid.getText() + "";
        executeQuery(query);
        showApprenant();
    }

    @FXML
    public void mouseclickgogogogogo() {
        ApprenantEntity apprenant = tvappr.getSelectionModel().getSelectedItem();
        tfid.setText(" " + apprenant.getId_apprenant());
        tfnom.setText(apprenant.getNom());
        tfprenom.setText(apprenant.getPrenom());
        //  tfphoto.setText(" "+apprenant.getPhoto());
        Image image = new Image("file:///" + apprenant.getPhoto());
        imageTactor.setImage(image);
        imagePath = apprenant.getPhoto();
        tfemail.setText(" " + apprenant.getEmail());
        tfpassword.setText(apprenant.getPassword());

    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<ApprenantEntity> getApprenantList() {
        ObservableList<ApprenantEntity> ApprenantList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM apprenant ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ApprenantEntity appre;
            while (rs.next()) {
                appre = new ApprenantEntity(rs.getInt("id_apprenant"), rs.getString("nom"), rs.getString("prenom"), rs.getString("photo"), rs.getString("email"), rs.getString("password"), rs.getString("status"));
                ApprenantList.add(appre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApprenantList;
    }

    public void showApprenant() {
        ObservableList<ApprenantEntity> list = getApprenantList();

        colid.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, Integer>("id_apprenant"));
        colnom.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("prenom"));
        colphoto.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("photo"));
        colemail.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("email"));

        colpassword.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("password"));


        tvappr.setItems(list);
        Search_apprenant();


    }

    public String FileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Benzarti\\Desktop\\projects\\javafx\\untitled5\\src\\sample\\iconspicture"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.*"));


        File f = fc.showOpenDialog(null);
        if (f != null) {
            System.out.println(f);
        }
        imagePath = f.getPath();
        imagePath = imagePath.replace("\\", "\\\\");
        return f.getName();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void Search_apprenant() {
        ObservableList<ApprenantEntity> list = getApprenantList();

        FilteredList<ApprenantEntity> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(prof -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (prof.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (prof.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(prof.getEmail()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<ApprenantEntity> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvappr.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvappr.setItems(sortedData);
    }
    @FXML
    private void PDFApprenant(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException {
        Connection connection = getConnection();

        Statement stmt = connection.createStatement();
        /* Define the SQL query */
        ResultSet query_set = stmt.executeQuery("SELECT * FROM apprenant");
        /* Step-2: Initialize PDF documents - logical objects */
        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Liste Apprenant.pdf"));
        my_pdf_report.open();
        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(4);
        //create a cell object
        PdfPCell table_cell;

        while (query_set.next()) {
            String dept_id = query_set.getString("nom");
            table_cell=new PdfPCell(new Phrase(dept_id));
            my_report_table.addCell(table_cell);
            String dept_name=query_set.getString("prenom");
            table_cell=new PdfPCell(new Phrase(dept_name));
            my_report_table.addCell(table_cell);
            String manager_id=query_set.getString("email");
            table_cell=new PdfPCell(new Phrase(manager_id));
            my_report_table.addCell(table_cell);
            String location_id=query_set.getString("password");
            table_cell=new PdfPCell(new Phrase(location_id));
            my_report_table.addCell(table_cell);
        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        /* Close all DB related objects */
        query_set.close();
        stmt.close();
        connection.close();
    }
    public boolean EmailTest(String testmail)
    {
        String email="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat=Pattern.compile(email,Pattern.CASE_INSENSITIVE);
        Matcher matcher=emailpat.matcher(testmail);
        return matcher.find();
    }


}
