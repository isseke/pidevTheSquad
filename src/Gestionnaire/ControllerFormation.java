package Gestionnaire;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import ConnectDB.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Modele.Formation;
import ConnectDB.mysqlconnect;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.swing.*;


public class ControllerFormation implements Initializable {


    @FXML
    private TableView<Formation> table_Formation;
    @FXML
    private TableView<Formation> table_Formation1;

    @FXML
    private TableColumn<Formation, Integer> col_id;

    @FXML
    private TableColumn<Formation, String> col_intitule;

    @FXML
    private TableColumn<Formation, String> col_debut;

    @FXML
    private TableColumn<Formation, String> col_fin;

    @FXML
    private TableColumn<Formation, String> col_langue;
    @FXML
    private TableColumn<Formation, String> col_mode;
    @FXML
    private TableColumn<Formation,Integer> col_volume;



    @FXML
    private TableColumn<Formation, Integer> col_id1;

    @FXML
    private TableColumn<Formation, String> col_intitule1;

    @FXML
    private TableColumn<Formation, String> col_debut1;

    @FXML
    private TableColumn<Formation, String> col_fin1;

    @FXML
    private TableColumn<Formation, String> col_langue1;
    @FXML
    private TableColumn<Formation, String> col_mode1;
    @FXML
    private TableColumn<Formation,Integer> col_volume1;


    @FXML
    private Button btn_inscription ;
    @FXML
    private Button btn_des ;
    @FXML
    private Button dreturn;

    ObservableList<Formation> listM=null;
    ObservableList<Formation> listM1=null;
    ObservableList<Formation> dataList;
@FXML
public TextField mohamed;

    @FXML
    public TextField tfintitule;
    @FXML
    public ComboBox cblangue;
    @FXML
    public DatePicker dpdebut;
    @FXML
    public DatePicker dpfin;
    @FXML
    public TextField tfvolume;
    @FXML
    public Label id_formation;


    public void homeClickApprenant(MouseEvent event) throws IOException {
        //  Parent root = FXMLLoader.load(getClass().getResource("/vue/Apprenanthome.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Apprenanthome.fxml"));
        Parent root = loader.load();
        ControllerFormation pc = new ControllerFormation();
        pc = loader.getController();

        Stage window = (Stage) dreturn.getScene().getWindow();
        window.setScene(new Scene(root, 1370, 700));  }

