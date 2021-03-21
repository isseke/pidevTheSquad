package Gestionnaire;

import Modele.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    public TextField tfid;


    @FXML
    public TextField tfnom;

    @FXML
    public TextField tfprenom;

    @FXML
    public TextField tfphoto;

    @FXML
    public TextField tfemail;
    @FXML
    public TextField tfpassword;

    @FXML
    public TextField filterField;
    @FXML
    public TextField tfspecialite;
    @FXML
    public TextField tfprofil;

    @FXML
    public Button insertButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button ListButton;
    @FXML
    public Button disconnectButton;

    @FXML
    public Button profbutton;

    @FXML
    public Button testpro;
    @FXML
    public Button testapp;
    @FXML
    public Button testapp3;
    @FXML
    public Button testhome;

    String imagePath = null;




    @FXML
    public TableView<Professeur> tvprof;

    @FXML
    public TableColumn<Professeur, Integer> colid;

    @FXML
    public TableColumn<Professeur, String> colnom;

    @FXML
    public TableColumn<Professeur, String> colprenom;

    @FXML
    public TableColumn<Professeur, String> colphoto;

    @FXML
    public TableColumn<Professeur, String> colemail;
    @FXML
    public TableColumn<Professeur, String> colpassword;
    @FXML
    public TableColumn<Professeur, String> colspecialite;
    @FXML
    public TableColumn<Professeur, String> colprofil;


    @FXML
    public BorderPane bp;
    @FXML
    public AnchorPane ap;


    @FXML
    public BorderPane bp2;
    @FXML
    public AnchorPane ap2;

    @FXML
    public ImageView imageTactor;




    @FXML
    public void insertButton() {

       // String query = "insert into professeur values (nom='"+tfnom.getText()+"',prenom='"+tfprenom.getText()+"',photo='"+tfphoto.getText()+"',email='"+tfemail.getText()+",password='"+tfpassword.getText()+"',specialite='"+tfspecialite.getText()+"',profil='"+tfprofil.getText()+"')";
        String query = "insert into professeur (nom,prenom,photo,email,password,specialite,profil) values ('"+tfnom.getText()+"','"+tfprenom.getText()+"','"+imagePath+"','"+tfemail.getText()+"','"+tfpassword.getText()+"','"+tfspecialite.getText()+"','"+tfprofil.getText()+"')";


        executeQuery(query);
        showProf();
    }

    @FXML
    public void Listbouton() {

        showProf();
    }





    @FXML
    public void updateButton() {
        String query = "UPDATE professeur SET nom='"+tfnom.getText()+"',prenom='"+tfprenom.getText()+"',photo='"+imagePath+"',email='"+tfemail.getText()+"',password='"+tfpassword.getText()+"',specialite='"+tfspecialite.getText()+"',profil='"+tfprofil.getText()+"' WHERE Id_professeur ="+tfid.getText()+"";

        executeQuery(query);
        showProf();
    }

    @FXML
    public void deleteButton() {
        String query = "DELETE FROM professeur WHERE Id_professeur="+tfid.getText()+"";
        executeQuery(query);
        showProf();
    }
    @FXML
    public void mouseclickgogogogogo(){
        Professeur professeur= tvprof.getSelectionModel().getSelectedItem();
       tfid.setText(" " +professeur.getId());

        tfnom.setText(professeur.getNom());
        tfprenom.setText(professeur.getPrenom());
       // tfphoto.setText(" "+professeur.getPhoto());
        Image image = new Image("file:///"+professeur.getPhoto());
        imageTactor.setImage(image);
        imagePath=professeur.getPhoto();

        tfemail.setText(" "+professeur.getEmail());
        tfpassword.setText(professeur.getPassword());
        tfspecialite.setText(" "+professeur.getSpecialite());
        tfprofil.setText(" "+professeur.getProfil());

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev","root","");

            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public ObservableList<Professeur> getProfList(){
        ObservableList<Professeur> ProfList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM professeur ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Professeur profs;
            while(rs.next()) {
                profs = new Professeur(rs.getInt("Id_professeur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("photo"),rs.getString("email"),rs.getString("password"),rs.getString("specialite"),rs.getString("profil"));
                ProfList.add(profs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ProfList;
    }
    public void showProf() {
        ObservableList<Professeur> list = getProfList();

            colid.setCellValueFactory(new PropertyValueFactory<Professeur, Integer>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<Professeur, String>("nom"));
            colprenom.setCellValueFactory(new PropertyValueFactory<Professeur, String>("prenom"));
            colphoto.setCellValueFactory(new PropertyValueFactory<Professeur, String>("photo"));
            colemail.setCellValueFactory(new PropertyValueFactory<Professeur, String>("email"));

        colpassword.setCellValueFactory(new PropertyValueFactory<Professeur, String>("password"));
        colspecialite.setCellValueFactory(new PropertyValueFactory<Professeur, String>("specialite"));
        colprofil.setCellValueFactory(new PropertyValueFactory<Professeur, String>("profil"));

        tvprof.setItems(list);

    }



    @FXML
    public void homeClick(MouseEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));
       // URL url = Paths.get("./src/sample/Views/SideBar.fxml").toUri().toURL();
        // Parent root = FXMLLoader.load(url);


        Stage window=(Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));


    }

    @FXML
    public void page01Click(MouseEvent event) throws IOException {

      Parent root = FXMLLoader.load(getClass().getResource("/vue/page01.fxml"));

    //    URL url = Paths.get("./src/sample/Views/page01.fxml").toUri().toURL();
       //  Parent root = FXMLLoader.load(url);


        Stage window=(Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));


    }

    @FXML
    public void page02Click(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/page02.fxml"));
       // URL url = Paths.get("./src/sample/Views/page02.fxml").toUri().toURL();
        // Parent root = FXMLLoader.load(url);

        Stage window=(Stage) testapp.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));
    }
    @FXML
    public void page03Click(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/page03.fxml"));

      //  URL url = Paths.get("./src/sample/Views/page03.fxml").toUri().toURL();
       // Parent root = FXMLLoader.load(url);

        Stage window=(Stage) testapp3.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));

    }




    @FXML
    public void DisconnectClick(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));
      //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
       // Parent root = FXMLLoader.load(url);


        Stage window=(Stage) disconnectButton.getScene().getWindow();
window.setScene(new Scene(root,1370,700));




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public String FileChooser(ActionEvent event) { FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Benzarti\\Desktop\\projects\\javafx\\untitled5\\src\\sample\\iconspicture"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.*"));


        File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePath=f.getPath();
        imagePath=imagePath.replace("\\","\\\\");
        return f.getName();
    }



}
