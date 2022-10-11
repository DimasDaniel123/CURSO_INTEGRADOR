
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ComprasDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List ListaCompras(String valor){
        List<Compras> lista = new ArrayList(); 
        String sql = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id ORDER BY c.id DESC";
        String buscar = "SELECT c.*, p.proveedor FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id WHERE p.proveedor LIKE '%"+valor+"%' OR  c.fecha LIKE '%"+valor+"%'";
        try {
            con = cn.getConnection();  
            if(valor.equalsIgnoreCase("")){
                ps=con.prepareStatement(sql);
            }else{
                ps =con.prepareStatement(buscar);
            }        
           rs=ps.executeQuery();
           while (rs.next()) {               
               Compras comp = new Compras();
               comp.setId(rs.getInt("id"));
               comp.setId_proveedor(rs.getInt("id_proveedor"));
               comp.setTotal(rs.getDouble("total"));
               comp.setFecha(rs.getString("fecha"));
               comp.setNom_proveedor(rs.getString("proveedor"));
               lista.add(comp);
           }
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
       }
       return lista;
   }
     
}
