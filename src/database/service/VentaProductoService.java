package database.service;

import database.dao.VentaProductoDao;
import database.models.VentaProducto;

import java.util.List;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de ventaProducto con la intefaz de usuario
 */
public class VentaProductoService {

    /**
     * Metodo que comunica con la capa Dao y el modelo de VentaProducto con la interfaz de usuario
     * @param productoList recibe un List de tipo VentaProducto donde traer los parametros del producto para ser insertados
     *                     en la base de datos
     */
    public static void saveBill(List<VentaProducto> productoList){
        VentaProductoDao.saveBillDB(productoList);
    }

}
