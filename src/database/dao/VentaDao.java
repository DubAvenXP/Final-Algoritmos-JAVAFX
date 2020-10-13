package database.dao;

import database.models.Cliente;
import database.models.Vendedor;

public class VentaDao {

    Vendedor vendedor = new Vendedor();
    Cliente cliente = new Cliente();
    private Integer idVenta;
    private Integer idSeller = vendedor.getIdVendedor();
    private Integer idClient = cliente.getIdCliente();
    private String numeroVenta;
    private Double monto;

    public VentaDao() {

    }

    public VentaDao(Integer idVenta, Integer idSeller, Integer idClient, String numeroVenta, Double monto) {
        this.idVenta = idVenta;
        this.idSeller = idSeller;
        this.idClient = idClient;
        this.numeroVenta = numeroVenta;
        this.monto = monto;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
