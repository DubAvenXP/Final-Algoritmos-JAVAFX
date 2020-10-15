package database;

import database.models.Cliente;
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
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Ingresa el nombre");
                    String name = scanner.nextLine();
                    System.out.println("Ingresa el apellido");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingresa el nit");
                    String nit = scanner.nextLine();
                    System.out.println("Ingresa la direccion");
                    String direccion = scanner.nextLine();
                    System.out.println("Ingresa el telefono");
                    String telefono = scanner.nextLine();

                    Cliente cliente = new Cliente();
                    cliente.setNombre(name);
                    cliente.setApellido(apellido);
                    cliente.setNit(nit);
                    cliente.setDireccion(direccion);
                    cliente.setTelefono(telefono);
                    ClienteService.createClient(cliente);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (option != 6);
    }

}
