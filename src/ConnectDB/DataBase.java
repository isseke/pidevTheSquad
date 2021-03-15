/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gicke
 */
public class DataBase {
    private static String url ="jdbc:mysql://localhost:3306/pidev";
    private static String user ="root";
    private static String pwd ="";
    
    private static Connection conn;
 
    static DataBase instance;
    
    private DataBase() {
        
        try {
            conn = DriverManager.getConnection(url, user, pwd);
                        System.out.println(" connecté !!!!");

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataBase getInstance(){
        if(instance == null)
            instance = new DataBase();
        
        return instance;
    }

    public static Connection getConn() {
        return conn;
    }
    
}
