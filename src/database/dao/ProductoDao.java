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

    public static void createProductDB(Producto producto){

        Connect connect = new Connect();
        PreparedStatement ps;

        try(Connection connection = connect.getConnection()){
            String sql = "INSERT INTO public.producto(\"idProducto\", nombre, precio, stock, descripcion, \"idProveedor\")\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getDescripcion());
            ps.setInt(6, producto.getIdProveedor());
            ps.executeUpdate();
            System.out.println("Producto creado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo crear" + e);
        }
        connect.closeConnection();
    }

    public static List listProductDB(){
        Connect connect = new Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();

        try(Connection connection = connect.getConnection()){
            String sql = "SELECT * FROM public.producto";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setIdProveedor(rs.getInt(6));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el producto" + e);
        }
        connect.closeConnection();
        return productos;
    }

    public static Producto listProductDBByID(int idProducto){
        Connect connect = new Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Producto producto = new Producto();

        try(Connection connection = connect.getConnection()){
            String sql = "SELECT \"idProducto\", nombre, precio, stock, descripcion, \"idProveedor\"\n" +
                    "\tFROM public.producto where \"idProducto\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            while (rs.next()){
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setIdProveedor(rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el producto" + e);
        }
        connect.closeConnection();
        return producto;
    }

    public static void deleteProductDB(int idProducto){
        Connect connect = new Connect();
        PreparedStatement ps = null;

        try (Connection connection = connect.getConnection()){
            String sql = "DELETE FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            System.out.println("Producto borrado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo borrar" + e);
        }
        connect.closeConnection();
    }

    public static void updateProductDB(Producto producto){
        Connect connect = new Connect();
        PreparedStatement ps;

        try(Connection connection = connect.getConnection()){
            String sql = "UPDATE public.producto SET \"idProducto\"=?, nombre=?, precio=?, stock=?, descripcion=?, \"idProveedor\"=?\n" +
                    "WHERE \"idProducto\"=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getDescripcion());
            ps.setInt(6, producto.getIdProveedor());
            ps.executeUpdate();
            System.out.println("Producto actualizado");
        } catch (SQLException e) {
            System.out.println("El producto no se pudo actualizarnombre" + e);
        }
        connect.closeConnection();

    }

}
