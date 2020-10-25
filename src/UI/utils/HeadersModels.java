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
            "idProvider",
            "bestSellerCount"
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
            "serieVenta",
            "fechaVenta",
            "monto",
            "metodoPago",
            "nombreCliente"
    };

    public static String[] ventaProductosHeaders = new String[]{
            "idVentaProducto",
            "serieVenta",
            "idProducto",
            "cantidad",
            "precioVenta"
    };
}
