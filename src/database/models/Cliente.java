package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla cliente
 */
public class Cliente {

    private Integer idCliente;
    private String nit;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nit, String nombre, String apellido, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nit = nit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
