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

/**
 *
 * @author Jose
 */
public class DBArea {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<Area> obtenerRegistros() {
        List<Area> modeloAreas = new ArrayList<>();
        con = getConection();

        try {
            ps = con.prepareStatement("select * from areas where Estado=1");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloAreas.add(new Area(rs.getString("Id"), rs.getString("Nombre"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado")));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloAreas;
    }

    public static Area obtenerRegistro(String Id) {
        Area modeloArea = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from areas where Id=? and Estado=1");
            ps.setString(1, Id);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloArea = new Area(rs.getString("Id"), rs.getString("Nombre"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado"));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }

        return modeloArea;
    }
}
