package database.service;

import database.dao.ClienteDao;
import database.models.Cliente;

import java.util.List;
import java.util.Scanner;

//Esta clase es la intermedia entre la capa dao y el modelo
public class ClienteService {

    public static void createClient(Cliente cliente) {
        ClienteDao.createClientDB(cliente);
    }

    public static void listClient(){
        ClienteDao.viewClientDB();
    }

    public static List<Cliente> listClientId(String nit){
        return ClienteDao.viewClientById(nit);
    }

    public static void deleteClient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nit del cliente que quieres borrar");
        String nit = sc.next();
        ClienteDao.deleteClientDB(nit);
    }

    public static void updateClient(Cliente cliente){
        ClienteDao.createClientDB(cliente);
    }

}
