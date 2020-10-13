package database.dao;

import database.Connect;
import database.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Esta clase es la que nos permite hacer las comunicaciones con la db
public class ClienteDao {

    public static void createClientDB(Cliente cliente) {
        PreparedStatement ps;

        try (Connection connection = Connect.getConnection()) {
            try {
                String sql = "INSERT INTO public.cliente(\"idCliente\", nit, nombre, apellido, direccion, telefono)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, autoIdClient(cliente.getIdCliente()));
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

    public static Cliente viewClientDB() {
        Cliente client = new Cliente();
        PreparedStatement ps;
        ResultSet rs;

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT * FROM public.cliente";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                client.setIdCliente(rs.getInt(1));
                client.setNit(rs.getString(2));
                client.setNombre(rs.getString(3));
                client.setApellido(rs.getString(4));
                client.setDireccion(rs.getString(5));
                client.setTelefono(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los clientes\n" + e);
        }
        Connect.closeConnection();
        return client;
    }

    public static Cliente viewClientById(String nit) {
        Cliente client = new Cliente();
        PreparedStatement ps;
        ResultSet rs;

        try (Connection connection = Connect.getConnection()) {
            String sql = "SELECT \"idCliente\", nit, nombre, apellido, direccion, telefono\n" +
                    "\tFROM public.cliente where nit = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nit);
            rs = ps.executeQuery();

            while (rs.next()) {
                client.setIdCliente(rs.getInt(1));
                client.setNit(rs.getString(2));
                client.setNombre(rs.getString(3));
                client.setApellido(rs.getString(4));
                client.setDireccion(rs.getString(5));
                client.setTelefono(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el cliente\n" + e);
        }
        Connect.closeConnection();
        return client;
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
