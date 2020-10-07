package database.service;

import database.dao.ClienteDao;
import database.dao.VendedorDao;
import database.models.Vendedor;

import java.util.Scanner;

public class VendedorService {

    public static void createSeller(Vendedor vendedor){
        VendedorDao.createSellerDB(vendedor);
    }

    public static void listSeller(){
        VendedorDao.viewSellerDB();
    }

    public static void listSellerByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el dpi del vendedor que quieres ver");
        String dpi = sc.next();
        VendedorDao.viewSellerByID(dpi);
    }

    public static void deleteSeller(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el dpi del vendedor que quieres borrar");
        String dpi = sc.next();
        VendedorDao.deleteSellerDB(dpi);
    }

    public static void updateSeller(Vendedor vendedor){
        VendedorDao.updateSeller(vendedor);
    }

}
