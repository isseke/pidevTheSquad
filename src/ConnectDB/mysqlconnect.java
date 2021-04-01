package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modele.Formation;
import Gestionnaire.ControllerFormation;
import javax.swing.JOptionPane;


public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pidev","root","");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<Formation> getDataformation(){
        Connection conn = ConnectDb();
        ObservableList<Formation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from formation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Formation(Integer.parseInt(rs.getString("id_formation")), rs.getString("intitule"), rs.getString("date_debut"), rs.getString("date_fin"), rs.getString("volume_horaire"),rs.getString("langue"),rs.getString("mode_enseignement")));
            }
        } catch (Exception e) {
        }
        return list;
    }










}