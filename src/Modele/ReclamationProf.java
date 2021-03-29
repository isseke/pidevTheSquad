/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author ASUS
 */
public class ReclamationProf {
    int id;
    String title,etat,date,recl ,nom_user ,reclmodif ,exp ,msg ,msgA;

    public ReclamationProf(int id, String title, String date, String etat, String nom_user ,String recl ,String reclmodif ,String exp ,String msg ,String msgA) {
        this.id = id;
        this.nom_user = nom_user;
        this.title = title;
        this.date = date;
        this.etat = etat;
        this.recl = recl;
        this.reclmodif = reclmodif;
        this.exp = exp;
        this.msg = msg;
        this.msgA = msgA;
    }

    public ReclamationProf(String etat ,String recl ,String date ,String reclmodif ,String exp ,String msg ,String msgA) {
        this.etat = etat ;
        this.recl = recl ;
        this.date = date ;
        this.reclmodif = reclmodif ;
        this.exp = exp ;
        this.msg = msg ;
        this.msgA =msgA;}

    public ReclamationProf() { }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom_user() {
        return nom_user;
    }
    public void setNom_user(String nom_user) { this.nom_user = nom_user; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) { this.date = date; }

    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRecl() {
        return recl;
    }
    public void setRecl(String recl) {
        this.recl = recl;
    }

    public String getReclmodif() {
        return reclmodif;
    }
    public void setReclmodif(String reclmodif) {
        this.reclmodif = reclmodif;
    }

    public String getExp() {
        return exp;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) { this.msg = msg; }

    public String getMsgA() {
        return msgA;
    }
    public void setMsgA(String msgA) {
        this.msgA = msgA;
    }

}
