package database.service;

import database.dao.ProveedorDao;
import database.models.Proveedor;

import java.util.Scanner;

public class ProveedorService {

    public static void createProvider(Proveedor proveedor) {
        ProveedorDao.createProviderDB(proveedor);
    }

    public static void listProvider(){
        ProveedorDao.viewProviderDB();
    }

    public static Proveedor listProviderByID(int id){
        return ProveedorDao.viewProviderByID(id);
    }

    public static void deleteProvider(int id){
        ProveedorDao.deleteProvider(id);
    }

    public static void updateProvider(Proveedor proveedor){
        ProveedorDao.updateProviderDB(proveedor);
    }

}
