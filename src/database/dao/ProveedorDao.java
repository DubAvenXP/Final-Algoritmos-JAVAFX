package database.dao;

import database.Connect;
import database.models.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

    public static void createProviderDB(Proveedor proveedor) {
        Connect connect = new Connect();

        try (Connection connection = connect.getConnection()) {
            PreparedStatement ps;
            try {
                String sql = "INSERT INTO public.proveedor(\"idProveedor\", nombre, descripcion)" +
                        "VALUES (?, ?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, proveedor.getIdProveedor());
                ps.setString(2, proveedor.getNombre());
                ps.setString(3, proveedor.getDescripcion());
                ps.executeUpdate();
                System.out.println("Proveedor creado");
            } catch (SQLDataException e) {
                System.out.println(e + "\nEl proveedor no se pudo crear");

            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl proveedor no se pudo crear");
        }

        connect.closeConnection();
    }

    public static List viewProviderDB() {

        Connect connect = new Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proveedor> proveedores = new ArrayList<>();
        Proveedor proveedor = new Proveedor();

        try (Connection connection = connect.getConnection()) {
            String sql = "SELECT * FROM public.proveedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setDescripcion(rs.getString(3));

                System.out.println(proveedor.getNombre());
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los proveedores\n" + e);
        }
        connect.closeConnection();
        return proveedores;
    }

    public static Proveedor viewProviderByID(int idProveedor) {
        Connect connect = new Connect();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Proveedor proveedor = new Proveedor();

        try (Connection connection = connect.getConnection()) {
            String sql = "SELECT * FROM public.proveedor WHERE \"idProveedor\"=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setDescripcion(rs.getString(3));

                System.out.println(proveedor.getIdProveedor());
                System.out.println(proveedor.getNombre());
                System.out.println(proveedor.getDescripcion());
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los proveedores\n" + e);
        }
        connect.closeConnection();
        return proveedor;
    }

    public static void deleteProvider(int idProveedor)  {
        Connect connect = new Connect();

        PreparedStatement ps = null;
        try (Connection connection = connect.getConnection()) {
            String sql = "delete from \"proveedor\" where \"proveedor\".\"idProveedor\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se borrar pudo el proveedor\n" + e);

        }
        connect.closeConnection();
    }

    public static void updateProviderDB(Proveedor proveedor) {
        Connect connect = new Connect();

        try (Connection connection = connect.getConnection()) {
            PreparedStatement ps;
            try {
                String sql = "update \"proveedor\" set \"nombre\"=?, \"descripcion\"=? where \"idProveedor\"=?;";
                ps = connection.prepareStatement(sql);
                ps.setString(1, proveedor.getNombre());
                ps.setString(2, proveedor.getDescripcion());
                ps.setInt(3, proveedor.getIdProveedor());
                ps.executeUpdate();
                System.out.println("Proveedor actualizado");
            } catch (SQLDataException e) {
                System.out.println(e + "\nEl proveedor no se pudo acrualizar");
            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl cliente no se pudo actualizar");
        }
        connect.closeConnection();
    }
}
