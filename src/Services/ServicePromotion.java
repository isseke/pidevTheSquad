/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectDB.DataBase;
import Interface.InterCrud;
import Modele.Evenement;
import java.sql.Connection;
import Modele.Promotion;
import Services.ServiceEvenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gicke
 */
public class ServicePromotion implements InterCrud<Promotion>{
    
    Connection cnx ;
    public ServicePromotion() {
        
         cnx = DataBase.getInstance().getConn();
        
    }
    
    private ObservableList<Promotion> PromotionList = FXCollections.observableArrayList();
    private ObservableList<String> promoprof = FXCollections.observableArrayList();
    private ObservableList<String> Promoapprenant = FXCollections.observableArrayList();

    @Override
    public void ajouter(Promotion e) {
        int apprenantID, profId;
        
        try {
            PreparedStatement st = cnx.prepareStatement("SELECT id_apprenant,Id_professeur from  ");
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
                 String profName = set.getString("nomProf");

             }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            PreparedStatement prest = cnx.prepareStatement("INSERT INTO promotion(promotion, raison_promotion,date_promotion,id_professeur, id_apprenant  ) VALUES (?,?,?,?,?)" +
                    "WHERE ");
          //  prest.setString(1,e.getLien());


            prest.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(Promotion e) {

        try {
            PreparedStatement pst = cnx.prepareStatement("delete from promotion where id_promotion = ? ");
            
            pst.setInt(1, e.getId_promotion());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Promotion> Consulter() {
        
         try {
            PreparedStatement st = cnx.prepareStatement("SELECT id_promotion, promotion, raison_promotion, date_promotion, professeur.nom as nomProf, professeur.prenom as prenomProf, apprenant.nom as nomApprenant, apprenant.prenom as prenomApprenant from promotion,professeur, apprenant where promotion.id_professeur = professeur.Id_professeur and promotion.id_apprenant = apprenant.id_apprenant ");
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
                 String profName = set.getString("nomProf");
                 String profPrenam = set.getString("prenomProf");
                 String apprenantName = set.getString("nomApprenant");
                 String apprenantPrenam = set.getString("prenomApprenant");
                 
                 String prof = profName +" "+profPrenam;
                 String appr = apprenantName +" "+apprenantPrenam;
 
                 PromotionList.add(new Promotion(set.getInt("id_promotion"), set.getString("promotion"), set.getString("date_promotion"), set.getString("raison_promotion"), prof, appr));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return PromotionList;
    }
    
    public ObservableList<String> afficheProff(){
        
        try {
            PreparedStatement st = cnx.prepareStatement("SELECT nom, prenom FROM professeur ");
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
                 String nom = set.getString("nom");
                 String prenom = set.getString("prenom");
                 
                 String prof = nom +"  "+prenom;
                 promoprof.add(prof);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        return promoprof;
    }
    
    public ObservableList<String> afficheApprenant(){
        
        try {
            PreparedStatement st = cnx.prepareStatement("SELECT nom, prenom FROM apprenant ");
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
                 String nom = set.getString("nom");
                 String prenom = set.getString("prenom");
                 
                 String apprenant = nom +"  "+prenom;
                 Promoapprenant.add(apprenant);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        return Promoapprenant;
    }
    
}
