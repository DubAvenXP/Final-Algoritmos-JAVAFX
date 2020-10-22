package database.service;

import database.dao.SaldoPendienteDao;
import database.models.SaldoPendiente;

public class SaldoPendienteService {

    public static void createDobter(SaldoPendiente saldoPendiente){
        SaldoPendienteDao.createDebtorDB(saldoPendiente);
    }

}
