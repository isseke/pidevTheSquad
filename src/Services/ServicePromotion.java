/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectDB.DataBase;
import Interface.InterCrud;
import java.sql.Connection;
import Modele.Promotion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ObservableList<Promotion> PromoAnnee = FXCollections.observableArrayList();
    private ObservableList<Promotion> recherchePromo = FXCollections.observableArrayList();
    private ObservableList<Promotion> initPromo = FXCollections.observableArrayList();

    @Override
    public void ajouter(Promotion e) {

        try {
            PreparedStatement prest = cnx.prepareStatement("INSERT INTO promotion (promotion,raison_promotion,date_promotion,id_professeur, id_apprenant) VALUES (?,?,?, (SELECT Id_professeur from professeur WHERE nom = ? and prenom = ?),(SELECT id_apprenant from apprenant WHERE nom = ? and prenom = ?)) ");
            prest.setString(1,e.getPromotion());
            prest.setString(2,e.getRaison());
            prest.setString(3,e.getDate());
            prest.setString(4,e.getNomProf());
            prest.setString(5,e.getPrenomProf());
            prest.setString(6,e.getNomApprenant());
            prest.setString(7,e.getPrenomApprenant());
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
        System.out.println("GICKEL a MODIFIER LA PROMOTION");
        
        try {

            PreparedStatement prest = cnx.prepareStatement("UPDATE promotion set promotion=? ,raison_promotion = ?, date_promotion= ?, id_professeur= (SELECT Id_professeur from professeur WHERE nom = ? and prenom = ?), id_apprenant = (SELECT id_apprenant from apprenant WHERE nom = ? and prenom = ?) WHERE id_promotion = ? ");
            prest.setString(1,e.getPromotion());
            prest.setString(2,e.getRaison());
            prest.setString(3,e.getDate());
            prest.setString(4,e.getNomProf());
            prest.setString(5,e.getPrenomProf());
            prest.setString(6,e.getNomApprenant());
            prest.setString(7,e.getPrenomApprenant());
            prest.setInt(8, e.getId_promotion());
            
            prest.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public ObservableList<Promotion> promotionAnnee(){
               SimpleDateFormat s = new SimpleDateFormat("yyyy");
               Date date = new Date();
       
               String annee =  s.format(date);
        
        try {
           PreparedStatement st = cnx.prepareStatement("SELECT promotion, raison_promotion, date_promotion,apprenant.nom as nomApprenant, apprenant.prenom as prenomApprenant from promotion, apprenant where promotion.id_apprenant = apprenant.id_apprenant and date_promotion like ?");
            
            st.setString(1, "%"+annee+"%");
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
               
                 String apprenantName = set.getString("nomApprenant");
                 String apprenantPrenam = set.getString("prenomApprenant");
 
                 String appr = apprenantName +" "+apprenantPrenam;
 
                 PromoAnnee.add(new Promotion(set.getString("promotion"),set.getString("raison_promotion"), set.getString("date_promotion"),appr));
            
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return PromoAnnee;
    }
    
    /**
     * les promotion recherché par intitule
     */
    public ObservableList<Promotion> recherchePromotion(String promotion){
        
        try {
          
            PreparedStatement st = cnx.prepareStatement("SELECT promotion, raison_promotion, date_promotion,apprenant.nom as nomApprenant, apprenant.prenom as prenomApprenant from promotion, apprenant where promotion.id_apprenant = apprenant.id_apprenant and promotion like ?");
            st.setString(1, "%"+promotion+"%");
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
               
                 String apprenantName = set.getString("nomApprenant");
                 String apprenantPrenam = set.getString("prenomApprenant");
 
                 String appr = apprenantName +" "+apprenantPrenam;
 
                 recherchePromo.add(new Promotion(set.getString("promotion"),set.getString("raison_promotion"), set.getString("date_promotion"),appr));
            
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recherchePromo;
    }
    
    public ObservableList<Promotion> initPromotion(){
        
        try {
          
            PreparedStatement st = cnx.prepareStatement("SELECT promotion, raison_promotion, date_promotion,apprenant.nom as nomApprenant, apprenant.prenom as prenomApprenant from promotion, apprenant where promotion.id_apprenant = apprenant.id_apprenant");
           
            
            ResultSet set = st.executeQuery();
             while(set.next()){
                 
               
                 String apprenantName = set.getString("nomApprenant");
                 String apprenantPrenam = set.getString("prenomApprenant");
 
                 String appr = apprenantName +" "+apprenantPrenam;
 
                 initPromo.add(new Promotion(set.getString("promotion"),set.getString("raison_promotion"), set.getString("date_promotion"),appr));
            
             }
 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return initPromo;
    }
    
}
