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
                System.out.println(e);;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void viewClientDB(){
        PreparedStatement ps;
        ResultSet rs = null;
        try(Connection connection = Connect.getConnection()){
                String sql = "SELECT \"idCliente\", nombre, apellido, direccion, telefono, nit FROM public.cliente";
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
            throw new RuntimeException(e);
        }
    }

    public static void deleteClientDB(Integer idCliente){

    }

    public static void updateClientDB(Cliente cliente){

    }

}
