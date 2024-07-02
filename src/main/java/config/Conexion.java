package config;

import java.sql.Connection;
//com.mysql.cj.jdbc.JdbcConnection;
//com.mysql.jdbc.Driver
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://hotelproaws.che6640gmga5.us-east-2.rds.amazonaws.com:3306/bd_rest?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
    static String user="root";
    static String pass="ASdf1234";
    protected Connection conn=null;
    Connection con;

    public Connection Conexion(){
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+e);
        }
        return con;
    }

    public Connection Connected(){
        return conn;
    }
    public Connection Discconet(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error de desconexión.. "+ex.getMessage());
        }
        return null;
    }


}
