package conexion;
import java.sql.Connection;
//com.mysql.cj.jdbc.JdbcConnection;
//com.mysql.jdbc.Driver
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://hotelproaws.che6640gmga5.us-east-2.rds.amazonaws.com:3306/bd_rest?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
    static String user="root";
    static String pass="ASdf1234";
    protected Connection conn=null;
    public conexionBD() {
        try{
            Class.forName(driver);
            conn= (Connection) DriverManager.getConnection(url,user,pass);
            if(conn!=null){
                System.out.println("Conexión realizada..."+conn);
                //JOptionPane.showMessageDialog(null,"Conectado");
            }
        }catch(SQLException ex){
            System.out.println("Conexión fallida..."+ex.getMessage());
        }catch (ClassNotFoundException ex) {
            System.out.println("Falta Driver "+ex.getMessage());
        }
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
