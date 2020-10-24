package database.service;

import database.dao.VentaDao;
import database.models.Venta;

import java.util.List;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Venta con la intefaz de usuario
 */
public class VentaService {

    public static void createSale(Venta venta){
        VentaDao.createSaleDB(venta);
    }

    /**
     * Metodo que comunica con la capa Dao y el modelo de la tabla Venta
     * @param id parametro que recibe para hacer el query a la base de datos
     * @return devuelve un Double con el precio del producto
     */
    public static Double viewPrice(Integer id) {
        return VentaDao.viewPriceProduct(id);
    }

    /**
     * Metodo que comunica con la capa Dao y el modelo de la tabla Venta
     * @param id parametro que recibe para hacer el query a la base de datos
     * @return devuelve un Integer con la cantidad de productos disponibles
     */
    public static Integer availableProduct(Integer id) {
        return VentaDao.availableProductDB(id);
    }

    /**
     * Metodo que comunica con la capa Dao y el modelo de la tabla Venta
     * @param stock parametro con el nuevo valor de stock para actualizar en la base de datos
     * @param id parametro que recibe para hacer el query a la base de datos
     */
    public static void updateStock(Integer stock, Integer id){
        VentaDao.updateStockDB(stock, id);
    }

    /**
     * Metodo que comunica con la capa Dao y el modelo de la tabla Venta
     * @return retorna un List con los datos que pertenecen al balance de saldos del cliente
     */
    public static List<Venta> balance(){
        return VentaDao.balanceDB();
    }

    public static List<Venta> viewBillClient(String nit){
        return  VentaDao.viewBillClient(nit);
    }

}
