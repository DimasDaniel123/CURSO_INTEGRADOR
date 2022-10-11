
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarCliente(Cliente cl){
        String sql="INSERT INTO clientes(nombre,dni,direccion,telefono,razon)VALUES(?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,cl.getNombre());
            ps.setInt(2,cl.getDni());            
            ps.setString(3,cl.getDireccion());
            ps.setInt(4,cl.getTelefono());
            ps.setString(5,cl.getRazon());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }
    }
    
    public List ListarCliente(String valor){ 
        List<Cliente> ListaCl=new ArrayList();
        String sql="SELECT *FROM clientes";
        String buscar = "SELECT *FROM clientes WHERE nombre LIKE '%"+valor+"%' OR  telefono LIKE '%"+valor+"%'";
        try{
            con=cn.getConnection();  
            if(valor.equalsIgnoreCase("")){
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
            }else{
                ps =con.prepareStatement(buscar);
                rs =ps.executeQuery();
            }
            while (rs.next()){
                Cliente cl=new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setNombre(rs.getString("nombre"));
                cl.setDni(rs.getInt("dni"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setRazon(rs.getString("razon"));
                ListaCl.add(cl);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaCl;
    }
    
    public boolean EliminarCliente(int id){
        String sql="DELETE FROM clientes WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean ModificarCliente(Cliente cl){
        String sql="UPDATE clientes SET nombre=?,dni=?,direccion=?,telefono=?,razon=? WHERE id=?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,cl.getNombre());
            ps.setInt(2,cl.getDni());
            ps.setString(3,cl.getDireccion());
            ps.setInt(4,cl.getTelefono());
            ps.setString(5,cl.getRazon());
            ps.setInt(6,cl.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    

} 

