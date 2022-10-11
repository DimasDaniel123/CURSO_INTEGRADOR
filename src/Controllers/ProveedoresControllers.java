
package Controllers;

import Modelo.Combo;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Vista.SistemaInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



public class ProveedoresControllers implements ActionListener, MouseListener, KeyListener{
    private Proveedor pro;
    private ProveedorDao proDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();
    

    public ProveedoresControllers(Proveedor pro, ProveedorDao proDao, SistemaInventario views) {
        this.pro = pro;
        this.proDao = proDao;
        this.views = views;
        this.views.btnguardarProveedor.addActionListener(this);
        this.views.btnEditarProveedor.addActionListener(this);
        this.views.TableProveedor.addMouseListener(this);
        this.views.btnProveedores.addMouseListener(this);
        this.views.txtBuscarProv.addKeyListener(this);
        AutoCompleteDecorator.decorate(views.cbxCategoria);
        AutoCompleteDecorator.decorate(views.cbxProveedorPro);
        AutoCompleteDecorator.decorate(views.cbxProveedor);
        llenarProveedor();
        listarProveedor(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnguardarProveedor){
             if(views.txtNombreproveedor.getText().equals("")
                     || views.txtRucProveedor.getText().equals("")
                     || views.txtDireccionProveedor.getText().equals("")
                     || views.txtTelefonoProveedor.getText().equals("")
                     || views.txtRazonProveedor.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
             }else{
                 pro.setNombre(views.txtNombreproveedor .getText());
                 pro.setRuc(views.txtRucProveedor.getText());
                 pro.setDireccion(views.txtDireccionProveedor.getText());
                 pro.setTelefono(views.txtTelefonoProveedor.getText());
                 pro.setRazon(views.txtRazonProveedor.getText());
                 if(proDao.RegistrarProveedor(pro)){
                     limpiarTable();
                     listarProveedor();
                     JOptionPane.showMessageDialog(null, "PROVEEDOR REGISTRADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
                 }
                 
             }  
                
         }else if(e.getSource() == views.btnEditarProveedor){
            if(views.txtIdProveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SELECCIONAR UNA FILA");
            }else{
                if(views.txtNombreproveedor.getText().equals("")
                   || views.txtRucProveedor.getText().equals("")
                   || views.txtDireccionProveedor.getText().equals("")
                   || views.txtTelefonoProveedor.getText().equals("")
                   || views.txtRazonProveedor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
                }else{
                 pro.setNombre(views.txtNombreproveedor .getText());
                 pro.setRuc(views.txtRucProveedor.getText());
                 pro.setDireccion(views.txtDireccionProveedor.getText());
                 pro.setTelefono(views.txtTelefonoProveedor.getText());
                 pro.setRazon(views.txtRazonProveedor.getText());
                 pro.setId(Integer.parseInt(views.txtIdProveedor.getText()));
                 if(proDao.ModificarProveedor(pro)){
                     limpiarTable();
                     listarProveedor();
                     JOptionPane.showMessageDialog(null, "PROVEEDOR MODIFICADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL EDITAR");
                 }
               }
            }
        }
        
    }
    
    public void listarProveedor(){

        List<Proveedor> Listarpr = proDao.ListarProveedor(views.txtBuscarProv.getText()); 
        modelo = (DefaultTableModel) views.TableProveedor.getModel();
        Object[] ob=new Object[6];
        for(int i=0;i<Listarpr.size();i++){
            ob[0] = Listarpr.get(i).getId();
            ob[1] = Listarpr.get(i).getNombre();
            ob[2] = Listarpr.get(i).getRuc();
            ob[3] = Listarpr.get(i).getDireccion();
            ob[4] = Listarpr.get(i).getTelefono();
            ob[5] = Listarpr.get(i).getRazon();
            modelo.addRow(ob);
        }
        views.TableProveedor.setModel(modelo);
    }
    
    public void limpiarTable(){
         for(int i=0; i < modelo.getRowCount(); i++){
             modelo.removeRow(i);
             i = i -1;
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableProveedor){
        int fila=views.TableProveedor.rowAtPoint(e.getPoint());
        views.txtIdProveedor.setText(views.TableProveedor.getValueAt(fila,0).toString());
        views.txtNombreproveedor.setText(views.TableProveedor.getValueAt(fila,1).toString());
        views.txtRucProveedor.setText(views.TableProveedor.getValueAt(fila,2).toString());
        views.txtDireccionProveedor.setText(views.TableProveedor.getValueAt(fila,3).toString());
        views.txtTelefonoProveedor.setText(views.TableProveedor.getValueAt(fila,4).toString());
        views.txtRazonProveedor.setText(views.TableProveedor.getValueAt(fila,5).toString());     
        views.btnguardarProveedor.setEnabled(false);
       }else if(e.getSource() == views.btnProveedores){
            views.jTabbedPane1.setSelectedIndex(1);
            limpiarTable();
            listarProveedor();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarProv){
            limpiarTable();
            listarProveedor();
        }
    }
    
    private void llenarProveedor(){
        List<Proveedor> Listarpr = proDao.ListarProveedor(views.txtBuscarProv.getText()); 
        
        for(int i=0;i<Listarpr.size();i++){
            int id = Listarpr.get(i).getId();
            String nombre = Listarpr.get(i).getNombre();
            views.cbxProveedorPro.addItem(new Combo(id, nombre));
            views.cbxProveedor.addItem(new Combo(id, nombre));
        }
    }


    
}
