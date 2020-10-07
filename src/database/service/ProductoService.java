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

    public static void listProductByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id");
        int idProducto = sc.nextInt();
        ProductoDao.listProductDBByID(idProducto);
    }

    public static void deleteProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id que quieres borrar");
        int idProducto = sc.nextInt();
        ProductoDao.deleteProductDB(idProducto);
    }

    public static void updateProduct(Producto producto) {
        ProductoDao.updateProductDB(producto);
    }

}
