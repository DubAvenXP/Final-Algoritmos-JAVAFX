package database.dao;

import database.Connect;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendedorDao {

    public static void createSellerDB(Vendedor vendedor) {
        Connect connect = new Connect();
        PreparedStatement ps = null;
        try (Connection connection = connect.getConnection()) {
            String sql = "INSERT INTO public.vendedor(\"idVendedor\", nombre, apellido, dpi, direccion, telefono)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vendedor.getIdVendedor());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getApellido());
            ps.setString(4, vendedor.getDpi());
            ps.setString(5, vendedor.getDireccion());
            ps.setString(6, vendedor.getTelefono());
            ps.executeUpdate();
            System.out.println("Vendedor creado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo crear\n" + e);
        }
        connect.closeConnection();

    }

    public static void viewSellerDB() {
        Connect connect = new Connect();
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = connect.getConnection()) {
            String sql = "SELECT * FROM public.vendedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idVendedor"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("APELLIDO: " + rs.getString("apellido"));
                System.out.println("DPI: " + rs.getString("dpi"));
                System.out.println("DIRECCION: " + rs.getString("direccion"));
                System.out.println("TELEFONO: " + rs.getString("telefono"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los vendedores" + e);
        }
        connect.closeConnection();
    }

    public static void viewSellerByID(int idVendedor) {
        Connect connect = new Connect();
        PreparedStatement ps;
        ResultSet rs = null;
        try (Connection connection = connect.getConnection()) {
            String sql = "SELECT \"idVendedor\", nombre, apellido, dpi, direccion, telefono\n" +
                    "\tFROM public.vendedor where \"idVendedor\"=?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idVendedor"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("APELLIDO: " + rs.getString("apellido"));
                System.out.println("DPI: " + rs.getString("dpi"));
                System.out.println("DIRECCION: " + rs.getString("direccion"));
                System.out.println("TELEFONO: " + rs.getString("telefono"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer al vendedor\n" + e);
        }
        connect.closeConnection();
    }

    public static void deleteSellerDB(int idVendedor) {
        Connect connect = new Connect();
        PreparedStatement ps;
        try (Connection connection = connect.getConnection()) {
            String sql = "delete from \"vendedor\" where \"vendedor\".\"idVendedor\" = ?;\n";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo borrar" + e);
        }
        connect.closeConnection();
    }

    public static void updateSeller(Vendedor vendedor) {
        Connect connect = new Connect();
        PreparedStatement ps;
        try (Connection connection = connect.getConnection()) {
            String sql = "update \"vendedor\" set \"nombre\"=?, \"apellido\"=?, \"direccion\"=?, \"telefono\"=?, \"dpi\"=?" +
                    "where \"idVendedor\"=?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getApellido());
            ps.setString(3, vendedor.getDireccion());
            ps.setString(4, vendedor.getTelefono());
            ps.setString(5, vendedor.getDpi());
            ps.setInt(6, vendedor.getIdVendedor());
            ps.executeUpdate();
            System.out.println("Vendedor actualizado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo actualizar" + e);
        }
        connect.closeConnection();
    }

}
