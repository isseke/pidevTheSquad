package Modele;

public class SupportCours {

int id_supportCours;
String nom_cours;
String image_cours;
String date_depot;
String langue_cours;
int id_professeur;


    public SupportCours(int id_supportCours, String nom_cours, String image_cours, String date_depot, String langue_cours, int id_professeur) {
        this.id_supportCours = id_supportCours;
        this.nom_cours = nom_cours;
        this.image_cours = image_cours;
        this.date_depot = date_depot;
        this.langue_cours = langue_cours;
        this.id_professeur = id_professeur;
    }

    public int getId_supportCours() {
        return id_supportCours;
    }

    public void setId_supportCours(int id_supportCours) {
        this.id_supportCours = id_supportCours;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public String getImage_cours() {
        return image_cours;
    }

    public void setImage_cours(String image_cours) {
        this.image_cours = image_cours;
    }

    public String getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(String date_depot) {
        this.date_depot = date_depot;
    }

    public String getLangue_cours() {
        return langue_cours;
    }

    public void setLangue_cours(String langue_cours) {
        this.langue_cours = langue_cours;
    }

    public int getId_professeur() {
        return id_professeur;
    }

    public void setId_professeur(int id_professeur) {
        this.id_professeur = id_professeur;
    }
}
