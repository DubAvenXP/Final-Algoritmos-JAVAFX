package database.dao;

import database.Connect;
import database.models.Cliente;
import database.models.Producto;
import database.models.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla venta en la base de datos
 */
public class VentaDao {

    public static Integer idSale;

    /**
     * Metodo encargado de generar la venta
     *
     * @param venta es el objeto de tipo Venta que recibe como parametro para generar una nueva venta e insertarla
     *              en la tabla venta de la base de datos
     */
    public static void createSaleDB(Venta venta) {
        idSale = autoIdSale(venta.getIdVenta());
        PreparedStatement ps;
        try (Connection connection = Connect.getConnection()) {
            String sql = "INSERT INTO public.venta(\"idVenta\", \"nombreVendedor\", \"nitCliente\", \"serieVenta\"," +
                    "monto, \"metodoPago\", \"fechaVenta\") VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSale);
            ps.setString(2, venta.getUserVendedor());
            ps.setString(3, venta.getNitCliente());
            ps.setString(4, venta.getSerieVenta());
            ps.setDouble(5, venta.getMonto());
            ps.setString(6, venta.getMetodoPago());
            ps.setString(7, venta.generateDate());
            ps.executeUpdate();
            System.out.println("Venta generada con exito");
        } catch (SQLException e) {
            System.out.println("No se pudo generar la venta." + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo que hace auto-incrementable el id al generar una nueva venta en la base de datos
     *
     * @param id es el parametro que recibe para hacer el query a la base de datos para traer el precio del productoperteneciente al id
     * @return devuelve un Integer que es el que se le asigna al id de la tabla al generarse una nueva venta
     */
    public static Integer autoIdSale(Integer id) {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()) {
            String sql = "select max(\"idVenta\") from public.venta";
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
     * Metodo para precio asignado de la tabla producto en la base de datos
     *
     * @param id es el parametro que recibe para hacer el query a la base de datos para traer el precio del producto\
     *           perteneciente al id
     * @return devuelve un Double con el precio que esta asignado al producto al que pertenece el id que se pasa como
     * parametro
     */
    public static Double viewPriceProduct(Integer id) {
        Double price = 0.0;
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps;
            ResultSet rs = null;
            String priceDB = "SELECT precio FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(priceDB);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el precio del producto" + e);
        }
        Connect.closeConnection();
        return price;
    }

    /**
     * Metodo para traer el stock disponible de la tabla producto en la base de datos
     *
     * @param id es el parametro que recibe para hacer el query a la base de datos para traer el precio del producto
     *           perteneciente al id
     * @return devuelve un Integer con el stock disponible en la base de datos
     */
    public static Integer availableProductDB(Integer id) {
        Integer stock = 0;
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps;
            ResultSet rs = null;
            String stockAvailable = "SELECT stock FROM public.producto WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(stockAvailable);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("El stock no se pudo traer." + e);
        }
        Connect.closeConnection();
        return stock;
    }

    /**
     * Metodo para actualizar el stock en la base de datos
     *
     * @param stock es el parametro que se le pasa al query para actualizar el stock en la base de datos,
     *              tiene que ser de tipo Integer
     * @param id    es el parametro que recibe para hacer el query a la base de datos para traer el precio del producto
     *              perteneciente al id
     */
    public static void updateStockDB(Integer stock, Integer id) {
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps;
            String updateStock = "UPDATE public.producto SET stock=? WHERE \"idProducto\" = ?";
            ps = connection.prepareStatement(updateStock);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Stock actualizdo");
        } catch (SQLException e) {
            System.out.println("El stock no se actualizo\n" + e);
        }
        Connect.closeConnection();
    }

