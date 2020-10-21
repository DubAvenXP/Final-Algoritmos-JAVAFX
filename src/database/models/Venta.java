package database.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author glasd
 * Esta clase es el mapeado de la tabla venta
 */
public class Venta {

    Vendedor vendedor = new Vendedor();
    Cliente cliente = new Cliente();
    private Integer idVenta;
    private Integer idSeller = vendedor.getIdVendedor();
    private String nitClient = cliente.getNit();
    private String numeroVenta;
    private Date fecha;
    private Double monto;

    public Venta() {
    }

    public Venta(Integer idVenta, Integer idSeller, String nitClient, String numeroVenta, Date fecha, Double monto) {
        this.idVenta = idVenta;
        this.idSeller = idSeller;
        this.nitClient = nitClient;
        this.numeroVenta = numeroVenta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Integer getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Integer idSeller) {
        this.idSeller = idSeller;
    }

    public String getNitClient() {
        return nitClient;
    }

    public void setNitClient(String nitClient) {
        this.nitClient = nitClient;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public Object getFecha() {
        Date date = new Date();
        String dateFormat = "hh:mm a dd-MMM-aaaa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
