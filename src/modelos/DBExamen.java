/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jose
 */
public class DBExamen {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<Examen> obtenerRegistros(String IdArea) {
        List<Examen> modeloExamenes = new ArrayList<>();
        con = getConection();

        try {
            ps = con.prepareStatement("select * from examenes where IdArea=? and Estado=1");
            ps.setString(1, IdArea);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloExamenes.add(new Examen(rs.getString("Id"), rs.getString("IdArea"), rs.getString("Nombre"), rs.getDouble("Precio"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado")));
                }
            }

            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloExamenes;
    }

    public static Examen obtenerRegistro(String IdArea) {
        Examen modeloExamen = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from examenes where IdArea=? and Estado=1");
            ps.setString(1, IdArea);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloExamen = new Examen(rs.getString("Id"), rs.getString("IdArea"), rs.getString("Nombre"), rs.getDouble("Precio"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado"));
                }
            }

            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }

        return modeloExamen;
    }

    public static void actualizarRegistro(String Id, String IdArea, String Precio) {
        con = getConection();
        try {

            ps = con.prepareStatement("update examenes set Precio=? where Id=? and IdArea=? and Estado=1;");
            ps.setDouble(1, Double.valueOf(Precio));
            ps.setString(2, Id);
            ps.setString(3, IdArea);
            
            ps.executeUpdate();
            
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR EL PRECIO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
