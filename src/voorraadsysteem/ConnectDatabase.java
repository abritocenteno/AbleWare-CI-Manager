/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

    
/**
 *
 * @author abel
 */
public class ConnectDatabase {
   
           Connection con = null;
           public static Connection conDB(){
               try {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdbcaribinter?useTimezone=true&serverTimezone=UTC", "root", "");
                   return con;
               } catch (Exception ex) {
                   return null;
               }
           }
}
        

            
    




