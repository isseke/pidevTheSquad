package Modele;

public class User {

    private String nom;
    private String prenom;
    private String photo;
    private String email;
    private String password;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String nom, String prenom, String photo, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.email = email;
        this.password = password;
    }
}
