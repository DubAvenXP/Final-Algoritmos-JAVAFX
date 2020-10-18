package database.service;

import database.dao.ClienteDao;
import database.models.Cliente;

import java.util.List;


//Esta clase es la intermedia entre la capa dao y el modelo
public class ClienteService {

    public static void createClient(Cliente cliente) {
        ClienteDao.createClientDB(cliente);
    }

    public static List<Cliente> listClient(){
        return ClienteDao.viewClientDB();
    }

    public static Cliente listClientId(String nit){
        return ClienteDao.viewClientByNit(nit);
    }

    public static void deleteClient(String nit){
        ClienteDao.deleteClientDB(nit);
    }

    public static void updateClient(Cliente cliente){
        ClienteDao.updateClientDB(cliente);
    }

}
