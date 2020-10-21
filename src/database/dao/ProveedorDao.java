package database.dao;

import database.Connect;
import database.models.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla proveedor en la base de datos
 */
public class ProveedorDao {
    public static Integer idProveedor;

    /**
     * Metodo para crear un nuevo vendedor en la base de datos
     * @param proveedor objeto de tipo Proveedor que recibe como parametro para insertar los datos pertenecientes
     *                al proveedor en la base de datos
     */
    public static void createProviderDB(Proveedor proveedor) {
        PreparedStatement ps;
        idProveedor = autoIdProvide();
        try (Connection connection = Connect.getConnection()){
            String sql = "INSERT INTO public.proveedor(\"idProveedor\", nombre, descripcion)" +
                    "VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getDescripcion());
            ps.executeUpdate();
            System.out.println("Proveedor creado");
        } catch (SQLException e) {
            System.out.println("El proveedor no se pudo crear" + e);
        }
        Connect.closeConnection();
    }

    /**
     *Metodo para ver todos los proveedores existentes en la base de datos
     * @return retorna un List de Proveedor con los datos extraidos de la base de datos
     */
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

    /**
     * Metodo para ver un unico proveedor
     * @param idProveedor perteneciente al proveedor que se quiere ver
     * @return devuelce un objeto de tipo Proveedor
     */
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

    /**
     * Metodo para eliminar un proveedor en la base de datos
     * @param idProveedor perteneciente al proveedor que se quiera eliminar
     */
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

    /**
     * Metodo para actualizar un provedor
     * @param proveedor objeto de tipo Proveedor que recibe como parametro para insertar los nuevos datos en la
     *                 base de datos
     */
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
            System.out.println(e + "\nEl provedor no se pudo actualizar");
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para hace el id del proveedor auto-incrementable al momento de crear un nuevo proveedor
     * @return retorna un Integer que es el que se le asignara al nuevo Proveedor al momento de crearse
     */
    public static Integer autoIdProvide(){
        PreparedStatement ps;
        ResultSet rs;
        Integer id = 0;
        try (Connection connection = Connect.getConnection()){
            String sql = "select max(\"idProveedor\") from public.proveedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) id = rs.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
}
