
package Controllers;

import Modelo.Categoria;
import Modelo.CategoriaDao;
import Modelo.Combo;
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



public class CategoriaControllers implements ActionListener, MouseListener, KeyListener{
    
    private Categoria cat;
    private CategoriaDao catDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();

    public CategoriaControllers(Categoria cat, CategoriaDao catDao, SistemaInventario views) {
        this.cat = cat;
        this.catDao = catDao;
        this.views = views;
        this.views.btnGuardarCat.addActionListener(this);
        this.views.btnEditarCat.addActionListener(this);
        this.views.TableCat.addMouseListener(this);
        this.views.btnCategoria.addMouseListener(this);
        this.views.txtBuscarCat.addKeyListener(this);
        AutoCompleteDecorator.decorate(views.cbxProveedorPro);
        listarCategorias();
        llenarCategoria();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarCat){
             if(views.txtNombreCat.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
             }else{
                 cat.setNombre(views.txtNombreCat .getText());
                 if(catDao.RegistrarCategoria(cat)){
                     limpiarTable();
                     listarCategorias();
                     JOptionPane.showMessageDialog(null, "CATEGORIA REGISTRADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
                 }       
             }  
                
        }else if(e.getSource() == views.btnEditarCat){
            if(views.txtIdCat.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SELECCIONAR UNA FILA");
            }else{
                if(views.txtNombreCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
                }else{
                 cat.setNombre(views.txtNombreCat .getText());
                 cat.setId(Integer.parseInt(views.txtIdCat.getText()));
                 if(catDao.ModificarCategoria(cat)){
                     limpiarTable();
                     listarCategorias();
                     JOptionPane.showMessageDialog(null, "CLIENTE MODIFICADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL EDITAR");
                 }
               }
            }
        }
        
    }
    
    
    public void listarCategorias(){

        List<Categoria> ListarCat = catDao.ListarCategoria(views.txtBuscarCat.getText()); 
        modelo = (DefaultTableModel) views.TableCat.getModel();
        Object[] ob=new Object[2];
        for(int i=0;i<ListarCat.size();i++){
            ob[0] = ListarCat.get(i).getId();
            ob[1] = ListarCat.get(i).getNombre();
            modelo.addRow(ob);
        }
        views.TableCat.setModel(modelo);
    }
    
    public void limpiarTable(){
         for(int i=0; i < modelo.getRowCount(); i++){
             modelo.removeRow(i);
             i = i -1;
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableCat){
        int fila=views.TableCat.rowAtPoint(e.getPoint());
        views.txtIdCat.setText(views.TableCat.getValueAt(fila,0).toString());
        views.txtNombreCat.setText(views.TableCat.getValueAt(fila,1).toString()); 
        views.btnGuardarCat.setEnabled(false);
       }else if(e.getSource() == views.btnCategoria){
            views.jTabbedPane1.setSelectedIndex(6);
            limpiarTable();
            listarCategorias();
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
        if(e.getSource() == views.txtBuscarCat){
            limpiarTable();
            listarCategorias();
        }
        
    }
    
    private void llenarCategoria(){
        List<Categoria> ListarCat = catDao.ListarCategoria(views.txtBuscarCat.getText()); 
        for(int i=0;i<ListarCat.size();i++){
            int id = ListarCat.get(i).getId();
            String nombre = ListarCat.get(i).getNombre();
            views.cbxCategoria.addItem(new Combo(id, nombre));
        }
    }
    
    
    
}
