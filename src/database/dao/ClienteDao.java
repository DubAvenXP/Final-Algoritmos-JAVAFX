package database.dao;

import database.Connect;
import database.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glasd
 * Esta clase es la encargada de hacer los CRUD de la tabla cliente en la base de datos
 */
public class ClienteDao {
    public static Integer idClient;

    public static void createClientDB(Cliente cliente) {
        PreparedStatement ps;
        idClient = autoIdClient(cliente.getIdCliente());

        try (Connection connection = Connect.getConnection()) {
            try {
                String sql = "INSERT INTO public.cliente(\"idCliente\", nit, nombre, apellido, direccion, telefono)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, idClient);
                ps.setString(2, cliente.getNit());
                ps.setString(3, cliente.getNombre());
                ps.setString(4, cliente.getApellido());
                ps.setString(5, cliente.getDireccion());
                ps.setString(6, cliente.getTelefono());
                ps.executeUpdate();
                System.out.println("Cliente creado");
            } catch (SQLDataException e) {
                System.out.println(e + "\nEl cliente no se pudo crear");

            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl cliente no se pudo crear");
        }
        Connect.closeConnection();
    }

    public static List<Cliente> viewClientDB() {
        PreparedStatement ps;
        ResultSet rs;
        List<Cliente> clienteArrayList = new ArrayList<>();

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT * FROM public.cliente";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setIdCliente(rs.getInt(1));
                client.setNit(rs.getString(2));
                client.setNombre(rs.getString(3));
                client.setApellido(rs.getString(4));
                client.setDireccion(rs.getString(5));
                client.setTelefono(rs.getString(6));
                clienteArrayList.add(client);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los clientes\n" + e);
        }
        Connect.closeConnection();
        return clienteArrayList;
    }

    public static String viewClientByNit(String nit) {
        PreparedStatement ps;
        ResultSet rs;
        String clientName = new String();
        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT nombre, apellido FROM public.cliente WHERE nit = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nit);
            rs = ps.executeQuery();

            while (rs.next()) {
                clientName = rs.getString(1) + " " + rs.getString(2);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el cliente\n" + e);
        }
        System.out.println(clientName);
        Connect.closeConnection();
        return clientName;
    }

    public static void deleteClientDB(String nit) {
        Connect connect = new Connect();
        PreparedStatement ps;

        try (Connection connection = connect.getConnection()) {
            String sql = "delete from public.cliente where nit = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nit);
            ps.executeUpdate();
            System.out.println("Cliente borrado");
        } catch (SQLException e) {
            System.out.println("No se pudo borrar el cliente\n" + e);
        }
        connect.closeConnection();
    }

    public static void updateClientDB(Cliente cliente) {
        Connect connect = new Connect();

        try (Connection connection = connect.getConnection()) {
            PreparedStatement ps;
            try {
                String sql = "update \"cliente\" set \"nombre\"=?, \"apellido\"=?, \"direccion\"=?, \"telefono\"=?, \"nit\"=?" +
                        "where \"idCliente\"=?;";
                ps = connection.prepareStatement(sql);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getDireccion());
                ps.setString(4, cliente.getTelefono());
                ps.setString(5, cliente.getNit());
                ps.setInt(6, cliente.getIdCliente());
                ps.executeUpdate();
                System.out.println("Cliente actualizado");
            } catch (SQLDataException e) {
                System.out.println(e + "\nEl cliente no se pudo acrualizar");

            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl cliente no se pudo actualizar");
        }
        connect.closeConnection();
    }

    public static Integer autoIdClient(Integer id) {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection connection = Connect.getConnection()) {
            String sql = "select max(\"idCliente\") from public.cliente";
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
