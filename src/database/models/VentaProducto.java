package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla ventaProducto
 */
public class VentaProducto {

    private Integer idVentaProducto;
    private String serieVenta;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioVenta;

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
}
