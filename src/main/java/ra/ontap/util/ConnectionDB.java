
package ra.ontap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "hung18061999";
    // mở kết nối
    public static Connection getConnection(){
        try{
            // khai báo driver
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
    // dong ket nói
    public  static  void closeConnection(Connection con){
        try {
            if (!con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
