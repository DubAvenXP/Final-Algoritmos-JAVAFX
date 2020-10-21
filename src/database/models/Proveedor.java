package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla proveedor
 */
public class Proveedor {

    private Integer idProveedor;
    private String nombre;
    private String descripcion;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor, String nombre, String descripcion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
