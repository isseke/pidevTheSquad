package Modele;

public class Professeur extends User {
    private int Id_professeur;
private String specialite;
private String profil;

    public Professeur(int Id_professeur, String nom, String prenom, String photo, String email, String password, String specialite, String profil) {
        super(nom,prenom,photo,email,password);
        this.Id_professeur =Id_professeur;
        this.specialite=specialite;
        this.profil=profil;

    }



    public int getId() {
        return Id_professeur;
    }

    public void setId_professeur(int id) {
        this.Id_professeur= id;
    }



    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
