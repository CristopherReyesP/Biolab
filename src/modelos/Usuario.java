/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author francisco
 */
public class Usuario {

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Clave
     */
    public String getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the Hora
     */
    public String getHora() {
        return Hora;
    }

    /**
     * @param Hora the Hora to set
     */
    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the Estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    private int Id;
    private String Nombre;
    private String Clave;
    private String Tipo;
    private String Hora;
    private String Fecha;
    private int Estado;

    public Usuario(int Id, String Nombre, String Clave, String Tipo, String Hora, String Fecha, int Estado) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Clave = Clave;
        this.Tipo = Tipo;
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Estado = Estado;
    }

    public Usuario(String Nombre, String Clave) {
        this.Nombre = Nombre;
        this.Clave = Clave;
    }

    public Usuario(String Nombre, String Clave, String Tipo) {
        this.Nombre = Nombre;
        this.Clave = Clave;
        this.Tipo = Tipo;
    }

    public Usuario(int Id, String Nombre, String Clave) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Clave = Clave;
    }

    public Usuario(int Id, String Nombre, String Clave, String Tipo) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Clave = Clave;
        this.Tipo = Tipo;
    }

}
