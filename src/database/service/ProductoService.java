package database.service;

import database.dao.ProductoDao;
import database.models.Producto;

import java.util.List;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Producto con la intefaz de usuario
 */
public class ProductoService {

    /**
     * Metodo que comunica con la capa Dao
     *
     * @param producto objeto de tipo Producto que envia como parametro los datos para crear un nuevo producto
     */
    public static void createProduct(Producto producto) {
        ProductoDao.createProductDB(producto);
    }

    /**
     * Metodo que comunica con la capa Dao
     *
     * @return retorna un List de todos los productos que existen en la base de datos
     */
    public static List<Producto> listProduct() {
        return ProductoDao.listProductDB();
    }

    /**
     * Metodo que comunica con la capa Dao
     *
     * @param id perteneciente al producto que se quiera ver
     * @return retorna un objeto de tipo Producto con los datos extraidos de la tabla producto
     */
    public static Producto listProductByID(int id) {
        return ProductoDao.listProductDBByID(id);
    }

    /**
     * Metodo que comunica con la capa Doa
     *
     * @param id perteneciente al producto que se quiera eliminar
     */
    public static void deleteProduct(int id) {
        ProductoDao.deleteProductDB(id);
    }

    /**
     * Metodo que comunica con la capa Dao
     *
     * @param producto objeto de tipo Producto que envia como parametro los nuevos datos del producto
     */
    public static void updateProduct(Producto producto) {
        ProductoDao.updateProductDB(producto);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retorna un List con los productos sin stock
     */
    public static List<Producto> stockZero() {
        return ProductoDao.stockZeroDB();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retorna un List con los productos mejor vendidos
     */
    public static List<Producto> bestSellers() {
        return ProductoDao.bestSellers();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retorna un List con los productos ordenados de mayor a menor
     */
    public static List<Producto> highestPrice() {
        return ProductoDao.highestPrice();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retona un List con los productos ordenados de menor a mayor precio
     */
    public static List<Producto> lowestPrice() {
        return ProductoDao.lowestPrice();
    }
}
