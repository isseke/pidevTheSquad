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
public class Reclamation {
    int id;
    String title;
    String etat;
    String date;
    String recl;
    int id_user;
    String reclmodif;
    String exp;
    String msg;
    String msgA;

    public Reclamation(int id, String title, String date, String etat, int id_user ,String recl ,String reclmodif ,String exp ,String msg ,String msgA) {
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.date = date;
        this.etat = etat;
        this.recl = recl;
        this.reclmodif = reclmodif;
        this.exp = exp;
        this.msg = msg;
        this.msgA = msgA;
    }

    public Reclamation(int id_user ,String title ,String date ,String recl ,String reclmodif ,String exp ,String msg ,String msgA) {
        this.id_user = id_user;
        this.title = title;
        this.recl = recl ;
        this.date = date ;
        this.reclmodif = reclmodif ;
        this.exp = exp ;
        this.msg = msg ;
        this.msgA =msgA;}

    public Reclamation() { }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) { this.id_user = id_user; }

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
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgA() {
        return msgA;
    }
    public void setMsgA(String msgA) {
        this.msgA = msgA;
    }

}
