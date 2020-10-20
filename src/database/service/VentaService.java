package database.service;

import database.dao.VentaDao;

import java.util.Scanner;

public class VentaService {

    public static void totalSale(){
        int contador = 0;
        Integer id = 1;
        Integer quantity = 2;
        do {
            System.out.println("El id del producto es: " + id);
            id++;
            System.out.println("La cantidad de productos es: " + quantity);
            VentaDao.totalSaleDB(id, quantity);
            contador++;
        }while (contador <= 1);
    }

    public static void productAvailable(){
        Integer id = 1;
        Integer quantity = 10;
        VentaDao.productAvailableDB(id, quantity);
    }

}
