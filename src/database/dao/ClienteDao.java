package database.dao;

import database.Connect;
import database.models.Cliente;

import java.sql.*;

//Esta clase es la que nos permite hacer las comunicaciones con la db
public class ClienteDao {

    public static void createClientDB(Cliente cliente){
        try(Connection connection = Connect.getConnection()){
            PreparedStatement ps;
            try {
                String sql = "INSERT INTO public.cliente(\"idCliente\", nombre, apellido, direccion, telefono, nit)" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, cliente.getIdCliente());
                ps.setString(2, cliente.getNit());
                ps.setString(3, cliente.getNombre());
                ps.setString(4, cliente.getApellido());
                ps.setString(5, cliente.getDireccion());
                ps.setString(6, cliente.getTelefono());
                ps.executeUpdate();
                System.out.println("Cliente creado");
            }catch (SQLDataException e){
                System.out.println(e + "\nEl cliente no se pudo crear");

            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl cliente no se pudo crear");
        }
    }

    public static void viewClientDB(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try(Connection connection = Connect.getConnection()){
                String sql = "SELECT * FROM public.cliente";
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();

                while(rs.next()){
                    System.out.println("ID: " + rs.getInt("idCliente"));
                    System.out.println("NOMBRE: " + rs.getString("nombre"));
                    System.out.println("APELLIDO: " + rs.getString("apellido"));
                    System.out.println("DIRECCION: " + rs.getString("direccion"));
                    System.out.println("TELEFONO: " + rs.getString("telefono"));
                    System.out.println("NIT: " + rs.getString("nit"));
                    System.out.println("");
                }
        } catch (SQLException e) {
            System.out.println("No se pudieron traer los mensajes\n" + e);
        }
    }

    public static void deleteClientDB(int idCliente){
        PreparedStatement ps = null;
        try (Connection connection = Connect.getConnection()){
            String sql = "delete from \"cliente\" where \"cliente\".\"idCliente\" = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            System.out.println("El cliente ha sido borrado");
        }catch (SQLException e){
            System.out.println("No se pudo borrar el cliente\n" + e);

        }
    }

    public static void updateClientDB(Cliente cliente){
        try(Connection connection = Connect.getConnection()){
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
            }catch (SQLDataException e){
                System.out.println(e + "\nEl cliente no se pudo acrualizar");

            }
        } catch (SQLException e) {
            System.out.println(e + "\nEl cliente no se pudo actualizar");
        }
    }
}
