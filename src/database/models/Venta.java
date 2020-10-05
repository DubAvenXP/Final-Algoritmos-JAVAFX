package database.models;

import java.time.LocalDateTime;

public class Venta {

    private Integer idVenta;
    private Integer idVendedor;
    private Integer idCliente;
    private String numeroVenta;
    private LocalDateTime fecha;
    private Double monto;

    public Venta() {
    }

    public Venta(Integer idVenta, Integer idVendedor, Integer idCliente, String numeroVenta, LocalDateTime fecha, Double monto) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.numeroVenta = numeroVenta;
        this.fecha = fecha;
        this.monto = monto;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
