package Modele;

import java.util.Date;

public class Emploidetemps {
    private int idemploi;
    private Date datedebutvalidite;
    private Date datefinvalidite;
    private Date dateajoutemploi;
    private String emploi;
    private int idprof;

    public Emploidetemps(int idemploi, Date datedebutvalidite, Date datefinvalidite, Date dateajoutemploi, String emploi, int idprof) {
        this.idemploi = idemploi;
        this.datedebutvalidite = datedebutvalidite;
        this.datefinvalidite = datefinvalidite;
        this.dateajoutemploi = dateajoutemploi;
        this.emploi = emploi;
        this.idprof = idprof;
    }

    public int getIdemploi() {
        return idemploi;
    }

    public void setIdemploi(int idemploi) {
        this.idemploi = idemploi;
    }

    public Date getDatedebutvalidite() {
        return datedebutvalidite;
    }

    public void setDatedebutvalidite(Date datedebutvalidite) {
        this.datedebutvalidite = datedebutvalidite;
    }

    public Date getDatefinvalidite() {
        return datefinvalidite;
    }

    public void setDatefinvalidite(Date datefinvalidite) {
        this.datefinvalidite = datefinvalidite;
    }

    public Date getDateajoutemploi() {
        return dateajoutemploi;
    }

    public void setDateajoutemploi(Date dateajoutemploi) {
        this.dateajoutemploi = dateajoutemploi;
    }

    public String getEmploi() {
        return emploi;
    }

    public void setEmploi(String emploi) {
        this.emploi = emploi;
    }

    public int getIdprof() {
        return idprof;
    }

    public void setIdprof(int idprof) {
        this.idprof = idprof;
    }
}
