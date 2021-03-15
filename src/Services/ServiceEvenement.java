/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectDB.DataBase;
import Modele.Evenement;
import java.sql.Connection;
import Interface.InterCrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gicke
 */
public class ServiceEvenement implements InterCrud <Evenement>{
    
    Connection cnx ;
    public ServiceEvenement() {
         cnx = DataBase.getInstance().getConn();
        
    }
    
    private ObservableList<Evenement> evenementList = FXCollections.observableArrayList();

    @Override
    public void ajouter(Evenement e) {
        try {
            PreparedStatement prest = cnx.prepareStatement("INSERT INTO evenement(lien,theme,date_evenement, presentateur ) VALUES (?,?,?,?)");
            prest.setString(1,e.getLien());
            prest.setString(2,e.getTheme());
            prest.setString(3,e.getDate());
            prest.setString(4,e.getPresentateur());
            
            prest.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Evenement e) {
        try {
            PreparedStatement pst = cnx.prepareStatement("delete from evenement where id_evenement = ? ");
            
            pst.setInt(1, e.getId_evenement());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Evenement e) {
        
        try {

            PreparedStatement st = cnx.prepareStatement("UPDATE evenement set lien = ?, theme = ? , date_evenement = ? , presentateur = ? where Id_evenement = ?");
            st.setString(1, e.getLien());
            st.setString(2, e.getTheme());
            st.setString(3, e.getDate());
            st.setString(4, e.getPresentateur());
            st.setInt(5, e.getId_evenement());
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Evenement> Consulter() {
       
        try {
            PreparedStatement st = cnx.prepareStatement("SELECT * FROM evenement");
            
            ResultSet set = st.executeQuery();
            
            while(set.next()){
                
                evenementList.add(new Evenement(set.getInt("Id_evenement"), set.getString("lien"), set.getString("theme"), set.getString("presentateur"), set.getString("date_evenement")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    return evenementList;
    }
    
}
