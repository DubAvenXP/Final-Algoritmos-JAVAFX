package database.service;

import database.dao.ClienteDao;
import database.models.Cliente;

import java.util.Scanner;

//Esta clase es la intermedia entre la capa dao y el modelo
public class ClienteService {
    public static void createClient() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del cliente que quieres borrar");
        int idCliente = sc.nextInt();
        ClienteDao.deleteClientDB(idCliente);
    }

    public static void updateClient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el id del cliente a editar");
        Integer id = sc.nextInt();
        System.out.println("Escribe el nuevo nit del cliente");
        String nit = sc.next();
        System.out.println("Ingresa el nuevo nombre del cliente");
        String name = sc.next();
        System.out.println("Ingresa el nuevo apellido del cliente");
        String lastName = sc.next();
        System.out.println("Ingresa la nuevo direccion del cliente");
        String direction = sc.next();
        System.out.println("Ingresa el nuevo telefono del cliente");
        String phone = sc.next();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setNit(nit);
        cliente.setNombre(name);
        cliente.setApellido(lastName);
        cliente.setDireccion(direction);
        cliente.setTelefono(phone);
        ClienteDao.updateClientDB(cliente);
    }

}
