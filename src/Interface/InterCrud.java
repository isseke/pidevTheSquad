/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author gicke
 * @param <T>
 */
public interface InterCrud <T>{
    public void ajouter(T e);
    public void supprimer(T e);
    public void modifier(T e);
    public List<T> Consulter();
    
}
