/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    
     int id,id_apprenant;
     String title,date,etat;

    public Reclamation(int id, String title, String date, String etat, int id_apprenant) {
        this.id = id;
        this.id_apprenant = id_apprenant;
        this.title = title;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation( String title, String date , int id_apprenant) {
        this.id_apprenant = id_apprenant;
        this.title = title;
        this.date = date;
    }

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_apprenant() {
        return id_apprenant;
    }

    public void setId_apprenant(int id_apprenant) {
        this.id_apprenant = id_apprenant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String description) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String answer) {
        this.etat = etat;
    }
    
}
