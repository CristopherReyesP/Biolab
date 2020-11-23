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
public class DBPersona {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<Persona> obtenerRegistros() {
        List<Persona> modeloPersonas = new ArrayList<>();
        con = getConection();

        try {
            ps = con.prepareStatement("select * from personas where Estado=1");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloPersonas.add(new Persona(rs.getString("Dpi"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("FechaNacimiento"), rs.getString("Genero"), rs.getString("Telefono"), rs.getString("Correo"), rs.getString("Direccion"), rs.getString("RegistroPor"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado")));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloPersonas;
    }

    public static Persona obtenerRegistro(String Dpi) {
        Persona modeloPersona = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from personas where Dpi=? and Estado=1");
            ps.setString(1, Dpi);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloPersona = new Persona(rs.getString("Dpi"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("FechaNacimiento"), rs.getString("Genero"), rs.getString("Telefono"), rs.getString("Correo"), rs.getString("Direccion"), rs.getString("RegistroPor"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado"));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloPersona;
    }
    
    public static Persona obtenerRegistroLike(String Dpi) {
        Persona modeloPersona = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from personas where Dpi=? and Estado=1");
            ps.setString(1, Dpi);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloPersona = new Persona(rs.getString("Dpi"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("FechaNacimiento"), rs.getString("Genero"), rs.getString("Telefono"), rs.getString("Correo"), rs.getString("Direccion"), rs.getString("RegistroPor"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado"));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloPersona;
    }
    
    

    public static void guardarRegistro(Persona persona) {
        con = getConection();
        try {

            ps = con.prepareStatement("insert into personas(Dpi,Nombre,Apellido,FechaNacimiento,Genero,Telefono,Correo,Direccion,RegistroPor) values(?,?,?,?,?,?,?,?,?);");
            ps.setString(1, persona.getDpi());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setString(4, persona.getFechaNacimiento());
            ps.setString(5, persona.getGenero());
            ps.setString(6, persona.getTelefono());
            ps.setString(7, persona.getCorreo());
            ps.setString(8, persona.getDireccion());
            ps.setString(9, persona.getRegistroPor());            

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getErrorCode() + " " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR LA PERSONA", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static boolean actualizarRegistro(Persona persona) {
        con = getConection();
        try {

            ps = con.prepareStatement("update personas set Nombre=?, Apellido=?, FechaNacimiento=?, Genero=?, Telefono=?, Correo=?, Direccion=? where Dpi=? and Estado=1;");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getFechaNacimiento());
            ps.setString(4, persona.getGenero());
            ps.setString(5, persona.getTelefono());
            ps.setString(6, persona.getCorreo());
            ps.setString(7, persona.getDireccion());
            ps.setString(8, persona.getDpi());

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();
            return true;

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "EL CORREO O NUMERO DE TELEFONO YA ESTA REGISTRADO", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR LA PERSONA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public static void eliminarRegistro(String Dpi) {
        con = getConection();
        try {

            ps = con.prepareStatement("update personas set Estado=0 where Dpi=?;");
            ps.setString(1, Dpi);

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR LA PERSONA", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

}
