package Gestionnaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    public Button addemploiprof;
    @FXML
    public DatePicker dp1;
    @FXML
    public DatePicker dp2;
    @FXML
    public DatePicker dp3;



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
        Parent root = FXMLLoader.load(getClass().getResource("/vue/Emploidetempsprofaffiche.fxml"));

        //  URL url = Paths.get("./src/sample/Views/login.fxml").toUri().toURL();
        //  Parent root = FXMLLoader.load(url);

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
        ObservableList<Modele.Emploidetemps> EmploiList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM emploidetemps ";
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
    @FXML
    public void homeClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/Professeurhome.fxml"));


        Stage window=(Stage) testpro.getScene().getWindow();
        window.setScene(new Scene(root,1370,700));
    }

    public void GoToaddpageemploi(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/AddEmploitempsProf.fxml"));

Stage stage=new Stage();
stage.setScene(new Scene(root,850,500));
stage.show();
    }

    @FXML
    public void insertButton() {
        Integer idtest=29;
        String idtest2="29";

        // String query = "insert into professeur values (nom='"+tfnom.getText()+"',prenom='"+tfprenom.getText()+"',photo='"+tfphoto.getText()+"',email='"+tfemail.getText()+",password='"+tfpassword.getText()+"',specialite='"+tfspecialite.getText()+"',profil='"+tfprofil.getText()+"')";
        String query = "insert into emploidetemps (datedebutvalidite,datefinvalidite,dateajoutemploi,emploi,idprof) values ('"+dp1.getValue()+"','"+dp2.getValue()+"','"+dp3.getValue()+"','"+idtest+"','"+idtest2+"')";


        executeQuery(query);
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













}
