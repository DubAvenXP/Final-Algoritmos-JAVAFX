package database.service;

import database.dao.ProveedorDao;
import database.models.Proveedor;

import java.util.List;
import java.util.Scanner;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Proveedor con la intefaz de usuario
 */
public class ProveedorService {

    /**
     * Metodo que comunica con la capa Dao
     * @param proveedor objeto de tipo Proveedor que envia los como parametro los datos para crear un nuevo proveedor
     */
    public static void createProvider(Proveedor proveedor) {
        ProveedorDao.createProviderDB(proveedor);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retorna un List con todos los proveedores existentes en la base de datos
     */
    public static List<Proveedor> listProvider(){
        return ProveedorDao.viewProviderDB();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param id pertenciente al proveedor que se quiera ver
     * @return retonna un objeto de tipo Proveedor
     */
    public static Proveedor listProviderByID(int id){
        return ProveedorDao.viewProviderByID(id);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param id perteneciente al proveedor que se quiera eliminar
     */
    public static void deleteProvider(int id){
        ProveedorDao.deleteProvider(id);
    }

    /**
     *Metodo que comunica con la capa Dao
     * @param proveedor objeto de tipo Proveedor que envia como parametro los nuevos datos del proveedor
     */
    public static void updateProvider(Proveedor proveedor){
        ProveedorDao.updateProviderDB(proveedor);
    }

}
