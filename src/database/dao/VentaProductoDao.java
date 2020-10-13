package database.dao;

import database.models.Producto;
import database.models.Venta;

public class VentaProductoDao {

    Venta venta = new Venta();
    Producto producto = new Producto();
    private Integer idVentaProducto;
    private Integer idSale = venta.getIdVenta();
    private Integer idProduct = producto.getIdProducto();
    private Integer cantidad;
    private Double precioVenta;

    public VentaProductoDao() {
    }

    public VentaProductoDao(Integer idVentaProducto, Integer idSale, Integer idProduct, Integer cantidad, Double precioVenta) {
        this.idVentaProducto = idVentaProducto;
        this.idSale = idSale;
        this.idProduct = idProduct;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
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
