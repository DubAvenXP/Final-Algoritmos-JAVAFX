package database.service;

import database.dao.VentaProductoDao;
import database.models.VentaProducto;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de ventaProducto con la intefaz de usuario
 */
public class VentaProductoService {

    /**
     * Metodo que comunica con la capa Dao y el modelo de VentaProducto con la interfaz de usuario
     * @param ventaProducto objeto de tipo VentaProducto que envia como parametro para guardar los datos de la
     *                      facturacion
     */
    public static void saveBill(VentaProducto ventaProducto){
        VentaProductoDao.saveBillDB(ventaProducto);
    }

}
