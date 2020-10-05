package database;

import database.models.Proveedor;
import database.service.ClienteService;
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
            System.out.println("1. Crear Proveedor");
            System.out.println("2. Ver lista Proveedor");
            System.out.println("3. Borrar Proveedor");
            System.out.println("4. Editar Proveedor");
            System.out.println("5. Ver Proveedor por ID");
            System.out.println("6. Salir");
            option = input.nextInt();

            switch (option) {
                case 1:
                    VendedorService.createSeller();
                    break;
                case 2:
                    VendedorService.listSeller();
                    break;
                case 3:
                    VendedorService.deleteSeller();
                    break;
                case 4:
                    VendedorService.updateSeller();
                    break;
                case 5:
                    VendedorService.listSellerByID();
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

}
