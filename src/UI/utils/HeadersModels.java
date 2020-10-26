package UI.utils;

public class HeadersModels {

    public static String[] clientHeaders = new String[]{
            "id",
            "nit",
            "nombre",
            "apellido",
            "direccion",
            "telefono"
    };

    public static String[] productoHeaders = new String[]{
            "idProducto",
            "nombre",
            "precio",
            "descripcion",
            "stock",
            "provider",
            "idProvider"
    };

    public static String[] proveedorHeaders = new String[]{
            "idProveedor",
            "nombre",
            "descripcion"
    };

    public static String[] ventaHeaders = new String[]{
            "idVenta",
            "nitCliente",
            "userVendedor",
            "nombreCliente",
            "serieVenta",
            "fechaVenta",
            "monto",
            "metodoPago"
    };

    public static String[] ventaProductosHeaders = new String[]{
            "serieVenta",
            "idProducto",
            "cantidad",
            "precioVenta",
            "nombreProducto",
            "descripcionProducto"
    };

    public static String[] saldoPendienteHeaders = new String[]{
            "idSaldoPendiente",
            "nitClient",
            "nombreCliente",
            "totalPagar",
            "deudaPendiente",
            "abono"
    };
}
