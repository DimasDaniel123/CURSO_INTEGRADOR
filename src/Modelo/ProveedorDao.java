
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pro){
        String sql="INSERT INTO proveedor(proveedor,ruc,direccion,telefono,razon)VALUES(?,?,?,?,?)";
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getNombre());
           ps.setString(2, pro.getRuc());
           ps.setString(3, pro.getDireccion());
           ps.setString(4, pro.getTelefono());
           ps.setString(5, pro.getRazon());
           ps.execute();
           return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarProveedor(String valor){
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        String buscar = "SELECT *FROM proveedor WHERE proveedor LIKE '%"+valor+"%' OR  telefono LIKE '%"+valor+"%'";
        try {
            con = cn.getConnection();
            if(valor.equalsIgnoreCase("")){
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
            }else{
                ps =con.prepareStatement(buscar);
                rs =ps.executeQuery();
            }
            while (rs.next()) {                
                Proveedor pro = new Proveedor();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("proveedor"));
                pro.setRuc(rs.getString("ruc"));
                pro.setDireccion(rs.getString("direccion"));
                pro.setTelefono(rs.getString("telefono"));
                pro.setRazon(rs.getString("razon"));
                Listapr.add(pro);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id = ? ";
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
    
    public boolean ModificarProveedor(Proveedor pro){
        String sql = "UPDATE proveedor SET proveedor=?, ruc=?, direccion=?, telefono=?, razon=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getRuc());
            ps.setString(3, pro.getDireccion());
            ps.setString(4, pro.getTelefono());
            ps.setString(5, pro.getRazon());
            ps.setInt(6, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public Proveedor getDatos(int id_compra){
        String sql="SELECT p.*, c.id, c.id_proveedor FROM proveedor p INNER JOIN compras c ON p.id = c.id_proveedor WHERE c.id = ?";
        Proveedor pr = new Proveedor();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs=ps.executeQuery();
            if(rs.next()){
                pr.setNombre(rs.getString("proveedor"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setTelefono(rs.getString("telefono"));
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return pr;
    }
}
