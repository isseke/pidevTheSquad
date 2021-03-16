/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class ReclamationProf {

    int id,id_professeur;
    String title,description,answer;

    public ReclamationProf(int id, String title, String description, String answer, int id_professeur) {
        this.id = id;
        this.id_professeur = id_professeur;
        this.title = title;
        this.description = description;
        this.answer = answer;
    }

    public ReclamationProf( String title, String description , int id_professeur) {
        this.id_professeur = id_professeur;
        this.title = title;
        this.description = description;
    }

    public ReclamationProf() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_professeur() {
        return id_professeur;
    }

    public void setId_professeur(int id_professeur) {
        this.id_professeur = id_professeur;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