    public void setid(String text) {
        this.id_formation.setText("" + text);
    }

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  ObservableList<Formation> getDataformation1(){
        Connection connection = getConnection();
        ObservableList<Formation> list = FXCollections.observableArrayList();
        try {

            String k=mohamed.getText();

           String query="SELECT formationapprenant.id_formation FROM formationapprenant WHERE id_apprenant=)";


            Statement st;
            ResultSet rs;


                st = connection.createStatement();
                rs = st.executeQuery(query);

            System.out.println(rs.first());





            while (rs.next()){
                list.add(new Formation(Integer.parseInt(rs.getString("id_formation")), rs.getString("intitule"), rs.getString("date_debut"), rs.getString("date_fin"), rs.getString("volume_horaire"),rs.getString("langue"),rs.getString("mode_enseignement")));

                System.out.println(list);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Integer query() { // returner id
        try {
            String req = "SELECT id_apprenant FROM apprenant where email =('" + id_formation.getText() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Integer idu = rs.getInt("id_apprenant");
                return idu; }
        } catch (SQLException ex) { System.out.println(ex.getMessage()); } return 0;}










/*
public void select_id_formation(){
    conn = mysqlconnect.ConnectDb();
    String sql = "SELECT id_apprenant from apprenant (id_apprenant)values(?,?)";
    try {
        pst = conn.prepareStatement(sql);
        pst.setString(1,col_id.getText());
        pst.setString(2,);

        pst.execute();

        JOptionPane.showMessageDialog(null, "Users Add succes");
        UpdateTable();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    return id;
}
*/


    public void executeQuery(String query) {
        conn = mysqlconnect.ConnectDb();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Add_formation (){




        Formation formation=table_Formation.getSelectionModel().getSelectedItem();
        String combo= (String) cblangue.getValue();

        String date_debut=(dpdebut.getValue()).toString();
        System.out.println(date_debut);


        String date_fin=(dpfin.getValue()).toString();
        System.out.println(date_fin);
       String  intitule=tfintitule.getText();
       Integer volume= Integer.parseInt(tfvolume.getText().toString());
String d="ecole";

        //Integer id_formation=formation.getId_formation();
        //Integer id_apprenant=7;
        conn = mysqlconnect.ConnectDb();
        String sql = " Insert into formation(intitule,date_debut,date_fin,volume_horaire,langue,mode_enseignement)values('"+intitule+"','"+date_debut+"','"+date_fin+"','"+volume+"','"+combo+"','"+d+"')";
        try {



            executeQuery(sql);
UpdateTable1();

            JOptionPane.showMessageDialog(null, "Users Add succes");
            //UpdateTable();


        } catch (Exception e) {
        }
    }


    //////// methode select users ///////
    /*
    @FXML
    void getSelected (MouseEvent event){
        index = table_Formation.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }



    }
    */
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }




    
public  void mousegogo() throws ParseException {

        Formation formation=table_Formation.getSelectionModel().getSelectedItem();


        tfintitule.setText(formation.getIntitule());
        cblangue.setValue(formation.getLangue());
        tfvolume.setText(formation.getVolume_horaire());
        dpdebut.setValue(LOCAL_DATE(formation.getDate_debut()));
        dpfin.setValue(LOCAL_DATE(formation.getDate_fin()));




    }

    public void modifier(){
        Formation formation=table_Formation.getSelectionModel().getSelectedItem();
        String combo= (String) cblangue.getValue();

        String date_debut=(dpdebut.getValue()).toString();



        String date_fin=(dpfin.getValue()).toString();

        System.out.println(date_fin);
        String  intitule=tfintitule.getText();
        Integer volume= Integer.parseInt(tfvolume.getText().toString());
        String d="ecole";
        String query = "UPDATE formation SET intitule='"+intitule+"',date_debut='"+date_debut+"',date_fin='"+date_fin+"',volume_horaire='"+volume+"',langue='"+combo+"',mode_enseignement='"+d+"' WHERE id_formation ="+ formation.getId_formation()+"";

        executeQuery(query);
        UpdateTable1();



    }





/*
public void handlebuttonAction(ActionEvent event){

        if(event.getSource()==btn_inscription){

            Add_formation(formation);
        }
        else if(event.getSource()==btn_des){
          //  desinscription(index);
        }
}
*/


    public void Desinscription(){
        Formation formation=table_Formation.getSelectionModel().getSelectedItem();
        Integer id=formation.getId_formation();

        conn = mysqlconnect.ConnectDb();
        String sql = "delete from formation where (id_formation =?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id.toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable1();



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }




/*
    @FXML
    void search_formation() {
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));

        dataList = mysqlconnect.getDatausers();
        table_users.setItems(dataList);
        FilteredList<users> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

*/

    public void UpdateTable1(){
        col_id.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("id_formation"));
        col_intitule.setCellValueFactory(new PropertyValueFactory<Formation,String>("intitule"));
        col_debut.setCellValueFactory(new PropertyValueFactory<Formation,String>("date_debut"));
        col_fin.setCellValueFactory(new PropertyValueFactory<Formation,String>("date_fin"));
        col_volume.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("volume_horaire"));
        col_langue.setCellValueFactory(new PropertyValueFactory<Formation,String>("langue"));
        col_mode.setCellValueFactory(new PropertyValueFactory<Formation,String>("mode_enseignement"));

        listM = mysqlconnect.getDataformation();
        table_Formation.setItems(listM);
    }

    public void UpdateTable2() {




        col_id1.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));
        col_intitule1.setCellValueFactory(new PropertyValueFactory<Formation, String>("intitule"));
        col_debut1.setCellValueFactory(new PropertyValueFactory<Formation, String>("date_debut"));
        col_fin1.setCellValueFactory(new PropertyValueFactory<Formation, String>("date_fin"));
        col_volume1.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("volume_horaire"));
        col_langue1.setCellValueFactory(new PropertyValueFactory<Formation, String>("langue"));
        col_mode1.setCellValueFactory(new PropertyValueFactory<Formation, String>("mode_enseignement"));

        listM1 =getDataformation1();
        table_Formation1.setItems(listM1);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

ObservableList<String> k= FXCollections.observableArrayList("Anglais","Francais","Allemand","Espagnol");
cblangue.setItems(k);



        UpdateTable1();
        //UpdateTable2();
        // Code Source in description
    }
}