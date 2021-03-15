/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

/**
 *
 * @author gicke
 */
public class Promotion {
    private IntegerProperty id_promotion;
    private IntegerProperty id_apprenant;
    private IntegerProperty id_professeur;
    private StringProperty promotion;
    private StringProperty date;
    private StringProperty raison;
  
    private StringProperty nomProf;
    private StringProperty nomApprenant;
    private StringProperty prenomProf;
    private StringProperty prenomApprennt;
    

    
    
    
    
    public int getId_promotion() {
        return id_promotion.get();
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion.set(id_promotion);
    }

    public IntegerProperty id_promotion_property() {
		return id_promotion;
    }


    public int getId_apprenant() {
        return id_apprenant.get();
    }

    public void setId_apprenant(int id_apprenant) {
        this.id_apprenant.set(id_apprenant);
    }

    public IntegerProperty id_appenant_property() {
        return id_apprenant;
    }


    public int getId_professeur() {
        return id_professeur.get();
    }

    public void setId_professeur(int id_professeur) {
        this.id_professeur.set(id_professeur);
    }

    public IntegerProperty id_professer_property() {
        return id_professeur;
    }

    
    public String getPromotion() {
        return promotion.get();
    }

    public void setPromotion(String promotion) {
        this.promotion.set(promotion);
    }
    
    public StringProperty promotion_property() {
		return promotion;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
    
    public StringProperty date_property() {
		return date;
    }

    public String getRaison() {
        return raison.get();
    }

    public void setRaison(String raison) {
        this.raison.set(raison);
    }
    
    public StringProperty raison_property() {
		return raison;
    }

//    public int getApprenant() {
//        return apprenant.get();
//    }
//
//    public void setApprenant(int apprenant) {
//        this.apprenant.set(apprenant);
//    }
//    public IntegerProperty apprenant_property() {
//		return apprenant;
//    }
//    public int getProfesseur() {
//        return professeur.get();
//    }

//    public void setProfesseur(int professeur) {
//        this.professeur.set(professeur);
//    }
//    
//    public IntegerProperty professeur_property() {
//		return professeur;
//    }
    
    
  
    
    ///////////////////////////////////Deuxième partie/////////
    
   
    public String getNomProf() {
        return nomProf.get();
    }

    public void setNomProf(String nomProf) {
        this.nomProf.set(nomProf);
    }
    
    public StringProperty nomProf_property() {
		return nomProf;
    }

    public String getNomApprenant() {
        return nomApprenant.get();
    }

    public void setNomApprenant(String nomProf) {
        this.nomApprenant.set(nomProf);
    }
    
    public StringProperty nomApprenant_property() {
		return nomApprenant;
    }
    public String getPrenomProf() {
        return prenomProf.get();
    }

    public void setprenomProf(String nomProf) {
        this.prenomProf.set(nomProf);
    }
    
    public StringProperty prenomProf_property() {
		return prenomProf;
    }
    public String getPrenomApprenant() {
        return prenomApprennt.get();
    }

    public void setprenomApprenant(String prenomapprenant) {
        this.prenomApprennt.set(prenomapprenant);
    }
    
    public StringProperty prenomApprenant_property() {
		return prenomApprennt;
    }
    
    
    private StringProperty professeur;
    
    
    
    

    public Promotion(int id_promotion, String promotion, String date, String raison,String professeur, String apprenant) {
        this.id_promotion = new SimpleIntegerProperty(id_promotion);      
        this.promotion = new SimpleStringProperty(promotion);
        this.date = new SimpleStringProperty(date);
        this.raison = new SimpleStringProperty(raison);
        this.nomProf = new SimpleStringProperty(professeur);
        this.nomApprenant = new SimpleStringProperty(apprenant);
    }
    public Promotion(String promotion, String date, String raison, String apprenant, String professeur) {     
        this.promotion = new SimpleStringProperty(promotion);
        this.date = new SimpleStringProperty(date);
        this.raison = new SimpleStringProperty(raison);
        this.nomProf = new SimpleStringProperty(professeur);
        this.nomApprenant = new SimpleStringProperty(apprenant);
    }

    public Promotion(String promotion, String date, String raison, int id_apprenant, int id_professeur) {
        this.promotion = new SimpleStringProperty(promotion);
        this.date = new SimpleStringProperty(date);
        this.raison = new SimpleStringProperty(raison);
        this.id_professeur = new SimpleIntegerProperty(id_professeur);
        this.id_apprenant = new SimpleIntegerProperty(id_apprenant);
    }
    
        public Promotion(String promotion, String date, String raison, String nomapprenant, String prenomapprenant, String nomprofesseur, String prenomprofesseur) {     
        this.promotion = new SimpleStringProperty(promotion);
        this.date = new SimpleStringProperty(date);
        this.raison = new SimpleStringProperty(raison);
        this.nomProf = new SimpleStringProperty(nomprofesseur);
        this.nomApprenant = new SimpleStringProperty(nomapprenant);
         this.prenomProf = new SimpleStringProperty(prenomprofesseur);
        this.prenomApprennt = new SimpleStringProperty(prenomapprenant);
    }
    
    
    
    
}
