package Gestionnaire;
import Modele.ApprenantEntity;
import Modele.Professeur;
import Modele.administrateur;
import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import org.opencv.highgui.*;
//import org.opencv.core.*;
//import org.opencv.core.Mat;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Authentification implements Initializable {




    @FXML
    public Hyperlink passwordforget;
    @FXML
    public TextField tfid;

    @FXML
    public Button resetteacherlink;
    @FXML
    public TextField tfnom;
    @FXML
    public TextField tfnomreset;
    @FXML
    public TextField tfemailreset;

    @FXML
    public TextField tfmail1;


    @FXML
    public TextField tfprenom;
    @FXML
    public TextField tfconfirmpassword;
    @FXML
    public TextField tfconfirmpassword2;

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
    public TextField tfpasswordreset;
    @FXML
    public TextField tfpasswordreset2;


    @FXML
    public Label test999;

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


    @FXML
    private BorderPane borderpanneProf;


    String status1 = "false";
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

    public ObservableList<administrateur> getAdminList() {
        ObservableList<administrateur> adminList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  administrateur where(email='" + tfemaillogin.getText() + "' and password='" + pswfieldlogin.getText() + "')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            administrateur admis;
            while (rs.next()) {
                admis = new administrateur(rs.getInt("Id_admin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("photo"), rs.getString("email"), rs.getString("password"));
                adminList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public ObservableList<Professeur> getProfList() {
        ObservableList<Professeur> profList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  professeur where(email='" + tfemaillogin.getText() + "' and password='" + pswfieldlogin.getText() + "')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Professeur admis;
            while (rs.next()) {
                admis = new Professeur(rs.getInt("Id_professeur"), rs.getString("nom"), rs.getString("prenom"), rs.getString("photo"), rs.getString("email"), rs.getString("password"), rs.getString("specialite"), rs.getString("profil"));
                profList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profList;
    }

    public ObservableList<ApprenantEntity> getApprenantList() {
        String test50 = "True";
        ObservableList<ApprenantEntity> apprenantList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  apprenant where(email='" + tfemaillogin.getText() + "' and password='" + pswfieldlogin.getText() + "' and status='" + test50 + "')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ApprenantEntity admis;
            while (rs.next()) {
                admis = new ApprenantEntity(rs.getInt("id_apprenant"), rs.getString("nom"), rs.getString("prenom"), rs.getString("photo"), rs.getString("email"), rs.getString("password"), rs.getNString("status"));
                apprenantList.add(admis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apprenantList;
    }

    public ObservableList<ApprenantEntity> getNonactiveList() {
        ObservableList<ApprenantEntity> apprenantList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from  apprenant where(status='" + status1 + "')";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            ApprenantEntity admis;
            while (rs.next()) {
                admis = new ApprenantEntity(rs.getInt("id_apprenant"), rs.getString("nom"), rs.getString("prenom"), rs.getString("photo"), rs.getString("email"), rs.getString("password"), rs.getNString("status"));
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void gotoregister(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/Register.fxml"));
        // URL url = Paths.get("./src/sample/Views/Register.fxml").toUri().toURL();
        //Parent root = FXMLLoader.load(url);


        Stage window = (Stage) testbutton.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));


    }

    @FXML
    public void homeClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));

        // URL url = Paths.get("./src/sample/Views/SideBar.fxml").toUri().toURL();
        //     Parent root = FXMLLoader.load(url);

        Stage window = (Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));


    }

    public void gobackbro(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window = (Stage) idreturn.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));
    }

    public void registerdone(MouseEvent event) throws IOException {

        String query = "insert into apprenant (nom,prenom,photo,email,password) values ('" + tfnom.getText() + "','" + tfprenom.getText() + "','" + imagePath + "','" + tfemail.getText() + "','" + tfpassword.getText() + "')";


        if (executeQuery(query)) {

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

    public void loginClick(MouseEvent event) throws IOException {

        ObservableList<administrateur> list = getAdminList();
        ObservableList<Professeur> list2 = getProfList();
        ObservableList<ApprenantEntity> list3 = getApprenantList();


        if (list2.isEmpty() && list.isEmpty() && list3.isEmpty()) {
            String title = "Congratulations sir";
            String message = "You've successfully Failed to login ";
            NotificationType notification = NotificationType.ERROR;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();
        } else if (list2.isEmpty() && list3.isEmpty()) {

            Parent root = FXMLLoader.load(getClass().getResource("/vue/SideBar.fxml"));

            Stage window = (Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root, 1370, 700));
            new FadeIn(root).play();

        } else if
        (list.isEmpty() && list3.isEmpty()) {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Professeurhome.fxml"));
            Parent root = loader.load();
            Emploidetemps pc = loader.getController();
            pc.setid(tfemaillogin.getText());

            Stage window = (Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root, 1370, 700));
            new FadeIn(root).play();

        } else if (list2.isEmpty() && list.isEmpty()) {
            //   Parent root = FXMLLoader.load(getClass().getResource("/vue/Apprenanthome.fxml"));


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Apprenanthome.fxml"));
            Parent root = loader.load();
            Emploidetemps pc = loader.getController();
            pc.setid(tfemaillogin.getText());

            Stage window = (Stage) btnlogin.getScene().getWindow();
            window.setScene(new Scene(root, 1370, 700));
            new FadeIn(root).play();
        } else
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
        ApprenantEntity apprenant = tvapp.getSelectionModel().getSelectedItem();

        tfnom1.setText(apprenant.getNom());
        tfprenom1.setText(apprenant.getPrenom());
        tfid1.setText(" " + apprenant.getId_apprenant());
        tfmail1.setText(" " + apprenant.getEmail());

        Image image = new Image("file:///" + apprenant.getPhoto());
        imageTactor.setImage(image);
        imagePath = apprenant.getPhoto();

    }

    public void Unblockblock() throws Exception {
        String query = "UPDATE apprenant SET status = 'True' WHERE id_apprenant =" + tfid1.getText() + "";

        executeQuery(query);
        getNonactiveList();
        sendEmail();

    }
    public void sendEmail(){
String mail=tfmail1.getText();
        String emailToField=mail;
        String emailFromField="m.benzarti.1996@gmail.com";
        String to = tfmail1.getText();
        String from = emailFromField;
        String host = "smtp.gmail.com";
        final String username = "m.benzarti.1996@gmail.com";
        final String password = "redtube96";

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Activation Account");
            m.setText("Votre Compte a ete bien debloque par le patron <3 ");

            //send mail

            Transport.send(m);

            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }


    public void GoToApprenantRestPasswrd(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieApprenant.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window = (Stage) passwordforget.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));

    }

    public void GoToApprenantRestPasswrd2(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieApprenant.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window = (Stage) passwordforgetButton.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));

    }

    public void GoToresetPasswordTeacher(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/MotdePasseoublieProf.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window = (Stage) resetteacherlink.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));
    }

    public void RestPasswordoneApprenant(MouseEvent event) throws IOException {
        if (tfconfirmpassword.getText().equals(tfpasswordreset.getText()) && EmailTest(tfemailreset.getText())==true) {
            String query = "UPDATE apprenant SET password='" + tfpasswordreset.getText() + "' WHERE nom = '" + tfnomreset.getText() + "' and prenom ='" + tfprenom.getText() + "' and email ='" + tfemailreset.getText() + "';";


            executeQuery(query);

            if (executeQuery(query)) {

                String title = "Congratulations sir";
                String message = "You've successfully Changed your Password  ";
                NotificationType notification = NotificationType.CUSTOM.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndWait();
                sendEmail2();
            }
        } else {
            String title = "Congratulations sir";
            String message = "You've successfully Entred wrong password  or your accound dont exist    ";
            NotificationType notification = NotificationType.ERROR;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();
        }
    }
    public void sendEmail2(){
        String mail=tfemailreset.getText();

        String pass=tfconfirmpassword.getText();

        String emailToField=mail;
        String emailFromField="m.benzarti.1996@gmail.com";
        String to = tfemailreset.getText();
        String from = emailFromField;
        String host = "smtp.gmail.com";
        final String username = "m.benzarti.1996@gmail.com";
        final String password = "redtube96";

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Your password have been changed");
            m.setText("Your Password have been changed to "+pass);

            //send mail

            Transport.send(m);

            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }

    public void RestPasswordoneProf(MouseEvent event) {
        if (tfconfirmpassword.getText().equals(tfpasswordreset.getText()) && EmailTest(tfemailreset.getText())==true) {

            String query = "UPDATE professeur SET password='" + tfpasswordreset.getText() + "' WHERE nom = '" + tfnomreset.getText() + "' and prenom ='" + tfprenom.getText() + "' and email ='" + tfemailreset.getText() + "';";

            executeQuery(query);

            String title = "Congratulations sir";
            String message = "You've successfully Changed your Password  ";
            NotificationType notification = NotificationType.CUSTOM.SUCCESS;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndWait();
            sendEmail2();
        }

            else {

                String title = "Congratulations sir";
                String message = "You've successfully Entred wrong password  or your accound dont exist    ";
                NotificationType notification = NotificationType.ERROR;

                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndWait();
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

  /*  public void hoho(MouseEvent event) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);

        if(!camera.isOpened()){
            System.out.println("Error");
        }
        else {
            Mat frame = new Mat();
            while(true){
                if (camera.read(frame)){
                    System.out.println("Frame Obtained");
                    System.out.println("Captured Frame Width " +
                            frame.width() + " Height " + frame.height());
                    Highgui.imwrite("camera.jpg", frame);
                    System.out.println("OK");
                    break;
                }
            }
        }
        camera.release();
    }
*/
public boolean EmailTest(String testmail)
{
   String email="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    Pattern emailpat=Pattern.compile(email,Pattern.CASE_INSENSITIVE);
    Matcher matcher=emailpat.matcher(testmail);
   return matcher.find();
}


}