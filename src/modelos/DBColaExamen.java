
package modelos;

import static conexion.conexion.getConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Jose
 */
public class DBColaExamen {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<ColaExamen> obtenerRegistros() {
        List<ColaExamen> modeloColaExamenes = new ArrayList<>();
        con = getConection();

        try {
            ps = con.prepareStatement("select * from colaexamenes where Estado=1");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloColaExamenes.add(new ColaExamen(rs.getInt("Id"), rs.getString("DpiPaciente"),rs.getString("NombreDoctor"), rs.getString("TelefonoDoctor"), rs.getString("CorreoDoctor"), rs.getString("IdExamen"), rs.getString("DescripcionExamen")));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloColaExamenes;
    }

    public static ColaExamen obtenerRegistro(int Id) {
        ColaExamen modeloColaExamen = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from colaexamenes where Id=? and Estado=1");
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloColaExamen = new ColaExamen(rs.getInt("Id"), rs.getString("DpiPaciente"),rs.getString("NombreDoctor"), rs.getString("TelefonoDoctor"), rs.getString("CorreoDoctor"), rs.getString("IdExamen"), rs.getString("DescripcionExamen"));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }

        return modeloColaExamen;
    }
    
    
    public static void guardarRegistro(ColaExamen colaExamen) {
        con = getConection();
        try {

            ps = con.prepareStatement("insert into colaexamenes(DpiPaciente,NombreDoctor,TelefonoDoctor,CorreoDoctor,IdExamen,DescripcionExamen) values(?,?,?,?,?,?);");
            ps.setString(1, colaExamen.getDpiPaciente());        
            ps.setString(2, colaExamen.getNombreDoctor());            
            ps.setString(3, colaExamen.getTelefonoDoctor());
            ps.setString(4, colaExamen.getCorreoDoctor());
            ps.setString(5, colaExamen.getIdExamen());
            ps.setString(6, colaExamen.getDescripcionExamen());

            ps.execute();

            ps.clearParameters();           
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode() + " " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR LA INFORMACION", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }
}
