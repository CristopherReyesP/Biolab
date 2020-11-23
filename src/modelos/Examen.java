/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Jose
 */
public class Examen {

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the IdArea
     */
    public String getIdArea() {
        return IdArea;
    }

    /**
     * @param IdArea the IdArea to set
     */
    public void setIdArea(String IdArea) {
        this.IdArea = IdArea;
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
     * @return the Precio
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(double Precio) {
        this.Precio = Precio;
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

    private String Id;
    private String IdArea;
    private String Nombre;
    private double Precio;
    private String Hora;
    private String Fecha;
    private int Estado;

    public Examen(String Id, String IdArea, String Nombre, double Precio, String Hora, String Fecha, int Estado) {
        this.Id = Id;
        this.IdArea = IdArea;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Estado = Estado;
    }

    public Examen(String Id, String IdArea, String Nombre, double Precio) {
        this.Id = Id;
        this.IdArea = IdArea;
        this.Nombre = Nombre;
        this.Precio = Precio;
    }
}
