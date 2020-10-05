package database.service;

import database.dao.ClienteDao;
import database.dao.ProveedorDao;
import database.models.Cliente;
import database.models.Proveedor;

import java.util.Scanner;

public class ProveedorService {

    public static void createProvider() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del proveedor");
        Integer id = sc.nextInt();
        System.out.println("Escribe el nombre del proveedor");
        String name = sc.next();
//        System.out.println("Escribe la descripcion del proveedor");
//        String description = sc.nextLine();

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(id);
        proveedor.setNombre(name);
//        proveedor.setDescripcion(description);
        ProveedorDao.createProviderDB(proveedor);
    }

    public static void viewProviderDB(){
        ProveedorDao.viewProviderDB();
    }

    public static void viewProviderByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del proveedor que quieres ver");
        int idProveedor = sc.nextInt();
        ProveedorDao.listProviderByID(idProveedor);
    }

    public static void deleteProvider(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del proveedor que quieres borrar");
        int idProveedor = sc.nextInt();
        ProveedorDao.deleteProvider(idProveedor);
    }

    public static void updateProvider(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del cliente a editar");
        Integer id = sc.nextInt();
        System.out.println("Escribe el nuevo nombre del proveedor");
        String name = sc.next();
        System.out.println("Ingresa la nueva descripcion del proveedor");
        String description = sc.next();

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(id);
        proveedor.setNombre(name);
        proveedor.setDescripcion(description);
        ProveedorDao.updateProviderDB(proveedor);
    }
}
