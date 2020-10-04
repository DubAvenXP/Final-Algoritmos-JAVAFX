package database.service;

import database.dao.ClienteDao;
import database.models.Cliente;

import java.sql.SQLException;
import java.util.Scanner;

//Esta clase es la intermedia entre la capa dao y el modelo
public class ClienteService {
    public static Scanner sc = new Scanner(System.in);
    public static void createClient() throws SQLException {
        System.out.println("Escribe el id del cliente");
        Integer id = sc.nextInt();
        System.out.println("Escribe el nit del cliente");
        String nit = sc.next();
        System.out.println("Ingresa el nombre del cliente");
        String name = sc.next();
        System.out.println("Ingresa el apellido del cliente");
        String lastName = sc.next();
        System.out.println("Ingresa la direccion del cliente");
        String direction = sc.next();
        System.out.println("Ingresa el telefono del cliente");
        String phone = sc.next();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setNit(nit);
        cliente.setNombre(name);
        cliente.setApellido(lastName);
        cliente.setDireccion(direction);
        cliente.setTelefono(phone);
        ClienteDao.createClientDB(cliente);
    }

    public static void listClient(){
        ClienteDao.viewClientDB();
    }

    public static void deleteClient(){

    }

    public static void updateClient(){

    }

}
