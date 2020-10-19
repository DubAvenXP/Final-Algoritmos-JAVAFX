package database.dao;

import database.Connect;
import database.models.Cliente;
import database.models.Vendedor;
import database.models.Venta;

import java.sql.*;
import java.util.List;

import static database.dao.ClienteDao.idClient;
import static database.dao.VendedorDao.idSeller;

public class VentaDao {

    public static Integer idSale;

    public static void createSale(Venta venta){
        idSale = autoIdSale(venta.getIdVenta());
        PreparedStatement ps;
        try(Connection connection = Connect.getConnection()){
            String sql = "INSERT INTO public.venta(\"idVenta\", \"idVendedor\", \"idCliente\", numeroventa, fechaventa, monto)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSale);
            ps.setInt(2, idSeller);
            ps.setInt(2, idClient);
            ps.setString(3, venta.getNumeroVenta());
            ps.setString(4, (String) venta.getFecha());
            ps.setDouble(5, venta.getMonto());
            ps.executeUpdate();
            System.out.println("Venta generada con exito");
        } catch (SQLException e) {
            System.out.println("No se pudo generar la venta." + e);
        }
    }

//    public static List<Venta> viewSaleDB(){
//
//    }

    public static Integer autoIdSale(Integer id){
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()) {
            String sql = "select max(\"idVenta\") from public.venta";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

}