    /**
     * Metodo para ver las cuentas por cobrar de los clientes
     *
     * @return retorna un list con todos los datos del cliente
     */
    public static List<Venta> balanceDB() {
        List<Venta> ventaList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()) {
            String getBalance = "SELECT \"serieVenta\", \"nombreCliente\", \"metodoPago\", monto, \"fechaVenta\" FROM public.venta";
            ps = connection.prepareStatement(getBalance);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setSerieVenta(rs.getString(1));
                venta.setNitCliente(rs.getString(2));
                venta.setMetodoPago(rs.getString(3));
                venta.setMonto(rs.getDouble(4));
                venta.setFechaVenta(rs.getString(5));
                if (rs.getDouble(4) != 0)
                    ventaList.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los datos" + e);
        }
        Connect.closeConnection();
        return ventaList;
    }

    /**
     * Metodo que genera el numero de serie para la factura
     * @return retorna un string con el numero de serie de la factura
     */
    public static String generateBillNumber(){
        String billNumber = "";
        try (Connection connection = Connect.getConnection()){
            String sql = "SELECT MAX(\"serieVenta\") FROM public.venta";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                billNumber = rs.getString(1);
            }
            if (billNumber == null){
                billNumber = "1";
            }else{
                Integer max = Integer.parseInt(billNumber);
                max++;
                billNumber = String.valueOf(max);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo generar el numero de serie");
        }
        Connect.closeConnection();
        return billNumber;
    }

    /**
     * Metoto para ver todas las facturas pertenecientes a un cliente
     * @param nit perteneciente al cliente al que se quiere buscar
     * @return retorna un List con los datos de las facturas del cliente
     */
    public static List<Venta> viewBillClient(String nit){
        PreparedStatement ps;
        ResultSet rs;
        List<Venta> clienteList = new ArrayList<>();
        try(Connection connection = Connect.getConnection()){
            String sql = "select \"serieVenta\",\"nitCliente\",monto,\"metodoPago\",\"fechaVenta\", (select(nombre) from cliente \n" +
                    "where cliente.nit = venta.\"nitCliente\") as \"nombre\" from public.venta where \"nitCliente\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nit);
            rs = ps.executeQuery();

            while (rs.next()){
                Venta venta = new Venta();
                venta.setSerieVenta(rs.getString(1));
                venta.setNitCliente(rs.getString(2));
                venta.setMonto(rs.getDouble(3));
                venta.setMetodoPago(rs.getString(4));
                venta.setFechaVenta(rs.getString(5));
                venta.setNombreCliente(rs.getString(6));
                clienteList.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer la factura\n" + e);
        }
        for (Venta venta : clienteList) {
            System.out.println("No Factura:" + venta.getSerieVenta());
            System.out.println("Nit: " + venta.getNitCliente());
            System.out.println("NOMBRE:" + venta.getNombreCliente());
            System.out.println("Monto:" + venta.getMonto());
            System.out.println("Forma pago:" + venta.getMetodoPago());
            System.out.println("Fecha:" + venta.getFechaVenta());
            System.out.println();
        }
        Connect.closeConnection();
        return clienteList;
    }

    public static List<Venta> viewAllBill(){
        PreparedStatement ps;
        ResultSet rs;
        List<Venta> ventaList = new ArrayList<>();
        try (Connection connection = Connect.getConnection()){
            String sql = "select * from public.venta";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt(1));
                venta.setUserVendedor(rs.getString(2));
                venta.setNitCliente(rs.getString(3));
                venta.setSerieVenta(rs.getString(4));
                venta.setMonto(rs.getDouble(5));
                venta.setMetodoPago(rs.getString(6));
                venta.setFechaVenta(rs.getString(7));
                ventaList.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer la facturas\n" + e);
        }
        for (Venta venta : ventaList) {
            System.out.println(venta.getIdVenta());
            System.out.println(venta.getUserVendedor());
            System.out.println(venta.getNitCliente());
            System.out.println(venta.getSerieVenta());
            System.out.println(venta.getMonto());
            System.out.println(venta.getMetodoPago());
            System.out.println(venta.getFechaVenta());
            System.out.println();
        }
        Connect.closeConnection();
        return ventaList;
    }

}
