package database.service;

import database.dao.SaldoPendienteDao;
import database.models.SaldoPendiente;

import java.util.List;

public class SaldoPendienteService {

    /**
     * Metodo que comunica con la capa Dao
     * @param saldoPendiente objeto de tipo SaldoPendiente que envia los parametros con la informacion del
     *                       deudor
     */
    public static void createDobter(SaldoPendiente saldoPendiente){
        SaldoPendienteDao.createDebtorDB(saldoPendiente);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @return retorna un List con los datos de todos los deudores en la base de datos
     */
    public static List<SaldoPendiente> viewAllDobter(){
        return SaldoPendienteDao.viewAllDobter();
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param nit perteneciente al deudor del que se quiere saber la informacion
     * @return retorna un List con los datos del deudor
     */
    public static List<SaldoPendiente>  viewDobter(String nit){
        return SaldoPendienteDao.viewDobter(nit);
    }

    /**
     * Metodo que comunica con la capa Dao
     * @param saldoPendiente objeto de tipo SaldoPendiente que envia los parametros con la informacion nueva del
     *                       deudor
     */
    public static void updateDobter(SaldoPendiente saldoPendiente){
        SaldoPendienteDao.updateDobter(saldoPendiente);
    }

}
