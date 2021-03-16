package Modele;

public class ApprenantEntity extends User {

    private int id_apprenant;
    private String status;


    public int getId_apprenant() {
        return id_apprenant;
    }

    public void setId_apprenant(int id_apprenant) {
        this.id_apprenant = id_apprenant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ApprenantEntity(int id_apprenant, String nom, String prenom, String photo, String email, String password,String status) {
      super(nom,prenom,photo,email,password);

        this.id_apprenant = id_apprenant;
        this.status=status;

    }
    public boolean  equals(Object obj)
    {
        if(obj != null && obj.getClass()== this.getClass()) {  //meme type classe
            ApprenantEntity p = (ApprenantEntity) obj;
            return p.status.equals(this.status);
        }
        else return false;

    }
}
