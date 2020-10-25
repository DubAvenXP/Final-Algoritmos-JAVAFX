package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla ventaProducto
 */
public class VentaProducto {

    Venta vent = new Venta();
    private Integer idVentaProducto;
    private String serieVenta = vent.getSerieVenta();
    private Integer idProducto;
    private Integer cantidad;
    private Double precioVenta;
    private String nombreProducto;
    private String descripcionProducto;

    public VentaProducto() {
    }

    public VentaProducto(Integer idVentaProducto, String serieVenta, Integer idProducto, Integer cantidad, Double precioVenta) {
        this.idVentaProducto = idVentaProducto;
        this.serieVenta = serieVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public String getSerieVenta() {
        return serieVenta;
    }

    public void setSerieVenta(String serieVenta) {
        this.serieVenta = serieVenta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
}
