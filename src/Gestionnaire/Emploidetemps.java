package Gestionnaire;

import Modele.ApprenantEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Emploidetemps {
    @FXML
    public Button gogotoemploiteacher;
    @FXML
    public Button idreturn;
    @FXML
    public Button testpro;
    @FXML
    public Button emploidetempsapprenant;
    @FXML
    public Button addemploiprof;
    @FXML
    public DatePicker dp1;
    @FXML
    public DatePicker dp2;
    @FXML
    public DatePicker dp3;

    @FXML
    public Label test999;
    @FXML
    public Label test998;
    @FXML
    public Label test997;



    @FXML
    public ImageView imageTactor;
    String imagePath = null;


    @FXML
    public TableView<Modele.Emploidetemps> tvemploi;

    @FXML
    public TableColumn<Emploidetemps, Integer> idemploi;

    @FXML
    public TableColumn<Emploidetemps, Date> datedebutvalidite;

    @FXML
    public TableColumn<Emploidetemps, Date> datefinvalidite;

    @FXML
    public TableColumn<Emploidetemps, Date> dateajoutemploi;

    @FXML
    public TableColumn<Emploidetemps, Date> emploi;
    @FXML
    public TableColumn<Emploidetemps, Integer> idprof;




    public void GoToEmploideTempsprof(MouseEvent event) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("/vue/Emploidetempsprofaffiche.fxml"));


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Emploidetempsprofaffiche.fxml"));
        Parent root = loader.load();
        Emploidetemps pc = loader.getController();
       pc.setid2(test999.getText());



        Stage window = (Stage) gogotoemploiteacher.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));

    }
    public void gobackbro(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

        Stage window = (Stage) idreturn.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));
    }

    public void Listbouton(MouseEvent event) {
        showEmploi();
    }

    private void showEmploi() {
        ObservableList<Modele.Emploidetemps> list = getEmploiList();

        idemploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Integer>("idemploi"));
        datedebutvalidite.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("datedebutvalidite"));
        datefinvalidite.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("datefinvalidite"));
        dateajoutemploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("dateajoutemploi"));
        emploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("emploi"));

        idprof.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Integer>("idprof"));


        tvemploi.setItems(list);

    }

    private ObservableList<Modele.Emploidetemps> getEmploiList() {
        String idprof=test998.getText();

        ObservableList<Modele.Emploidetemps> EmploiList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM emploidetemps where emploidetemps.idprof =(select professeur.Id_professeur from professeur where email ='"+idprof+"')";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Modele.Emploidetemps emplois;
            while(rs.next()) {
                emplois = new Modele.Emploidetemps(rs.getInt("idemploi"),rs.getDate("datedebutvalidite"),rs.getDate("datefinvalidite"),rs.getDate("dateajoutemploi"),rs.getString("emploi"),rs.getInt("idprof"));
                EmploiList.add(emplois);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EmploiList;
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


    public void GoToaddpageemploi(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/AddEmploitempsProf.fxml"));
        Parent root = loader.load();
        Emploidetemps pc = loader.getController();
        pc.setid3(test998.getText());

       Stage stage=new Stage();
stage.setScene(new Scene(root,850,500));
stage.show();
    }

    @FXML
    public void insertButton() {
       Integer i=testquery();



       String query = "insert into emploidetemps (datedebutvalidite,datefinvalidite,dateajoutemploi,emploi,idprof) values ('"+dp1.getValue()+"','"+dp2.getValue()+"','"+dp3.getValue()+"','"+imagePath+"','"+i+"')";
       executeQuery(query);
        String title = "Congratulations sir";
        String message = "You've successfully Add your planning   ";
        NotificationType notification = NotificationType.CUSTOM.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndWait();



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


    public String FileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
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
    @FXML
    public void mouseclickgogogogogo(){
        Modele.Emploidetemps emploi= tvemploi.getSelectionModel().getSelectedItem();

        Image image = new Image("file:///"+emploi.getEmploi());
        imageTactor.setImage(image);
        imagePath=emploi.getEmploi();


    }


    public void setid(String text)
    {
        this.test999.setText(""+text);
    }

    public void setid2(String text)
    {
        this.test998.setText(""+text);
    }
    public void setid3(String text)
    {
        this.test997.setText(""+text);
    }


    public Integer testquery() {
        Connection connection = getConnection();
        String query = "SELECT Id_professeur FROM professeur where email =('" + test997.getText() + "')";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Integer lool = rs.getInt("Id_professeur");
                return lool;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void homeClickProf(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/Professeurhome.fxml"));


        Stage window=(Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));

    }

    public void ListerEmploibouton(MouseEvent event) {
        ObservableList<Modele.Emploidetemps> list = getEmploiListApprenant();

        idemploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Integer>("idemploi"));
        datedebutvalidite.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("datedebutvalidite"));
        datefinvalidite.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("datefinvalidite"));
        dateajoutemploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("dateajoutemploi"));
        emploi.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Date>("emploi"));

        idprof.setCellValueFactory(new PropertyValueFactory<Emploidetemps, Integer>("idprof"));

        tvemploi.setItems(list);

    }










    private ObservableList<Modele.Emploidetemps> getEmploiListApprenant() {
        String idApprenant=test998.getText();

        ObservableList<Modele.Emploidetemps> EmploiList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM emploidetemps where emploidetemps.idprof =(select professeur.Id_professeur from professeur where email ='"+idprof+"')";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Modele.Emploidetemps emplois;
            while(rs.next()) {
                emplois = new Modele.Emploidetemps(rs.getInt("idemploi"),rs.getDate("datedebutvalidite"),rs.getDate("datefinvalidite"),rs.getDate("dateajoutemploi"),rs.getString("emploi"),rs.getInt("idprof"));
                EmploiList.add(emplois);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EmploiList;
    }

    public void mouseclickgogogogogo2(MouseEvent event) {
    }
    public void GoToemploidetempsApprenant(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/AfficheEmploiTempsApprenant.fxml"));
        Parent root = loader.load();
        Emploidetemps pc = loader.getController();
        pc.setid2(test999.getText());



        Stage window = (Stage) emploidetempsapprenant.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));
    }

    public Integer testquery2() {
        Connection connection = getConnection();
        Integer idapprenant=testquery3();
        System.out.println(idapprenant);
        String query = "SELECT Id_professeur FROM interactiontwhoways where id_apprenant =('" +idapprenant+ "')";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Integer lool = rs.getInt("Id_professeur");
                return lool;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer testquery3() {
        Connection connection = getConnection();
        String query = "SELECT id_apprenant FROM apprenant where email =('" + test998.getText() + "')";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Integer lool = rs.getInt("Id_professeur");
                return lool;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}