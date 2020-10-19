package database.dao;

import database.Connect;
import database.models.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

    public static void createProviderDB(Proveedor proveedor) {
        PreparedStatement ps;
        try (Connection connection = Connect.getConnection()){
            String sql = "INSERT INTO public.proveedor(\"idProveedor\", nombre, description)" +
                    "VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, autoIdProvide(proveedor.getIdProveedor()));
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getDescripcion());
            ps.executeUpdate();
            System.out.println("Proveedor creado");
        } catch (SQLException e) {
            System.out.println("El proveedor no se pudo crear" + e);
        }
        Connect.closeConnection();
    }

    public static List<Proveedor> viewProviderDB() {
        PreparedStatement ps;
        ResultSet rs;
        List<Proveedor> proveedorList = new ArrayList<>();

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT * FROM public.proveedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setDescripcion(rs.getString(3));
                proveedorList.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los proveedores\n" + e);
        }
        Connect.closeConnection();
        return proveedorList;
    }

    public static Proveedor viewProviderByID(int idProveedor) {
        Proveedor proveedor = new Proveedor();
        PreparedStatement ps;
        ResultSet rs;

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT * FROM public.proveedor WHERE \"idProveedor\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setDescripcion(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los proveedores\n" + e);
        }
        Connect.closeConnection();
        return proveedor;
    }

    public static void deleteProvider(int idProveedor)  {
        PreparedStatement ps;
        ResultSet rs;

        try (Connection connection = Connect.getConnection()) {
            String sql = "delete from \"proveedor\" where \"proveedor\".\"idProveedor\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
            System.out.println("Proveedor eliminado");
        } catch (SQLException e) {
            System.out.println("No se borrar pudo el proveedor\n" + e);

        }
        Connect.closeConnection();
    }

    public static void updateProviderDB(Proveedor proveedor) {
        PreparedStatement ps;

        try (Connection connection = Connect.getConnection()) {
            try {
                String sql = "update \"proveedor\" set \"nombre\"=?, \"description\"=? where \"idProveedor\"=?;";
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
        Connect.closeConnection();
    }

    public static Integer autoIdProvide(Integer id){
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()){
            String sql = "select max(\"idProveedor\") from public.proveedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
}
