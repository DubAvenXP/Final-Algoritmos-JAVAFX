package database.dao;

import database.Connect;
import database.models.SaldoPendiente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaldoPendienteDao {

    public static Integer idDobter;

    /**
     * Metodo para crear un deudor en la base de datos
     *
     * @param saldoPendiente objeto de tipo SaldoPendiente que recibe los parametros para insertarlos en la base
     *                       de datos
     */
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

    /**
     * Metodo para hace el id del deudor auto-incrementable al momento de crear un nuevo deudor
     *
     * @return
     */
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

    /**
     * Metodo que trae la informacion de todos los deudores de la base de datos
     *
     * @return retorna un List con la informacion de los deudores
     */
    public static List<SaldoPendiente> viewAllDobter() {
        PreparedStatement ps;
        ResultSet rs;
        List<SaldoPendiente> dobterList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()) {
            String sql = "select * from public.\"saldoPendiente\"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SaldoPendiente dobter = new SaldoPendiente();
                dobter.setIdSaldoPendiente(rs.getInt(1));
                dobter.setNitClient(rs.getString(2));
                dobter.setNombreCliente(rs.getString(3));
                dobter.setTotalPagar(rs.getDouble(4));
                dobter.setDeudaPendiente(rs.getDouble(5));
                dobter.setAbono(rs.getDouble(6));
                dobterList.add(dobter);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los deudores\n" + e);
        }
        for (SaldoPendiente saldoPendiente : dobterList) {
            System.out.println("ID:" + saldoPendiente.getIdSaldoPendiente());
            System.out.println("NIT: " + saldoPendiente.getNitClient());
            System.out.println("NOMBRE:" + saldoPendiente.getNombreCliente());
            System.out.println("TOTAL:" + saldoPendiente.getTotalPagar());
            System.out.println("DEUDA:" + saldoPendiente.getDeudaPendiente());
            System.out.println("ABONO:" + saldoPendiente.getAbono());
            System.out.println();
        }
        return dobterList;
    }

    /**
     * Metodo que trae la informacion de un solo deudor de la base de datos
     *
     * @param nit perteneciente al deudor de que se quiere tener la informacion
     * @return retorna un objeto de tipo SaldoPendiente con la informacion
     */
    public static SaldoPendiente viewDobter(String nit) {
        PreparedStatement ps;
        ResultSet rs;
        SaldoPendiente dobter = new SaldoPendiente();
        try (Connection connection = Connect.getConnection()) {
            String sql = "select * from public.\"saldoPendiente\" where \"nitCliente\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nit);
            rs = ps.executeQuery();

            while (rs.next()) {
                dobter.setIdSaldoPendiente(rs.getInt(1));
                dobter.setNitClient(rs.getString(2));
                dobter.setNombreCliente(rs.getString(3));
                dobter.setTotalPagar(rs.getDouble(4));
                dobter.setDeudaPendiente(rs.getDouble(5));
                dobter.setAbono(rs.getDouble(6));
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los deudores\n" + e);
        }
        return dobter;
    }

    /**
     * Metodo para actualizar los abono que realiza el deudor
     *
     * @param saldoPendiente objeto de tipo SaldoPendiente que recibe los parametros para insertarlos en la base
     *                       de datos
     */
    public static void updateDobter(SaldoPendiente saldoPendiente) {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()) {
            String sql = "UPDATE public.\"saldoPendiente\" SET \"deudaPendiente\"=?, abono=?" +
                    "WHERE \"nitCliente\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, saldoPendiente.getDeudaPendiente());
            ps.setDouble(2, saldoPendiente.getAbono());
            ps.setString(3, saldoPendiente.getNitClient());
            ps.executeUpdate();
            System.out.println("Deudor actualizado");
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el deudor\n" + e);
        }
        Connect.closeConnection();
    }

}
