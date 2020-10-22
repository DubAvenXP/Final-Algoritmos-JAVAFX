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
     * @param ventaProducto objeto de tipo VentaProducto que recibe los parametros de la factura para ser insertados
     *                      en la base de datos
     */
    public static void saveBillDB(VentaProducto ventaProducto){
        try (Connection connection = Connect.getConnection()){
            PreparedStatement ps;
            idProductSale = autoIdSaleProduct();
            String sql = "INSERT INTO public.\"ventaProducto\"(\"idVentaProducto\", \"serieVenta\", \"idProducto\", " +
                    "cantidad, \"precioVenta\") VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProductSale);
            ps.setString(2, VentaDao.viewSerialSale(ventaProducto.getIdProducto()));
            ps.setInt(3, ventaProducto.getIdProducto());
            ps.setInt(4, ventaProducto.getCantidad());
            ps.setDouble(5, ventaProducto.getPrecioVenta());
            ps.executeUpdate();
            System.out.println("Factura guardada con exito");
        } catch (SQLException e) {
            System.out.println("No se pudo guardar la factura" + e);
        }
    }

    /**
     * Metodo para hace el id del producto auto-incrementable al momento de crear un nuevo producto
     * @return retorna un Integer que es el que se le asignara al nuevo producto al momento de crearse
     */
    public static Integer autoIdSaleProduct(){
        PreparedStatement ps;
        ResultSet rs;
        Integer id = null;
        try (Connection connection = Connect.getConnection()) {
            String sql = "select max(\"idVentaProducto\") from public.\"ventaProducto\"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            id = rs.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    /**
     * Metodo para ver toda la informacion de la facturacion
     * @return retorna un List con la informacion de la factura guardada
     */
    public static List<VentaProducto> viewBillDB(){
        PreparedStatement ps;
        ResultSet rs;
        List<VentaProducto> listVentaProducto = new ArrayList<>();
        try (Connection connection = Connect.getConnection()){
            String viewBill = "SELECT * FROM public.\"ventaProducto\"";
            ps = connection.prepareStatement(viewBill);
            rs = ps.executeQuery();
            while (rs.next()){
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
        return listVentaProducto;
    }

}
