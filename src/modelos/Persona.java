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
public class Persona {

    /**
     * @return the Dpi
     */
    public String getDpi() {
        return Dpi;
    }

    /**
     * @param Dpi the Dpi to set
     */
    public void setDpi(String Dpi) {
        this.Dpi = Dpi;
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
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the FechaNacimiento
     */
    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    /**
     * @param FechaNacimiento the FechaNacimiento to set
     */
    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    /**
     * @return the Genero
     */
    public String getGenero() {
        return Genero;
    }

    /**
     * @param Genero the Genero to set
     */
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the RegistroPor
     */
    public String getRegistroPor() {
        return RegistroPor;
    }

    /**
     * @param RegistroPor the RegistroPor to set
     */
    public void setRegistroPor(String RegistroPor) {
        this.RegistroPor = RegistroPor;
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

    private String Dpi;
    private String Nombre;
    private String Apellido;
    private String FechaNacimiento;
    private String Genero;
    private String Telefono;
    private String Correo;
    private String Direccion;
    private String RegistroPor;    
    private String Hora;
    private String Fecha;
    private int Estado;

    public Persona(String Dpi, String Nombre, String Apellido, String FechaNacimiento, String Genero, String Telefono, String Correo, String Direccion, String RegistroPor,String Hora, String Fecha, int Estado) {
        this.Dpi = Dpi;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.Genero = Genero;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Direccion = Direccion;
        this.RegistroPor = RegistroPor;        
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Estado = Estado;
    }

    public Persona(String Dpi, String Nombre, String Apellido, String FechaNacimiento, String Genero, String Telefono, String Correo, String Direccion) {
        this.Dpi = Dpi;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.Genero = Genero;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Direccion = Direccion;
    }
    
    public Persona(String Dpi, String Nombre, String Apellido, String FechaNacimiento, String Genero, String Telefono, String Correo, String Direccion, String RegistroPor) {
        this.Dpi = Dpi;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.Genero = Genero;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Direccion = Direccion;
        this.RegistroPor =RegistroPor;
    }

}
