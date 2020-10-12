package database.service;

import database.dao.ClienteDao;
import database.dao.ProductoDao;
import database.models.Producto;

import java.util.Scanner;

public class ProductoService {

    public static void createProduct(Producto producto){
        ProductoDao.createProductDB(producto);
    }

    public static void listProduct(){
        ProductoDao.listProductDB();
    }

    public static Producto listProductByID(int id){
        return ProductoDao.listProductDBByID(id);
    }

    public static void deleteProduct(int id){
        ProductoDao.deleteProductDB(id);
    }

    public static void updateProduct(Producto producto) {
        ProductoDao.updateProductDB(producto);
    }

}
