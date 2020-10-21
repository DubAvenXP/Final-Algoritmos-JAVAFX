package database.service;

import database.dao.VentaDao;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Venta con la intefaz de usuario
 */
public class VentaService {

    /**
     * Metodo que comunica con viewPriceProduct de VentaDao, tiene la funcion de retornar un Double con el precio
     * que recibe de la base de datos
     * @param id parametro que recibe para hacer el query a la base de datos
     * @return devuelve un Double con el precio del producto
     */
    public static Double viewPrice(Integer id) {
        return VentaDao.viewPriceProduct(id);
    }

    /**
     * Metodo que comunica con availableProductDB de VentaDao, retorna un Integer con el stock que recibe de la
     * base de datos
     * @param id parametro que recibe para hacer el query a la base de datos
     * @return devielve un Integer con la cantidad de productos disponibles
     */
    public static Integer availableProduct(Integer id) {
        return VentaDao.availableProductDB(id);
    }

    /**
     * Metodo que comunica con updateStockDB de VentaDao, envia el nuevo valor de stock al producto perteneciente
     * al id
     * @param stock parametro con el nuevo valor de stock para actualizar en la base de datos
     * @param id parametro que recibe para hacer el query a la base de datos
     */
    public static void updateStock(Integer stock, Integer id){
        VentaDao.updateStockDB(stock, id);
    }

}
