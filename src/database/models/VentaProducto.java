package database.models;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla ventaProducto
 */
public class VentaProducto {

    private Integer idVenta;
    private Integer idVendedor;
    private Integer idVentaProducto;
    private String cantidad;
    private String precioVenta;

    public VentaProducto() {
    }

    public VentaProducto(Integer idVenta, Integer idVendedor, Integer idVentaProducto, String cantidad, String precioVenta) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.idVentaProducto = idVentaProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }
}
