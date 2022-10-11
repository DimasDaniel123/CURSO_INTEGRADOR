
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class CategoriaDao { 
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarCategoria(Categoria cat){
        String sql="INSERT INTO categorias(categoria)VALUES(?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,cat.getNombre());           
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }
    }

    public List ListarCategoria(String valor){
        List<Categoria> ListaCat=new ArrayList();
        String sql="SELECT *FROM categorias";
        String buscar = "SELECT *FROM categorias WHERE categoria LIKE '%"+valor+"%'";
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
                Categoria cat=new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("categoria"));
                ListaCat.add(cat);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaCat;
    }
    public boolean ModificarCategoria(Categoria cat){
        String sql="UPDATE categorias SET categoria=? WHERE id=?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,cat.getNombre());
            ps.setInt(2,cat.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean EliminarCategoria(int id){
        String sql="DELETE FROM categorias WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;    } }}
