/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author quang
 */
public class ConnectDatabase {
      public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;database=DOACSDL;";
    public static final String username = "QuangHao";
     public static final String password = "123";
    public static Connection getDBConnect()  {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("Chua co Driver!" + e.toString());
        }
        try {
            conn = DriverManager.getConnection(connectionUrl,username, password);
        } catch (Exception e) {
            //Loi sai ten db
            // Loi ten dang nhap va pass
            System.out.println("Loi connect" + e.toString());
        }
        return conn;
    }
}
