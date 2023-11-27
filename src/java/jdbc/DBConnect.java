package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    public Connection conn = null;

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=EljShop", "sa", "123456");
    }
    
    public DBConnect(String url, String username, String password) {
        try {
            //call driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conect
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ResultSet getData(String sql) {
        ResultSet res = null;
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            res = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return res;
    }
}
