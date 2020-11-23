package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Marlon Caal
 */
public class conexion {

    //public static final String URL="jdbc:mysql://localhost:3306/bd_biolab?autoReconnect=true&useSSL=false";
    //public static final String URL="jdbc:mysql://localhost:3306/bd_biolab?useTimezone=true&serverTimezone=UTC";
    public static final String URL = "jdbc:mysql://localhost:3307/biolabs?useTimezone=true&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWOR = "1996";

    public static Connection getConection() {
        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWOR);
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

}
