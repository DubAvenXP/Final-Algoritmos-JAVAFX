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
        idProducto = autoIdProduct();
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
                    "AS \"Proveedor\" FROM public.producto order by \"idProveedor\" asc";
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
     * @return retorna un Integer que es el que se le asignara al nuevo producto al momento de crearse
     */
    public static Integer autoIdProduct(){
        PreparedStatement ps;
        ResultSet rs;
        Integer id = 0;
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

    /**
     * Metodo para retornar productos sin stock
     * @return retorna un List de Producto con toda la informacion de los productos sin stock
     */
    public static List<Producto> stockZeroDB(){
        PreparedStatement ps;
        ResultSet rs;
        List<Producto> productoList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()){
            String stockZero = "select * from public.producto where stock = 0";
            ps = connection.prepareStatement(stockZero);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setIdProvider(rs.getInt(2));
                producto.setNombre(rs.getString(3));
                producto.setPrecio(rs.getDouble(4));
                producto.setStock(rs.getInt(5));
                producto.setDescripcion(rs.getString(6));
                productoList.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los productos sin stock\n" + e);
        }
        Connect.closeConnection();
        return productoList;
    }

    /**
     * Metodo para ver los 5 productos que vas veces se venden en la base de datos
     * @return retorna los productos con mayor cantidad vendida
     */
    public static List<Producto> bestSellers(){
        PreparedStatement ps;
        ResultSet rs;
        List<Producto> productoList = new ArrayList<>();
        Integer count = 0;
        try(Connection connection = Connect.getConnection()){
            String bestSeller = "select \"idProducto\", count(*),(SELECT (\"nombre\") FROM \"producto\" WHERE \"producto\".\"idProducto\" = \"ventaProducto\".\"idProducto\")" +
                    "as \"nombre\", (select (\"descripcion\") FROM \"producto\" WHERE \"producto\".\"idProducto\" = \"ventaProducto\".\"idProducto\")\n" +
                    "as \"descripcion\", (select (\"precio\") FROM \"producto\" WHERE \"producto\".\"idProducto\" = \"ventaProducto\".\"idProducto\") as \"precio\" \n" +
                    "from public.\"ventaProducto\" group by \"idProducto\" order by count desc  FETCH FIRST 5 ROWS ONLY";
            ps = connection.prepareStatement(bestSeller);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setBestSellerCount(ProductoDao.totalSell(rs.getInt(1)));
                producto.setNombre(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(null);
                productoList.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los productos\n" + e);
        }
        Connect.closeConnection();
        return productoList;
    }

    /**
     * Metodo que tiene la logica para hacer la suma de las cantidades de producto vendidos
     * @param id parametro que recibe perteneciente al id del producto
     * @return retorna la cantidad de productos vendidos totales
     */
    public static Integer totalSell(Integer id){
        Integer quantitySell = 0;
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()){
            String sql = "select \"idProducto\", cantidad from public.\"ventaProducto\" where \"idProducto\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                Integer aux = rs.getInt(2);
                quantitySell += aux;
            }
            System.out.println("Fin suma-------");
        } catch (SQLException e) {
            System.out.println("No se pudo hacer la suma" + e);
        }
        Connect.closeConnection();
        return quantitySell;
    }

    /**
     * Metodo que trae los producto ordenados de mayor a menor precio
     * @return retorna un List de productos con los datos
     */
    public static List<Producto> highestPrice(){
        PreparedStatement ps;
        ResultSet rs;
        List<Producto> productoList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()){
            String sql = "SELECT \"idProducto\", nombre, precio, stock, descripcion, (SELECT (\"nombre\") FROM \"proveedor\"  \n" +
                    "WHERE \"proveedor\".\"idProveedor\" = \"producto\".\"idProveedor\") AS \"Proveedor\" FROM \"producto\"" +
                    "ORDER BY precio DESC LIMIT 10";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setProvider(rs.getString(6));
                productoList.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("No se pudieron traer lo productos\n" + e);
        }
        for (Producto producto : productoList) {
            System.out.println("ID PRODUCTO:" + producto.getIdProducto());
            System.out.println("NOMBRE: " + producto.getNombre());
            System.out.println("PRECIO:" + producto.getPrecio());
            System.out.println("STOCK:" + producto.getStock());
            System.out.println("DESCRIPCION:" + producto.getDescripcion());
            System.out.println("ID PROVEEDOR:" + producto.getIdProvider());
            System.out.println("DESC PROVEEDOR:" + producto.getProvider());
            System.out.println();
        }
        Connect.closeConnection();
        return productoList;
    }

    /**
     * Meotodo que trae los productos ordenados de menor a mayor precio
     * @return retona un List con la informacion de productos
     */
    public static List<Producto> lowestPrice(){
        PreparedStatement ps;
        ResultSet rs;
        List<Producto> productoList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()){
            String sql = "SELECT \"idProducto\", nombre, precio, stock, descripcion, (SELECT (\"nombre\") FROM \"proveedor\"  \n" +
                    "WHERE \"proveedor\".\"idProveedor\" = \"producto\".\"idProveedor\") AS \"Proveedor\" FROM \"producto\"" +
                    "ORDER BY precio ASC  LIMIT 10";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setProvider(rs.getString(6));
                productoList.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("No se pudieron traer lo productos\n" + e);
        }
        for (Producto producto : productoList) {
            System.out.println("ID PRODUCTO:" + producto.getIdProducto());
            System.out.println("NOMBRE: " + producto.getNombre());
            System.out.println("PRECIO:" + producto.getPrecio());
            System.out.println("STOCK:" + producto.getStock());
            System.out.println("DESCRIPCION:" + producto.getDescripcion());
            System.out.println("ID PROVEEDOR:" + producto.getIdProvider());
            System.out.println("DESC PROVEEDOR:" + producto.getProvider());
            System.out.println();
        }
        Connect.closeConnection();
        return productoList;
    }


}
