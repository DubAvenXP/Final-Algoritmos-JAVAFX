package database.models;

public class SaldoPendiente {

    Venta venta = new Venta();
    private Integer idSaldoPendiente;
    private String nitClient;
    private String nombreCliente;
    private Double totalPagar;
    private String serieVenta = venta.getSerieVenta();
    private Double deudaPendiente;
    private Double abono;
    private String tipoPago = venta.getMetodoPago();

    public SaldoPendiente(Integer idSaldoPendiente, String nitClient, String nombreCliente, Double totalPagar,
                          String serieVenta, Double deudaPendiente, Double abono) {
        this.idSaldoPendiente = idSaldoPendiente;
        this.nitClient = nitClient;
        this.nombreCliente = nombreCliente;
        this.totalPagar = totalPagar;
        this.serieVenta = serieVenta;
        this.deudaPendiente = deudaPendiente;
        this.abono = abono;
    }

    public SaldoPendiente() {

    }

    public Integer getIsSaldoPendiente() {
        return idSaldoPendiente;
    }

    public void setIsSaldoPendiente(Integer idSaldoPendiente) {
        this.idSaldoPendiente = idSaldoPendiente;
    }

    public String getNitClient() {
        return nitClient;
    }

    public void setNitClient(String nitClient) {
        this.nitClient = nitClient;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getSerieVenta() {
        return serieVenta;
    }

    public void setSerieVenta(String serieVenta) {
        this.serieVenta = serieVenta;
    }

    public Double getDeudaPendiente() {
        return deudaPendiente;
    }

    public void setDeudaPendiente(Double deudaPendiente) {
        this.deudaPendiente = deudaPendiente;
    }

    public Double getAbono() {
        return abono;
    }

    public void setAbono(Double abono) {
        this.abono = abono;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
