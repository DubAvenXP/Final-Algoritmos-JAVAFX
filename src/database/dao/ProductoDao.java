package database.dao;

import database.Connect;
import database.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    public static Integer idProducto;

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
