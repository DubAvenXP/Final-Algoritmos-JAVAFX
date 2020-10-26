package database.dao;

import database.Connect;
import database.models.VentaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla ventaProducto en la base de datos
 */
public class VentaProductoDao {

    public static Integer idProductSale;

    /**
     * Metodo para guardar los productos de una factura ne la base de datos
     *
     * @param productoList recibe un List de tipo VentaProducto donde traer los parametros del producto para ser insertados
     *                     en la base de datos
     */
    public static void saveBillDB(List<VentaProducto> productoList) {
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps;
            idProductSale = autoIdSaleProduct();
            String sql = "INSERT INTO public.\"ventaProducto\"(\"idVentaProducto\", \"serieVenta\", \"idProducto\", cantidad, \"precioVenta\")" +
                    "VALUES" + valuesProducts(productoList);
            ps = connection.prepareStatement(sql);
            Integer index = 1;
            for (VentaProducto v : productoList) {
                ps.setInt(index, idProductSale);
                ps.setString(++index, v.getSerieVenta());
                ps.setInt(++index, v.getIdProducto());
                ps.setInt(++index, v.getCantidad());
                ps.setDouble(++index, v.getPrecioVenta());
                index++;
                idProductSale++;
            }
            ps.executeUpdate();
            System.out.println("Factura guardada con exito");
        } catch (SQLException e) {
            System.out.println("No se pudo guardar la factura" + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para hace el id del producto auto-incrementable al momento de crear un nuevo producto
     *
     * @return retorna un Integer que es el que se le asignara al nuevo producto al momento de crearse
     */
    public static Integer autoIdSaleProduct() {
        PreparedStatement ps;
        ResultSet rs;
        Integer id = null;
        try (Connection connection = Connect.getConnection()) {
            String sql = "select max(\"idVentaProducto\") from public.\"ventaProducto\"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                id = rs.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        Connect.closeConnection();
        return id;
    }

    /**
     * Metodo para ver toda la informacion de la venta de los productos en la facturacion
     *
     * @return retorna un List con la informacion de la factura guardada
     */
    public static List<VentaProducto> viewSalesProducts() {
        PreparedStatement ps;
        ResultSet rs;
        List<VentaProducto> listVentaProducto = new ArrayList<>();
        try (Connection connection = Connect.getConnection()) {
            String viewBill = "SELECT * FROM public.\"ventaProducto\"";
            ps = connection.prepareStatement(viewBill);
            rs = ps.executeQuery();
            while (rs.next()) {
                VentaProducto ventaProducto = new VentaProducto();
                ventaProducto.setIdVentaProducto(rs.getInt(1));
                ventaProducto.setSerieVenta(rs.getString(2));
                ventaProducto.setIdVentaProducto(rs.getInt(3));
                ventaProducto.setCantidad(rs.getInt(4));
                ventaProducto.setPrecioVenta(rs.getDouble(5));
                listVentaProducto.add(ventaProducto);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer la informacion de facturacion");
        }
        Connect.closeConnection();
        return listVentaProducto;
    }

    /**
     * Este metodo es el que evalua la cantidad de productos que trae el List de VentaProducto
     *
     * @param productoList recibe un List de tipo VentaProducto donde traer los parametros del producto para ser insertados
     *                     en la base de datos
     * @return retorna un string con la secuencia sql correcta para hacer el query en la base de datos
     */
    public static String valuesProducts(List<VentaProducto> productoList) {
        String productSize = "";
        if (productoList.size() == 5) {
            productSize = "(?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";
        } else if (productoList.size() == 4) {
            productSize = "(?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";
        } else if (productoList.size() == 3) {
            productSize = "(?, ?, ?, ?, ?), (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";
        } else if (productoList.size() == 2) {
            productSize = "(?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";
        } else if (productoList.size() == 1) {
            productSize = "(?, ?, ?, ?, ?)";
        }
        return productSize;
    }

    /**
     * Metodo que traer todos lo productos vendidos en la facturas
     *
     * @return retorna un List con la informacion de las ventas de los prpductos
     */
    public static List<VentaProducto> viewAllProductSales() {
        PreparedStatement ps;
        ResultSet rs;
        List<VentaProducto> ventaProductoList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()) {
            String sql = "select \"serieVenta\", \"idProducto\", cantidad, \"precioVenta\",\n" +
                    "(select (nombnre) from producto where producto.\"idProducto\" = \"ventaProducto\".\"idProducto\") as \"nombre\",\n" +
                    "(select (descripcion) from producto where producto.\"idProducto\" = \"ventaProducto\".\"idProducto\") as \"descripcion\"\n" +
                    "from public.\"ventaProducto\" order by \"serieVenta\" asc";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                VentaProducto ventaProducto = new VentaProducto();
                ventaProducto.setSerieVenta(rs.getString(1));
                ventaProducto.setIdProducto(rs.getInt(2));
                ventaProducto.setCantidad(rs.getInt(3));
                ventaProducto.setPrecioVenta(rs.getDouble(4));
                ventaProducto.setNombreProducto(rs.getString(5));
                ventaProducto.setDescripcionProducto(rs.getString(6));
                ventaProductoList.add(ventaProducto);
            }
            for (VentaProducto ventaProducto : ventaProductoList) {
                System.out.println("NO FACTURA; " + ventaProducto.getSerieVenta());
                System.out.println("ID PRODUCTO; " + ventaProducto.getIdProducto());
                System.out.println("NOMBRE PRO; " + ventaProducto.getNombreProducto());
                System.out.println("DESC PROD; " + ventaProducto.getDescripcionProducto());
                System.out.println("CANTIDAD; " + ventaProducto.getCantidad());
                System.out.println("TOTAL; " + ventaProducto.getPrecioVenta());
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los producto\n" + e);
        }
        Connect.closeConnection();
        return ventaProductoList;
    }

}
