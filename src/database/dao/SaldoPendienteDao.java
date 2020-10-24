package database.dao;

import database.Connect;
import database.models.Cliente;
import database.models.SaldoPendiente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SaldoPendienteDao {

    public static Integer idDobter;

    public static void createDebtorDB(SaldoPendiente saldoPendiente) {
        PreparedStatement ps;
        idDobter = autoIdDobter();
        if (saldoPendiente.getTipoPago().equalsIgnoreCase("cuotas")) {
            try (Connection connection = Connect.getConnection()) {
                String sql = "INSERT INTO public.\"saldoPendiente\"(\"idSaldoPendiente\", \"nitCliente\", " +
                        "\"nombreCliente\", \"totalPagar\", \"deudaPendiente\", abono) VALUES (?, ?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, idDobter);
                ps.setString(2, saldoPendiente.getNitClient());
                ps.setString(3, saldoPendiente.getNombreCliente());
                ps.setDouble(4, saldoPendiente.getTotalPagar());
                ps.setDouble(5, saldoPendiente.getDeudaPendiente());
                ps.setDouble(6, saldoPendiente.getAbono());
                ps.executeUpdate();
                System.out.println("Deudor creado con exito");
            } catch (SQLException e) {
                System.out.println("No se pudo crear el deudor" + e);
            }
        }
        Connect.closeConnection();
    }

    public static Integer autoIdDobter() {
        PreparedStatement ps;
        ResultSet rs;
        Integer id = null;
        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT MAX(\"idSaldoPendiente\") FROM public.\"saldoPendiente\"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                id = rs.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        Connect.closeConnection();
        return id;
    }

//    public static List<SaldoPendiente> viewDobter(){
//
//    }

//    public static Cliente clientDobter(String nit){
//        return
//    }

}
