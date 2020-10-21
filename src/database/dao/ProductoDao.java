package database.dao;

import database.Connect;
import database.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla producto en la base de datos
 */
public class ProductoDao {
    public static Integer idProducto;

    /**
     * Metodo para crear un producto en la base de datos
     * @param producto objeto de tipo Producto que recibe como parametro para insertar los datos pertenecientes
     *                 al producto en la base de datos
     */
    public static void createProductDB(Producto producto){
        idProducto = autoIdProduct(producto.getIdProducto());
        PreparedStatement ps;

        try(Connection connection = Connect.getConnection()){
            String sql = "INSERT INTO public.producto(\"idProducto\", nombre, precio, stock, descripcion, \"idProveedor\")\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getDescripcion());
            ps.setInt(6, producto.getIdProvider());
            ps.executeUpdate();
            System.out.println("Producto creado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo crear" + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para ver todos los producto creados en la base de datos
     * @return retorna un List de Productos que existen en la base de datos con todos sus datos
     */
    public static List<Producto> listProductDB(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try(Connection connection = Connect.getConnection()){
            String sql = "SELECT \"idProducto\", nombre, precio, stock, descripcion, \"idProveedor\",  (SELECT (\"nombre\") " +
                    "FROM \"proveedor\" WHERE \"proveedor\".\"idProveedor\" = \"producto\".\"idProveedor\") " +
                    "AS \"Proveedor\" FROM \"producto\"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setIdProvider(rs.getInt(6));
                producto.setProvider(rs.getString(7));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el producto" + e);
        }
        Connect.closeConnection();
        return productos;
    }

    /**
     * Metodo para ver un unico producto la busqueda se hace por medio del id del producto en la base de datos
     * @param idProducto id perteneciente al producto que se quiere ver
     * @return retorna un Objeto de tipo Producto
     */
    public static Producto listProductDBByID(int idProducto){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Producto producto = new Producto();

        try(Connection connection = Connect.getConnection()){
            String sql = "SELECT \"idProducto\", nombre, precio, stock, descripcion, \"idProveedor\",  (SELECT (\"nombre\") " +
                    "FROM \"proveedor\" WHERE \"proveedor\".\"idProveedor\" = \"producto\".\"idProveedor\") " +
                    "AS \"Proveedor\" FROM \"producto\" where \"idProducto\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            while (rs.next()){
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setIdProvider(rs.getInt(6));
                producto.setProvider(rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el producto" + e);
        }
        Connect.closeConnection();
        return producto;
    }

    /**
     * Metodo oara eliminar un producto en la base de datos por medio de su id
     * @param idProducto id perteneciente al producto que se quiera eliminar
     */
    public static void deleteProductDB(int idProducto){
        PreparedStatement ps = null;

        try (Connection connection = Connect.getConnection()){
            String sql = "DELETE FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            System.out.println("Producto borrado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo borrar" + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para actualizar un producto en la base de datos
     * @param producto objeto de tipo Producto que recibe como parametro para insertar los nuevos datos en la
     *                 base de datos
     */
    public static void updateProductDB(Producto producto){
        PreparedStatement ps;

        try(Connection connection = Connect.getConnection()){
            String sql = "UPDATE public.producto SET nombre=?, precio=?, stock=?, descripcion=?, " +
                    "\"idProveedor\"=? WHERE \"idProducto\"=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getIdProvider());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();
            System.out.println("Producto actualizado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo actualizar" + e);
        }
        Connect.closeConnection();

    }

    /**
     * Metodo para hace el id del producto auto-incrementable al momento de crear un nuevo producto
     * @param id id perteneciente al producto
     * @return retorna un Integer que es el que se le asignara al nuevo producto al momento de crearse
     */
    public static Integer autoIdProduct(Integer id){
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()){
            String sql = "select max(\"idProducto\") from public.producto";
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
