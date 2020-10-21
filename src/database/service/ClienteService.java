package database.service;

import database.dao.ClienteDao;
import database.models.Cliente;

import java.util.List;

/**
 * @author glasd
 * Esta clase es la capa que comunica la capa Dao y los modelos de Cliente con la intefaz de usuario
 */
public class ClienteService {

    /**
     *Metodo que comunica con la capa Dao
     * @param cliente objeto de tipo Cliente que envia como parametro para crear un nuevo cliente
     */
    public static void createClient(Cliente cliente) {
        ClienteDao.createClientDB(cliente);
    }

    /**
     * Metodo que comunica con la Capa Dao
     * @return retona un List de todos los cliente existentes en la base de datos
     */
    public static List<Cliente> listClient(){
        return ClienteDao.viewClientDB();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param nit perteneciente al cliente que se quiere ver
     * @return retorna un String con el nombre y apellido del cliente
     */
    public static String listClientId(String nit){
        return ClienteDao.viewClientByNit(nit);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param nit perteneciente al cliente que se quiera eliminar
     */
    public static void deleteClient(String nit){
        ClienteDao.deleteClientDB(nit);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param cliente objeto de tipo Cliente que envia como parametro los nuevos datos del cliente
     */
    public static void updateClient(Cliente cliente){
        ClienteDao.updateClientDB(cliente);
    }

}
