package database;

import database.service.ClienteService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void showMenu(){
        Scanner input = new Scanner(System.in);
        Integer option = 0;

        do {
            System.out.println("<----------------------------------->");
            System.out.println("1. Crear cliente");
            System.out.println("2. Ver lista Clientes");
            System.out.println("3. Borrar Cliente");
            System.out.println("4. Editar cliente");
            System.out.println("5. Salir");
            option = input.nextInt();

            switch (option) {
                case 1:
                    ClienteService.createClient();
                    break;
                case 2:
                    ClienteService.listClient();
                    break;
                case 3:
                    ClienteService.deleteClient();
                    break;
                case 4:
                    ClienteService.updateClient();
                    break;
                default:
                    break;
            }
        } while (option != 5);
    }

}
