package Modele;

public class Formation {

    int id_formation ;
    String intitule,date_debut,date_fin,volume_horaire,langue,mode_enseignement;


    public Formation(int id_formation, String intitule, String date_debut, String date_fin, String volume_horaire, String langue, String mode_enseignement) {
        this.id_formation = id_formation;
        this.intitule = intitule;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.volume_horaire = volume_horaire;
        this.langue = langue;
        this.mode_enseignement = mode_enseignement;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getVolume_horaire() {
        return volume_horaire;
    }

    public void setVolume_horaire(String volume_horaire) {
        this.volume_horaire = volume_horaire;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getMode_enseignement() {
        return mode_enseignement;
    }

    public void setMode_enseignement(String mode_enseignement) {
        this.mode_enseignement = mode_enseignement;
    }
}