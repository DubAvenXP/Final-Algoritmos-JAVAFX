package database.dao;

import database.Connect;
import database.models.Cliente;
import database.models.Producto;
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
            ps.setInt(2, venta.getIdSeller());
            ps.setString(2, venta.getNitClient());
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

    public static Double totalSaleDB(Integer id, Integer quantity){
        Double total = 0.0;
        try(Connection connection = Connect.getConnection()){
            PreparedStatement ps;
            PreparedStatement ps1;
            PreparedStatement ps2;
            ResultSet rs;
            ResultSet rs1;
            String priceDB = "SELECT precio FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(priceDB);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                System.out.println("El precio unitario es: " + rs.getInt(1));
                total = rs.getDouble(1) * quantity;
                venta.setMonto(total);
                System.out.println("El total es: " + total);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la suma" + e);
        }
        Connect.closeConnection();
        return total;
    }

    public static Integer productAvailableDB(Integer id, Integer quantity){
        Integer restante = 0;
        try (Connection connection = Connect.getConnection()){
            PreparedStatement ps;
            ResultSet rs;
            String stockDB = "SELECT stock FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(stockDB);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            Producto producto = new Producto();
            while (rs.next()){
                System.out.println("La cantidad de productos disponibles es: " + rs.getInt(1));
                restante = rs.getInt(1) - quantity;
                producto.setStock(restante);
                System.out.println("La cantidad de productos restantes es: " + producto.getStock());
            }

            String update = "UPDATE public.producto SET stock=? WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(update);
            ps.setInt(1, producto.getStock());
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Stock actualizado");
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el stock" + e);
        }
        return restante;
    }

}
