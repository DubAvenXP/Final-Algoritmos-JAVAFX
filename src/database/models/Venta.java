package database.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla venta
 */
public class Venta {

    private Integer idVenta;
    private String nitCliente;
    private String userVendedor;
    private String serieVenta;
    private String fechaVenta;
    private Double monto;
    private String metodoPago;
    private String nombreCliente;

    public Venta() {
    }

    public Venta(Integer idVenta, String nitCliente, String userVendedor, String serieVenta, String fechaVenta,
                 Double monto, String metodoPago) {
        this.idVenta = idVenta;
        this.nitCliente = nitCliente;
        this.userVendedor = userVendedor;
        this.serieVenta = serieVenta;
        this.fechaVenta = fechaVenta;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getUserVendedor() {
        return userVendedor;
    }

    public void setUserVendedor(String userVendedor) {
        this.userVendedor = userVendedor;
    }

    public String getSerieVenta() {
        return serieVenta;
    }

    public void setSerieVenta(String serieVenta) {
        this.serieVenta = serieVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Getter propio del modelo Venta
     * @param fechaVenta parametro que recibe para ser seteado en la variable local
     */
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * Metodo que genera automaticamente la fecha usando la que tiene el sistema operativo sobre el que se trabaja
     * @return retorna un String para que sea almacenado en la base de datos
     */
    public String generateDate(){
        Date date = new Date();
        String dateFormat = "hh:mm a dd/MMM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
