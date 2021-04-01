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
import java.util.ArrayList;
import java.util.List;
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
    private ObservableList<Evenement> prochainEvent = FXCollections.observableArrayList();
    private ObservableList<Evenement> prochainEvents = FXCollections.observableArrayList();
    private ObservableList<Evenement> semaineEvent = FXCollections.observableArrayList();
    private ObservableList<Evenement> rechercheEvent = FXCollections.observableArrayList();
    private ArrayList<Evenement>   prochainEventP = new ArrayList<Evenement>();
    private ArrayList<Evenement>   prochainEventR = new ArrayList<Evenement>();
    private ArrayList<Evenement>   prochainEventPP = new ArrayList<Evenement>();


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
    
    /**
     * 
     * La liste de tous les evenements
     */
    public ObservableList<Evenement> prochainEvenement(){
        
        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement");
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){
      
                prochainEvent.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement")));   
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prochainEvent;
    }


   
    
    /**
     * la liste des prochains evenement
     */
    public ArrayList<Evenement> prochainEvenementp(){
        
        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement ");
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){
      
                prochainEventP.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement"),prochainEvenementSelect.getBinaryStream("image")));   
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prochainEventP;
    }

    /**
     * prochaine event non pr
     * @return
     */
    public ArrayList<Evenement> prochainEvenementpp(){

        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement where date_evenement > CURRENT_DATE ");
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){

                prochainEventPP.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement"),prochainEvenementSelect.getBinaryStream("image")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prochainEventPP;
    }
    public ArrayList<Evenement> prochainEvenementR(String date){

        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement where date_evenement = ? ");
            prEvt.setString(1, date);

            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){

                prochainEventR.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement"),prochainEvenementSelect.getBinaryStream("image")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prochainEventR;
    }
     public ObservableList<Evenement> prochainEvenements(){
        
        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement where date_evenement > CURRENT_DATE ");
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){
      
                prochainEvents.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement")));   
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prochainEvents;
    }
    
    
    /**
     * les evenements recherché par date
     */
    public ObservableList<Evenement> rechercheEvenements(String date){
        
        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement where date_evenement = ? ");
            prEvt.setString(1, date);
            
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){
      
                rechercheEvent.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement")));   
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rechercheEvent;
    }
    
    public ObservableList<Evenement> SemainEvenements(String date){
        
        try {
            PreparedStatement prEvt = cnx.prepareStatement("SELECT * from evenement where date_evenement = ? ");
            prEvt.setString(1, date);
            
            ResultSet prochainEvenementSelect = prEvt.executeQuery();
            while(prochainEvenementSelect.next()){
      
                semaineEvent.add(new Evenement(prochainEvenementSelect.getString("theme"),prochainEvenementSelect.getString("lien"),prochainEvenementSelect.getString("presentateur"),prochainEvenementSelect.getString("date_evenement")));   
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return semaineEvent;
    }
    
    
    
    
}
