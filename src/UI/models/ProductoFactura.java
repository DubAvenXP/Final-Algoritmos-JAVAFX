package UI.models;

/**
 * Descripcion: clase POJO utilizada como modelo para crear objetos del tipo ProductoFactura
 * */

public class ProductoFactura {
    private Integer index;
    private Integer id;
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioTotal;

    public ProductoFactura(){

    }

    public ProductoFactura(Integer index, Integer id, String producto, Integer cantidad, Double precioUnitario, Double precioTotal) {
        this.index = index;
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
