package Modele;

public class administrateur extends User {
    private int Id_admin;

    public administrateur(int Id_admin,String nom, String prenom, String photo, String email, String password) {
        super(nom, prenom, photo, email, password);
        this.Id_admin=Id_admin;


    }

    public int getId_admin() {
        return Id_admin;
    }

    public void setId_admin(int id_admin) {
        Id_admin = id_admin;
    }
}
