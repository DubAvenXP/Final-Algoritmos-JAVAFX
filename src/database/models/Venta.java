package database.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {

    Vendedor vendedor = new Vendedor();
    Cliente cliente = new Cliente();
    private Integer idVenta;
    private Integer idSeller = vendedor.getIdVendedor();
    private Integer idClient = cliente.getIdCliente();
    private String numeroVenta;
    private Date fecha;
    private Double monto;

    public Venta() {
    }

    public Venta(Integer idVenta, Integer idSeller, Integer idClient, String numeroVenta, Date fecha, Double monto) {
        this.idVenta = idVenta;
        this.idSeller = idSeller;
        this.idClient = idClient;
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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
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
