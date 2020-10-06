package database;

import database.models.Proveedor;
import database.service.ClienteService;
import database.service.ProductoService;
import database.service.ProveedorService;
import database.service.VendedorService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void showMenu(){
        Scanner input = new Scanner(System.in);
        Integer option = 0;

        do {
            System.out.println("<----------------------------------->");
            System.out.println("1. Crear");
            System.out.println("2. Ver lista");
            System.out.println("3. Ver por ID");
            System.out.println("4. Editar");
            System.out.println("5. Borrar");
            System.out.println("6. Salir");
            option = input.nextInt();

            switch (option) {
                case 1:
//                    ClienteService.createClient();
//                    ProductoService.createProduct();
                    break;
                case 2:
//                    ClienteService.listClient();
//                    ProveedorService.listProvider();
//                    ProductoService.listProduct();
                    break;
                case 3:
//                    ClienteService.listClientId();
//                    ProveedorService.listProviderByID();
//                    ProductoService.listProductByID();
                    break;
                case 4:
//                    ClienteService.updateClient();
//                    ProveedorService.updateProvider();
//                    ProductoService.updateProduct();
                    break;
                case 5:
//                    ClienteService.deleteClient();
//                    ProveedorService.deleteProvider();
//                    ProductoService.deleteProduct();
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

}
