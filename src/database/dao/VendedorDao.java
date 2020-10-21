package database.dao;

import database.Connect;
import database.models.Producto;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla vendedor en la base de datos
 */
public class VendedorDao {

    public static Integer idSeller;

    /**
     * Metodo para crear un nuevo vendedor en la base de datos
     * @param vendedor objeto de tipo Producto que recibe como parametro para insertar los datos pertenecientes
     *                al producto en la base de datos
     */
    public static void createSellerDB(Vendedor vendedor) {
        PreparedStatement ps = null;
        idSeller = autoIdSeller(vendedor.getIdVendedor());
        try (Connection connection = Connect.getConnection()) {
            String sql = "INSERT INTO public.vendedor(\"idVendedor\", nombre, apellido, dpi, direccion, telefono)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSeller);
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
        Connect.closeConnection();

    }

    /**
     *Metodo para ver todos los vendedores existentes en la base de datos
     * @return retorna un List de Vendedores con los datos extraidos de la base de datos
     */
    public static List viewSellerDB() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vendedor> vendedores = new ArrayList<>();

        try(Connection connection = Connect.getConnection()){
            String sql = "SELECT * FROM public.vendedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido(rs.getString(3));
                vendedor.setDpi(rs.getString(4));
                vendedor.setDireccion(rs.getString(5));
                vendedor.setTelefono(rs.getString(6));
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println("No se traer el vendedor" + e);
        }
        Connect.closeConnection();
        return vendedores ;
    }

    /**
     * Metodo para ver un unico vendedor
     * @param id perteneciente al vendedor que se quiere ver
     * @return devuelce un objeto de tipo Vendedor
     */
    public static Vendedor viewSellerByID(Integer id) {
        PreparedStatement ps;
        ResultSet rs = null;
        Vendedor vendedor = new Vendedor();

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT \"idVendedor\", nombre, apellido, dpi, direccion, telefono\n" +
                    "\tFROM public.vendedor where \"idVendedor\"=?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                vendedor.setIdVendedor(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido(rs.getString(3));
                vendedor.setDpi(rs.getString(4));
                vendedor.setDireccion(rs.getString(5));
                vendedor.setTelefono(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer al vendedor\n" + e);
        }
        Connect.closeConnection();
        return vendedor;
    }

    /**
     * Metodo para eliminar un vendedor en la base de datos
     * @param id perteneciente al vendedor que se quiera eliminar
     */
    public static void deleteSellerDB(Integer id) {
        PreparedStatement ps;
        try (Connection connection = Connect.getConnection()) {
            String sql = "delete from \"vendedor\" where \"idVendedor\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Vendedor eliminado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo borrar" + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para actualizar un cliente
     * @param vendedor objeto de tipo Vendedor que recibe como parametro para insertar los nuevos datos en la
     *                 base de datos
     */
    public static void updateSeller(Vendedor vendedor) {
        PreparedStatement ps;
        try (Connection connection = Connect.getConnection()) {
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
        Connect.closeConnection();
    }

    /**
     * Metodo para hace el id del vendedor auto-incrementable al momento de crear un nuevo vendedor
     * @param id id perteneciente al vendedor
     * @return retorna un Integer que es el que se le asignara al nuevo Vendedor al momento de crearse
     */
    public static Integer autoIdSeller(Integer id){
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()){
            String sql = "select max(\"idVendedor\") from public.vendedor";
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
