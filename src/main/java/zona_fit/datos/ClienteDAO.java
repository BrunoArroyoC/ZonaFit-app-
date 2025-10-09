package zona_fit.datos;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresias"));
                clientes.add(cliente);
            }

        }catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage() );
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar " + e.getMessage() );
            }

        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresias"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerar "+ e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (nombre,apellido,membresias) VALUES (?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());

            int filas = ps.executeUpdate();
            if(filas > 0){
                return true;
            }

        }catch (Exception e){
            System.out.println("Error al agregar " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }


        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();

        var sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresias = ? WHERE id = ?";

        try{
            ps= con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.setInt(4,cliente.getId());

            int filas = ps.executeUpdate();
            if(filas > 0){
                return true;
            }

        }catch (Exception e){
            System.out.println("Error a UPDATE " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }


        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();

        var sql = "DELETE FROM cliente WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        }catch (Exception e){
            System.out.println("Error al eliminar " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }


        return false;
    }
}
