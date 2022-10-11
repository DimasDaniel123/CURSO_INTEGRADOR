 
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginDAO {
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Login log(String correo, String pass){
        String sql="SELECT * FROM usuarios WHERE correo=?AND pass=?";
        Login l = new Login();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,correo);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setRol(rs.getString("rol"));
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return l;
    }
    
    
    public boolean registrar(Login log){
       String sql = "INSERT INTO usuarios (nombre, correo, pass, rol) VALUES (?,?,?,?)";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, log.getNombre());
           ps.setString(2, log.getCorreo());
           ps.setString(3, log.getPass());
           ps.setString(4, log.getRol());
           ps.execute();
           return true;
       } catch (Exception e){
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
    }
    
    public List ListaUsuarios(){
        List<Login> listaUsers = new ArrayList();
        String sql = "SELECT * FROM usuarios";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Login log = new Login();
                log.setId(rs.getInt("id"));
                log.setNombre(rs.getString("nombre"));
                log.setCorreo(rs.getString("correo"));
                log.setRol(rs.getString("rol"));
                listaUsers.add(log);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaUsers;
    }
    
    public boolean Modificar(Login log){
       String sql = "UPDATE usuarios SET nombre = ?, correo = ?, rol = ? WHERE id = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, log.getNombre());
           ps.setString(2, log.getCorreo());
           ps.setString(3, log.getRol());
           ps.setInt(4, log.getId());
           ps.execute();
           return true;
       } catch (Exception e){
           JOptionPane.showMessageDialog(null, e.toString());
           return false;
       }
    }
    
    public boolean EliminarUsuario(int id){
        String sql = "DELETE FROM usuarios WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public Configuracion getConfig() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    }
    
