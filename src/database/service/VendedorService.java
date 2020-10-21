package database.service;

import database.dao.VendedorDao;
import database.models.Vendedor;

import java.util.Scanner;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Vendedor con la intefaz de usuario
 */
public class VendedorService {

    /**
     *Metodo que comunica con la capa Dao
     * @param vendedor objeto de tipo Vendedor que envia como parametro para crear un nuevo vendedor
     */
    public static void createSeller(Vendedor vendedor){
        VendedorDao.createSellerDB(vendedor);
    }

    /**
     * Metodo que comunica con la capa Dao para ver todos los proveedores existentes en la base de datos
     */
    public static void listSeller(){
        VendedorDao.viewSellerDB();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param id perteneciente al vendedor que se quiere ver en la base de datos
     */
    public static void listSellerByID(Integer id){
        VendedorDao.viewSellerByID(id);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param id perteneciente al vendedor que se quiera eliminar
     */
    public static void deleteSeller(Integer id){
        VendedorDao.deleteSellerDB(id);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param vendedor objeto de tipo Vendedor que envia como parametro para insertar los nuevos datos del vendedor
     */
    public static void updateSeller(Vendedor vendedor){
        VendedorDao.updateSeller(vendedor);
    }

}
