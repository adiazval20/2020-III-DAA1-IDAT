package pe.edu.idat.semana4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static Connection cnx;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "app_avanzadas";
    private static String timezone = "serverTimezone=UTC";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    
    public static Connection getCnx() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(cnx == null) {
            try {
                // jdbc:mysql://localhost:3306/app_avanzadas?serverTimezone=UTC
                cnx = DriverManager.getConnection(url + dbName + "?" + timezone,username,password);
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return cnx;
    }
    
}
