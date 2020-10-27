package UI.utils;

public class HeadersModels {

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para clientes
     * */
    public static String[] clientHeaders = new String[]{
            "id",
            "nit",
            "nombre",
            "apellido",
            "direccion",
            "telefono"
    };

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para productos
     * */
    public static String[] productoHeaders = new String[]{
            "idProducto",
            "nombre",
            "precio",
            "descripcion",
            "stock",
            "provider",
            "idProvider"
    };

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para proveedores
     * */
    public static String[] proveedorHeaders = new String[]{
            "idProveedor",
            "nombre",
            "descripcion"
    };

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para ventas
     * */
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

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para los productos
     * vendidos
     * */
    public static String[] ventaProductosHeaders = new String[]{
            "serieVenta",
            "idProducto",
            "cantidad",
            "precioVenta",
            "nombreProducto",
            "descripcionProducto"
    };

    /**
     * Descripcion: Array que contiene los encabezados que iran como titulo en el excel a crearse para clientes morosos
     * */
    public static String[] saldoPendienteHeaders = new String[]{
            "idSaldoPendiente",
            "nitClient",
            "nombreCliente",
            "totalPagar",
            "deudaPendiente",
            "abono"
    };
}
