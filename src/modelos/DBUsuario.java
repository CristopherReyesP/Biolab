/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import app.Principal;
import static conexion.conexion.getConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class DBUsuario {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void validarLogin(JFrame LOGIN, Usuario usuario) {
        con = getConection();

        try {
            String sql = "SELECT 1, Id, Nombre, Clave, Tipo FROM usuarios where Nombre=? and Clave=? and Estado=1";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getClave());

            rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setClave(rs.getString("Clave"));
                usuario.setTipo(rs.getString("Tipo"));

                Principal p = new Principal(usuario);
                p.setVisible(true);
                LOGIN.dispose();
            } else {
                JOptionPane.showMessageDialog(LOGIN, "USUARIO O CLAVE INVALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            ps.clearParameters();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DBUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Usuario> obtenerRegistros() {
        List<Usuario> modeloUsuarios = new ArrayList<>();
        con = getConection();

        try {
            ps = con.prepareStatement("select * from usuarios where Estado=1");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloUsuarios.add(new Usuario(rs.getInt("Id"), rs.getString("Nombre"), rs.getString("Clave"), rs.getString("Tipo"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado")));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloUsuarios;
    }

    public static Usuario obtenerRegistro(String Id) {
        Usuario modeloUsuario = null;
        con = getConection();

        try {
            ps = con.prepareStatement("select * from usuarios where Id=? and Estado=1");
            ps.setInt(1, Integer.parseInt(Id));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    modeloUsuario = new Usuario(rs.getInt("Id"), rs.getString("Nombre"), rs.getString("Clave"), rs.getString("Tipo"), rs.getString("HoraCommit"), rs.getString("FechaCommit"), rs.getInt("Estado"));
                }
            }
            ps.clearParameters();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        return modeloUsuario;
    }

    public static void guardarRegistro(Usuario usuario) {
        con = getConection();
        try {

            ps = con.prepareStatement("insert into usuarios(Nombre,Clave,Tipo) values(?,?,?);");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getTipo());

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR EL USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static boolean actualizarRegistro(String Id, String Nombre, String Clave) {
        con = getConection();
        try {

            ps = con.prepareStatement("update usuarios set Nombre=?, Clave=? where Id=? and Estado=1;");
            ps.setString(1, Nombre);
            ps.setString(2, Clave);
            ps.setInt(3, Integer.parseInt(Id));

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR EL USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void eliminarRegistro(String Id, String Nombre) {
        con = getConection();
        try {

            ps = con.prepareStatement("update usuarios set Estado=0 where Id=? and Nombre=?;");
            ps.setInt(1, Integer.parseInt(Id));
            ps.setString(2, Nombre);

            ps.execute();

            ps.clearParameters();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

}
