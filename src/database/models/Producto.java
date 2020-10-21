package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla producto
 */
public class Producto{

    Proveedor proveedor = new Proveedor();
    private Integer idProducto;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;
    private String provider;
    private Integer idProvider = proveedor.getIdProveedor();

    public Producto() {

    }

    public Producto(Integer idProducto, String nombre, Double precio, String descripcion, Integer stock, String provider) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.provider = provider;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }
}

