
package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ProductoDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Producto pro){
        String sql = "INSERT INTO productos (codigo, nombre, id_proveedor, precio, precio_venta, id_categoria) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getId_proveedor());
            ps.setDouble(4, pro.getPrecio());
            ps.setDouble(5, pro.getPrecioV());
            ps.setInt(6, pro.getId_categoria());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarProductos(String valor){
        List<Producto> listaPro = new ArrayList(); 
        String sql = "SELECT * FROM productos";
        String buscar = "SELECT * FROM productos WHERE codigo LIKE '%"+valor+"%' OR  nombre LIKE '%"+valor+"%'";
        try {
            con=cn.getConnection();  
            if(valor.equalsIgnoreCase("")){
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
            }else{
                ps =con.prepareStatement(buscar);
                rs =ps.executeQuery();
            }          
           while (rs.next()) {               
               Producto pro = new Producto();
               pro.setId(rs.getInt("id"));
               pro.setCodigo(rs.getString("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setId_proveedor(rs.getInt("id_proveedor"));
               pro.setStock(rs.getInt("stock"));
               pro.setPrecio(rs.getDouble("precio"));
               pro.setPrecioV(rs.getDouble("precio_venta"));
               pro.setId_categoria(rs.getInt("id_categoria"));
               listaPro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return listaPro;
   }
    

    
    public boolean ModificarProductos(Producto pro){
       String sql = "UPDATE productos SET codigo=?, nombre=?, id_proveedor=?, precio=?, precio_venta=?, id_categoria=? WHERE id=?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getCodigo());
           ps.setString(2, pro.getNombre());
           ps.setInt(3, pro.getId_proveedor());
           ps.setDouble(4, pro.getPrecio());
           ps.setDouble(5, pro.getPrecioV());
           ps.setInt(6, pro.getId_categoria());
           ps.setInt(7, pro.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }  
    
    
    public boolean EliminarProducto(int id){
        String sql = "DELETE FROM productos WHERE id = ? ";
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
    
    public Producto buscarPro(int id){
        String sql = "SELECT p.*, pv.id, pv.proveedor, c.id, c.categoria FROM productos p INNER JOIN proveedor pv ON p.id_proveedor = pv.id INNER JOIN categorias c ON p.id_categoria = c.id WHERE p.id = ?";
        Producto pro = new Producto();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setPrecioV(rs.getDouble("precio_venta"));
                pro.setId_proveedor(rs.getInt("id_proveedor"));
                pro.setId_categoria(rs.getInt("id_categoria"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setCat(rs.getString("categoria"));    
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return pro;
    }
    
    
    public Producto buscarCodigo(String codigo){
        String sql = "SELECT  * FROM productos WHERE codigo  = ?";
        Producto pro = new Producto();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()){
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setPrecioV(rs.getDouble("precio_venta"));
                pro.setStock(rs.getInt("stock"));
                
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return pro;
    }
    
    public Producto buscarId(int id){
        String sql = "SELECT  * FROM productos WHERE id  = ?";
        Producto pro = new Producto();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                pro.setStock(rs.getInt("stock"));
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return pro;
    }
    
    public boolean registrarCompra(int id, String total){
        String sql = "INSERT INTO compras (id_proveedor, total) VALUES (?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.execute(); 
            return true;
        }  catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    public boolean registrarCompraDetalle(int id_compra, int id, double precio, int cant, double sub_total){
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, precio, cantidad, subtotal) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setInt(1, id_compra);
            ps.setInt(2, id);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute(); 
            return true;
        }  catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean ActualizarStock(int cant, int id){
       String sql = "UPDATE productos SET stock=? WHERE id=?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, cant);
           ps.setInt(2, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }
    
    
    public int getUltimoId(String tabla){
        int id = 0;
        String sql = "SELECT MAX(id) AS id FROM " + tabla;
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
         return id;
    }
    
    public List ListaDetalle(int id_compra){
        List<Producto> listaPro = new ArrayList(); 
        String sql = "SELECT c.*, d.*, p.id, p.nombre FROM compras c INNER JOIN detalle_compra d ON d.id_compra = c.id INNER JOIN productos p ON p.id = d.id_producto WHERE c.id = ?";
        
        try {
            con=cn.getConnection();  
            ps=con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs=ps.executeQuery();                     
           while (rs.next()) {               
               Producto pro = new Producto();
               pro.setStock(rs.getInt("cantidad"));
               pro.setNombre(rs.getString("nombre"));
               pro.setPrecio(rs.getDouble("precio"));
               pro.setPrecioV(rs.getDouble("subtotal"));
               listaPro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return listaPro;
   }
    //REPORTE COMPRAS
    public void generarReporte(int id_compra){
        double totalGeneral = 0.00;
        String fecha = "";
        String nomProveedor = "";
        String telProveedor = "";
        String dirProveedor = "";
        String razProveedor  = "";
        
        try{
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "compra.pdf");
            archivo = new FileOutputStream(salida); 
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            //Datos de la Empresa
            PdfPTable empresa = new PdfPTable(4);
            empresa.setWidthPercentage(100);
            float[] tamanioEncabezado = new float[]{15f, 15f, 40f, 30f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            Image img = Image.getInstance(getClass().getResource("/imagenes/logo01.png"));
            empresa.addCell(img);
            empresa.addCell("");
            String sql="SELECT * FROM config";

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
                if(rs.next()){
                    empresa.addCell("Ruc: "+ rs.getString("ruc") + "\nNombre: " + rs.getString("nombre")
                    + "\nTeléfono: "+ rs.getString("telefono") + "\nDirección: " + rs.getString("direccion"));

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            String consultaProveedor = "SELECT p.proveedor, p.telefono, p.direccion, p.Razon, c.total, c.fecha FROM compras c INNER JOIN proveedor p ON c.id_proveedor = p.id WHERE c.id =" + id_compra;

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(consultaProveedor);
                rs=ps.executeQuery();
                if(rs.next()){ 
                    nomProveedor = rs.getString("proveedor");
                    telProveedor = rs.getString("telefono");
                    dirProveedor = rs.getString("direccion");
                    razProveedor = rs.getString("razon");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            //Comprador
            empresa.addCell("N° Compra: "+ id_compra + "\nComprador: " + "Libreria Bazar"
                    + "\nFecha: " + fecha);
            
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            //Fin Empresa
            
            
            //TITULO PROVEEDOR
            Paragraph tituloProveedor = new Paragraph();
            tituloProveedor.add("DATOS DEL PROVEEDOR");
            tituloProveedor.setAlignment(Element.ALIGN_CENTER);
            doc.add(tituloProveedor);
            doc.add(Chunk.NEWLINE);
            //Datos del Proveedor
            
            PdfPTable proveedor = new PdfPTable(4);
            proveedor.setWidthPercentage(100);
            float[] tamanioProveedor = new float[]{30f, 20f, 30f, 20f};
            proveedor.setWidths(tamanioProveedor);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT); 
            proveedor.getDefaultCell().setBorder(0);
            
            PdfPCell nomPr = new PdfPCell(new  Phrase("Nombre", negrita));
            PdfPCell telPr = new PdfPCell(new  Phrase("Telefono", negrita));
            PdfPCell dirPr = new PdfPCell(new  Phrase("Direccion", negrita));
            PdfPCell razPr = new PdfPCell(new  Phrase("Razon", negrita));
            
            nomPr.setBorder(0);
            telPr.setBorder(0);
            dirPr.setBorder(0);
            razPr.setBorder(0);
            
            nomPr.setBackgroundColor(BaseColor.BLUE);
            telPr.setBackgroundColor(BaseColor.BLUE);
            dirPr.setBackgroundColor(BaseColor.BLUE);
            razPr.setBackgroundColor(BaseColor.BLUE);
            
            proveedor.addCell(nomPr);
            proveedor.addCell(telPr);
            proveedor.addCell(dirPr);
            proveedor.addCell(razPr);
            
            
            proveedor.addCell(nomProveedor);
            proveedor.addCell(telProveedor);
            proveedor.addCell(dirProveedor);
            proveedor.addCell(razProveedor);
            
            

            doc.add(proveedor);
            doc.add(Chunk.NEWLINE);
             
            //Fin Proveedor
            
            //TITULO PRODUCTOS
            Paragraph tituloProducto = new Paragraph();
            tituloProducto.add("DETALLES DE LA COMPRA");
            tituloProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(tituloProducto);
            doc.add(Chunk.NEWLINE);
            //Datos del Producto
            
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float[] tamanioProducto = new float[]{50f, 10f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT); 
            producto.getDefaultCell().setBorder(0);
            
            PdfPCell desPro = new PdfPCell(new  Phrase("Descripcion", negrita));
            PdfPCell cantPro = new PdfPCell(new  Phrase("Cant", negrita));
            PdfPCell precPro = new PdfPCell(new  Phrase("Precio", negrita));
            PdfPCell subPro = new PdfPCell(new  Phrase("Subtotal", negrita));
            
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precPro.setBorder(0);
            subPro.setBorder(0);
            
            desPro.setBackgroundColor(BaseColor.BLUE);
            cantPro.setBackgroundColor(BaseColor.BLUE);
            precPro.setBackgroundColor(BaseColor.BLUE);
            subPro.setBackgroundColor(BaseColor.BLUE);
            
            producto.addCell(desPro);
            producto.addCell(cantPro);
            producto.addCell(precPro);
            producto.addCell(subPro);
            
            String consultaProducto = "SELECT d.precio, d.cantidad, d.subtotal, p.nombre FROM compras c INNER JOIN detalle_compra d ON c.id = d.id_compra INNER JOIN productos p ON d.id_producto = p.id WHERE c.id =  " + id_compra;

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(consultaProducto);
                rs=ps.executeQuery();
                while(rs.next()){
                    producto.addCell(rs.getString("nombre"));
                    producto.addCell(rs.getString("cantidad"));
                    producto.addCell(rs.getString("precio"));
                    producto.addCell(rs.getString("subtotal"));

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            
            //Fin productos
            
            //Total a pagar
            Paragraph totalP = new Paragraph();
            totalP.add("Total a pagar: "+ totalGeneral);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalP);
            doc.add(Chunk.NEWLINE);
            
            
            
            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(salida);
        } catch(DocumentException | IOException e){
            
        }

    }   
    
    //ventas
    public boolean registrarVenta(int id, String total){
        String sql = "INSERT INTO ventas (id_cliente, total) VALUES (?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.execute(); 
            return true;
        }  catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    public boolean registrarVentaDetalle(int id_venta, int id_producto, double precio, int cant, double sub_total){
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, precio, cantidad, subtotal) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setInt(1, id_venta);
            ps.setInt(2, id_producto);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute(); 
            return true;
        }  catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    //REPORTE VENTAS
    
    public void generarReporteVenta(int id_venta){
        double totalGeneral = 0.00;
        String fecha = "";
        String nomCliente  = "";
        String telCliente = "";
        String dniCliente = "";
        String dirCliente  = "";
        
        try{
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "venta.pdf");
            archivo = new FileOutputStream(salida); 
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            //Datos de la Empresa
            PdfPTable empresa = new PdfPTable(4);
            empresa.setWidthPercentage(100);
            float[] tamanioEncabezado = new float[]{15f, 15f, 40f, 30f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            Image img = Image.getInstance(getClass().getResource("/imagenes/logo01.png"));
            empresa.addCell(img);
            empresa.addCell("");
            String sql="SELECT * FROM config";

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
                if(rs.next()){
                    empresa.addCell("Ruc: "+ rs.getString("ruc") + "\nNombre: " + rs.getString("nombre")
                    + "\nTeléfono: "+ rs.getString("telefono") + "\nDirección: " + rs.getString("direccion"));

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            String consultaCliente = "SELECT c.nombre, c.telefono, c.dni, c.direccion, v.total, v.fecha FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id WHERE v.id =" + id_venta;

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(consultaCliente);
                rs=ps.executeQuery();
                if(rs.next()){ 
                    nomCliente = rs.getString("nombre");
                    telCliente = rs.getString("telefono");
                    dniCliente = rs.getString("dni");
                    dirCliente = rs.getString("direccion");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            //Vendedor
            empresa.addCell("N° Venta: "+ id_venta + "\nVendedor: " + "Libreria Bazar"
                    + "\nFecha: " + fecha);
            
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            //Fin Empresa
            
            
            //TITULO CLIENTE
            Paragraph tituloCliente = new Paragraph();
            tituloCliente.add("DATOS DEL CLIENTE");
            tituloCliente.setAlignment(Element.ALIGN_CENTER);
            doc.add(tituloCliente);
            doc.add(Chunk.NEWLINE);
            //Datos del Cliente
            
            PdfPTable cliente = new PdfPTable(4);
            cliente.setWidthPercentage(100);
            float[] tamanioProveedor = new float[]{30f, 20f, 30f, 20f};
            cliente.setWidths(tamanioProveedor);
            cliente.setHorizontalAlignment(Element.ALIGN_LEFT); 
            cliente.getDefaultCell().setBorder(0);
            
            PdfPCell nomCli = new PdfPCell(new  Phrase("Nombre", negrita));
            PdfPCell telCli = new PdfPCell(new  Phrase("Telefono", negrita));
            PdfPCell dniCli = new PdfPCell(new  Phrase("Dni", negrita));
            PdfPCell dirCli = new PdfPCell(new  Phrase("Direccion", negrita));
            
            nomCli.setBorder(0);
            telCli.setBorder(0);
            dniCli.setBorder(0);
            dirCli.setBorder(0);
            
            nomCli.setBackgroundColor(BaseColor.BLUE);
            telCli.setBackgroundColor(BaseColor.BLUE);
            dniCli.setBackgroundColor(BaseColor.BLUE);
            dirCli.setBackgroundColor(BaseColor.BLUE);
            
            cliente.addCell(nomCli);
            cliente.addCell(telCli);
            cliente.addCell(dniCli);
            cliente.addCell(dirCli);
            
            
            cliente.addCell(nomCliente);
            cliente.addCell(telCliente);
            cliente.addCell(dniCliente);
            cliente.addCell(dirCliente);
            
            

            doc.add(cliente);
            doc.add(Chunk.NEWLINE);
             
            //Fin Proveedor
            
            //TITULO PRODUCTOS
            Paragraph tituloProducto = new Paragraph();
            tituloProducto.add("DETALLES DE LA VENTA");
            tituloProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(tituloProducto);
            doc.add(Chunk.NEWLINE);
            //Datos del Producto
            
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float[] tamanioProducto = new float[]{50f, 10f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT); 
            producto.getDefaultCell().setBorder(0);
            
            PdfPCell desPro = new PdfPCell(new  Phrase("Descripcion", negrita));
            PdfPCell cantPro = new PdfPCell(new  Phrase("Cant", negrita));
            PdfPCell precPro = new PdfPCell(new  Phrase("Precio", negrita));
            PdfPCell subPro = new PdfPCell(new  Phrase("Subtotal", negrita));
            
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precPro.setBorder(0);
            subPro.setBorder(0);
            
            desPro.setBackgroundColor(BaseColor.BLUE);
            cantPro.setBackgroundColor(BaseColor.BLUE);
            precPro.setBackgroundColor(BaseColor.BLUE);
            subPro.setBackgroundColor(BaseColor.BLUE);
            
            producto.addCell(desPro);
            producto.addCell(cantPro);
            producto.addCell(precPro);
            producto.addCell(subPro);
            
            String consultaProducto = "SELECT d.precio, d.cantidad, d.subtotal, p.nombre FROM ventas v INNER JOIN detalle_venta d ON v.id = d.id_venta INNER JOIN productos p ON d.id_producto = p.id WHERE v.id =  " + id_venta;

            try {
                con=cn.getConnection();
                ps=con.prepareStatement(consultaProducto);
                rs=ps.executeQuery();
                while(rs.next()){
                    producto.addCell(rs.getString("nombre"));
                    producto.addCell(rs.getString("cantidad"));
                    producto.addCell(rs.getString("precio"));
                    producto.addCell(rs.getString("subtotal"));

                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            
            //Fin productos
            
            //Total a pagar
            Paragraph totalP = new Paragraph();
            totalP.add("Total a pagar: "+ totalGeneral);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalP);
            doc.add(Chunk.NEWLINE);
            
            
            
            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(salida);
        } catch(DocumentException | IOException e){
            
        }

    }   
    
}
