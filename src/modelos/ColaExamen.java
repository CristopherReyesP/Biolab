
package modelos;

/**
 * @author Jose
 */
public class ColaExamen {

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
     * @return the DpiPaciente
     */
    public String getDpiPaciente() {
        return DpiPaciente;
    }

    /**
     * @param DpiPaciente the DpiPaciente to set
     */
    public void setDpiPaciente(String DpiPaciente) {
        this.DpiPaciente = DpiPaciente;
    }

    /**
     * @return the NombreDoctor
     */
    public String getNombreDoctor() {
        return NombreDoctor;
    }

    /**
     * @param NombreDoctor the NombreDoctor to set
     */
    public void setNombreDoctor(String NombreDoctor) {
        this.NombreDoctor = NombreDoctor;
    }

    /**
     * @return the TelefonoDoctor
     */
    public String getTelefonoDoctor() {
        return TelefonoDoctor;
    }

    /**
     * @param TelefonoDoctor the TelefonoDoctor to set
     */
    public void setTelefonoDoctor(String TelefonoDoctor) {
        this.TelefonoDoctor = TelefonoDoctor;
    }

    /**
     * @return the CorreoDoctor
     */
    public String getCorreoDoctor() {
        return CorreoDoctor;
    }

    /**
     * @param CorreoDoctor the CorreoDoctor to set
     */
    public void setCorreoDoctor(String CorreoDoctor) {
        this.CorreoDoctor = CorreoDoctor;
    }

    /**
     * @return the IdExamen
     */
    public String getIdExamen() {
        return IdExamen;
    }

    /**
     * @param IdExamen the IdExamen to set
     */
    public void setIdExamen(String IdExamen) {
        this.IdExamen = IdExamen;
    }

    /**
     * @return the DescripcionExamen
     */
    public String getDescripcionExamen() {
        return DescripcionExamen;
    }

    /**
     * @param DescripcionExamen the DescripcionExamen to set
     */
    public void setDescripcionExamen(String DescripcionExamen) {
        this.DescripcionExamen = DescripcionExamen;
    }

    private int Id;
    private String DpiPaciente; 
    private String NombreDoctor;
    private String TelefonoDoctor;
    private String CorreoDoctor;
    private String IdExamen;
    private String DescripcionExamen;

    public ColaExamen(int Id,String DpiPaciente, String NombreDoctor, String TelefonoDoctor, String CorreoDoctor, String IdExamen, String DescripcionExamen) {
        this.Id = Id;
        this.DpiPaciente = DpiPaciente;      
        this.NombreDoctor = NombreDoctor;
        this.TelefonoDoctor = TelefonoDoctor;
        this.CorreoDoctor = CorreoDoctor;
        this.IdExamen = IdExamen;
        this.DescripcionExamen = DescripcionExamen;
    }
    
    
    public ColaExamen(String DpiPaciente, String NombreDoctor, String TelefonoDoctor, String CorreoDoctor, String IdExamen, String DescripcionExamen) {        
        this.DpiPaciente = DpiPaciente;     ;
        this.NombreDoctor = NombreDoctor;
        this.TelefonoDoctor = TelefonoDoctor;
        this.CorreoDoctor = CorreoDoctor;
        this.IdExamen = IdExamen;
        this.DescripcionExamen = DescripcionExamen;
    }
}
