package database.service;

import database.dao.ClienteDao;
import database.dao.VendedorDao;
import database.models.Vendedor;

import java.util.Scanner;

public class VendedorService {

    public static void createSeller(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del vendedor");
        Integer id = sc.nextInt();
        System.out.println("Escribe el dpi del vendedor");
        String dpi = sc.next();
        System.out.println("Ingresa el nombre del vendedor");
        String name = sc.next();
        System.out.println("Ingresa el apellido del vendedor");
        String lastName = sc.next();
        System.out.println("Ingresa la direccion del vendedor");
        String direction = sc.next();
        System.out.println("Ingresa el telefono del vendedor");
        String phone = sc.next();

        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(id);
        vendedor.setNombre(name);
        vendedor.setApellido(lastName);
        vendedor.setDpi(dpi);
        vendedor.setDireccion(direction);
        vendedor.setTelefono(phone);
        VendedorDao.createSellerDB(vendedor);
    }

    public static void listSeller(){
        VendedorDao.viewSellerDB();
    }

    public static void listSellerByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del vendedor que quieres ver");
        int idVendedor = sc.nextInt();
        VendedorDao.viewSellerByID(idVendedor);
    }

    public static void deleteSeller(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del vendedor que quieres borrar");
        int idVendedor = sc.nextInt();
        VendedorDao.deleteSellerDB(idVendedor);
    }

    public static void updateSeller(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del vendedor");
        Integer id = sc.nextInt();
        System.out.println("Escribe el dpi del vendedor");
        String dpi = sc.next();
        System.out.println("Ingresa el nombre del vendedor");
        String name = sc.next();
        System.out.println("Ingresa el apellido del vendedor");
        String lastName = sc.next();
        System.out.println("Ingresa la direccion del vendedor");
        String direction = sc.next();
        System.out.println("Ingresa el telefono del vendedor");
        String phone = sc.next();

        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(id);
        vendedor.setNombre(name);
        vendedor.setApellido(lastName);
        vendedor.setDpi(dpi);
        vendedor.setDireccion(direction);
        vendedor.setTelefono(phone);
        VendedorDao.updateSeller(vendedor);
    }

}
