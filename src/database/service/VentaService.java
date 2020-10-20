package database.service;

import database.dao.VentaDao;


public class VentaService {

    //    Retorna el total de la venta de 1 producto
    public static Double totalSale(Integer id, Integer quantity) {
        int contador = 0;
        Double totalVenta;
        do {
            //System.out.println("El id del producto es: " + id);
            id++;
            //System.out.println("La cantidad de productos es: " + quantity);
            totalVenta = VentaDao.totalSaleDB(id, quantity);
            contador++;
        } while (contador <= 1);
        return totalVenta;
    }

    //    Retorna la cantidad de productos en stock luego de restarle los que se van a comprar
    public static Integer productAvailable(Integer id, Integer quantity) {
        return VentaDao.productAvailableDB(id, quantity);
    }

}
