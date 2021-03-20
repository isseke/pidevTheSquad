package Gestionnaire;

import Modele.ApprenantEntity;
import Modele.Professeur;
import Modele.administrateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Authentification {

@FXML
public Hyperlink passwordforget;
    @FXML
    public TextField tfid;

@FXML
public Button resetteacherlink;
    @FXML
    public TextField tfnom;

    @FXML
    public TextField tfprenom;

    @FXML
    public TextField tfemail;
    @FXML
    public TextField tfstatus;

    @FXML
    public Button testpro;

    @FXML
    public TextField tfemaillogin;
    @FXML
    public TextField tfpassword;

    @FXML
    public Hyperlink testbutton;

    @FXML
    public Button idreturn;
    @FXML
    public Button btnregister;

    String imagePath = null;
    @FXML
    public TextField filterField;

    @FXML
    public ImageView imageTactor;
@FXML
public Button passwordforgetButton;

    @FXML
    public PasswordField pswfieldlogin;

    @FXML
    public TableView<ApprenantEntity> tvapp;

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
    public TableColumn<ApprenantEntity, String> colstatus;

    @FXML
    public TextField tfnom1;

    @FXML
    public TextField tfprenom1;
    @FXML
    public TextField tfid1;


    String status1="false";
    @FXML
    public Button btnlogin;


    public boolean executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ObservableList<administrateur> getAdminList(){
        ObservableList<administrateur> adminList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  administrateur where(email='"+tfemaillogin.getText()+"' and password='"+pswfieldlogin.getText()+"')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            administrateur admis;
            while(rs.next()) {
                admis = new administrateur(rs.getInt("Id_admin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("photo"),rs.getString("email"),rs.getString("password"));
                adminList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }
    public ObservableList<Professeur> getProfList(){
        ObservableList<Professeur> profList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  professeur where(email='"+tfemaillogin.getText()+"' and password='"+pswfieldlogin.getText()+"')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Professeur admis;
            while(rs.next()) {
                admis = new Professeur(rs.getInt("Id_professeur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("photo"),rs.getString("email"),rs.getString("password"),rs.getString("specialite"),rs.getString("profil"));
                profList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profList;
    }
    public ObservableList<ApprenantEntity> getApprenantList(){
        String test50="True";
        ObservableList<ApprenantEntity> apprenantList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  apprenant where(email='"+tfemaillogin.getText()+"' and password='"+pswfieldlogin.getText()+"' and status='"+test50+"')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ApprenantEntity admis;
            while(rs.next()) {
                admis = new ApprenantEntity(rs.getInt("id_apprenant"),rs.getString("nom"),rs.getString("prenom"),rs.getString("photo"),rs.getString("email"),rs.getString("password"), rs.getNString("status"));
                apprenantList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apprenantList;
    }
    public ObservableList<ApprenantEntity> getNonactiveList(){
        ObservableList<ApprenantEntity> apprenantList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  apprenant where(status='"+status1+"')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ApprenantEntity admis;
            while(rs.next()) {
                admis = new ApprenantEntity(rs.getInt("id_apprenant"),rs.getString("nom"),rs.getString("prenom"),rs.getString("photo"),rs.getString("email"),rs.getString("password"), rs.getNString("status"));
                apprenantList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apprenantList;
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

    public void gotoregister(MouseEvent event) throws IOException {

     Parent root = FXMLLoader.load(getClass().getResource("/vue/Register.fxml"));
       // URL url = Paths.get("./src/sample/Views/Register.fxml").toUri().toURL();
        //Parent root = FXMLLoader.load(url);



        Stage window=(Stage) testbutton.getScene().getWindow();
   window.setScene(new Scene(root,1370,700));











    }
    @FXML
    public void homeClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));

       // URL url = Paths.get("./src/sample/Views/SideBar.fxml").toUri().toURL();
   //     Parent root = FXMLLoader.load(url);

        Stage window=(Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root,1350,700));


    }

    public void gobackbro(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));

      //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
      //  Parent root = FXMLLoader.load(url);

        Stage window=(Stage) idreturn.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));
    }

    public void registerdone(MouseEvent event) throws IOException {

        String query = "insert into apprenant (nom,prenom,photo,email,password) values ('"+tfnom.getText()+"','"+tfprenom.getText()+"','"+imagePath+"','"+tfemail.getText()+"','"+tfpassword.getText()+"')";


        if(executeQuery(query))
        {

            String title = "Congratulations sir";
            String message = "You've successfully created your Account ";
            NotificationType notification = NotificationType.CUSTOM.SUCCESS;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();

        }



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

    public void loginClick(MouseEvent event) throws IOException {

       ObservableList<administrateur> list = getAdminList();
        ObservableList<Professeur> list2 = getProfList();
        ObservableList<ApprenantEntity> list3 = getApprenantList();









        if (list2.isEmpty() && list.isEmpty() && list3.isEmpty())
        {
            String title = "Congratulations sir";
            String message = "You've successfully Failed to login ";
            NotificationType notification = NotificationType.ERROR;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();
        }

            else if(list2.isEmpty() && list3.isEmpty())
        {

            Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));
          //  URL url = Paths.get("./src/vue/SideBar.fxml").toUri().toURL();
           // Parent root = FXMLLoader.load(url);

            Stage window=(Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root,1370,700));

        }
            else if
        (list.isEmpty() && list3.isEmpty()) {
                Parent root = FXMLLoader.load(getClass().getResource("/vue/Professeurhome.fxml"));
          //  URL url = Paths.get("./src/sample/Views/Professeurhome.fxml").toUri().toURL();
            // Parent root = FXMLLoader.load(url);
            Stage window=(Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root,1370,700));

        }
            else if(list2.isEmpty() && list.isEmpty() )

{
    Parent root = FXMLLoader.load(getClass().getResource("/vue/Apprenanthome.fxml"));
   // URL url = Paths.get("./src/sample/Views/Apprenanthome.fxml").toUri().toURL();
   // Parent root = FXMLLoader.load(url);

            Stage window=(Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root,1370,700));
}
else
            System.out.println("ala8leb");




    }

    public void ListerNonactive() {
        ObservableList<ApprenantEntity> list = getNonactiveList();

        colid.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, Integer>("id_apprenant"));
        colnom.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("prenom"));
        colphoto.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("photo"));
        colemail.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("email"));


        colpassword.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("password"));
        colstatus.setCellValueFactory(new PropertyValueFactory<ApprenantEntity, String>("status"));


        tvapp.setItems(list);
    }

    public void mouseclickgogogogogo2(MouseEvent event) {
        ApprenantEntity apprenant= tvapp.getSelectionModel().getSelectedItem();

        tfnom1.setText(apprenant.getNom());
        tfprenom1.setText(apprenant.getPrenom());
        tfid1.setText(" " +apprenant.getId_apprenant());



        Image image = new Image("file:///"+apprenant.getPhoto());
        imageTactor.setImage(image);
        imagePath=apprenant.getPhoto();


    }

    public void Unblockblock()
    {    String query = "UPDATE apprenant SET status = 'True' WHERE id_apprenant ="+tfid1.getText()+"";

        executeQuery(query);
        getNonactiveList();


    }

    public void GoToApprenantRestPasswrd(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieApprenant.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window=(Stage) passwordforget.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));

    }
    public void GoToApprenantRestPasswrd2(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieApprenant.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window=(Stage) passwordforgetButton.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));

    }

    public void GoToresetPasswordTeacher(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieProf.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window=(Stage) resetteacherlink.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));
    }
}
