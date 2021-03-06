/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.InputStream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gicke
 */
public class Evenement {
    private IntegerProperty id_evenement;
    private StringProperty lien;
    private StringProperty theme;
    private StringProperty presentateur;
    private StringProperty date;
    private StringProperty afficheEvenement;
    private InputStream inputStream;
    public Evenement(String lien, String theme, String presentateur, String date) {
        this.lien = new SimpleStringProperty(lien);
        this.theme = new SimpleStringProperty(theme);
        this.presentateur = new SimpleStringProperty(presentateur);
        this.date = new SimpleStringProperty(date);
        
    }
        public Evenement(String theme, String lien, String presentateur, String date, InputStream inputStream) {
        this.lien = new SimpleStringProperty(lien);
        this.theme = new SimpleStringProperty(theme);
        this.presentateur = new SimpleStringProperty(presentateur);
        this.date = new SimpleStringProperty(date);
        this.inputStream = inputStream;
        
    }
    

    public Evenement(int id_evenement, String lien, String theme, String presentateur, String date) {
        this.id_evenement = new SimpleIntegerProperty(id_evenement);
        this.lien = new SimpleStringProperty(lien);
        this.theme = new SimpleStringProperty(theme);
        this.presentateur = new SimpleStringProperty(presentateur);
        this.date = new SimpleStringProperty(date);
    }
     public Evenement(int id_evenement, String lien, String theme, String presentateur, String date,InputStream inputStream) {
        this.id_evenement = new SimpleIntegerProperty(id_evenement);
        this.lien = new SimpleStringProperty(lien);
        this.theme = new SimpleStringProperty(theme);
        this.presentateur = new SimpleStringProperty(presentateur);
        this.date = new SimpleStringProperty(date);
        this.inputStream = inputStream;
    }

    public int getId_evenement() {
        return id_evenement.get();
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement.set(id_evenement);
    }
    public IntegerProperty id_even_Property() {
		return id_evenement;
    }
    public String getLien() {
        return lien.get();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    
    

    public void setLien(String lien) {
        this.lien.set(lien);
    }
    public StringProperty LienProperty() {
		return lien;
    }
    public String getTheme() {
        return theme.get();
    }

    public void setTheme(String theme) {
        this.theme.set(theme);
    }
    
    public StringProperty ThemeProperty() {
		return theme;
    }

    public String getPresentateur() {
        return presentateur.get();
    }

    public void setPresentateur(String presentateur) {
        this.presentateur.set(presentateur);
    }
    
    public StringProperty PresentateurProperty() {
		return presentateur;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
    public StringProperty dateProperty() {
		return date;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", lien=" + lien + ", theme=" + theme + ", presentateur=" + presentateur + ", date=" + date + ", afficheEvenement=" + afficheEvenement + '}';
    }

        
    
}
