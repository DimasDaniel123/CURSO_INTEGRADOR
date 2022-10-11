
package Controllers;

import Modelo.Cliente;
import Modelo.ClienteDao;
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

public class ClientesControllers implements ActionListener, MouseListener, KeyListener{
    private Cliente cl;
    private ClienteDao  clDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ClientesControllers(Cliente cl, ClienteDao clDao, SistemaInventario views) {
        this.cl = cl;
        this.clDao = clDao;
        this.views = views;
        this.views.btnGuardarCliente.addActionListener(this);
        this.views.btnEditarCliente.addActionListener(this);
        this.views.TableCliente.addMouseListener(this);
        this.views.btnClientes.addMouseListener(this);
        this.views.txtBuscarCli.addKeyListener(this);
        listarClientes();
        llenarClientes();
        AutoCompleteDecorator.decorate(views.cbxClientesV);
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarCliente){
             if(views.txtNombreCliente.getText().equals("")
                     || views.txtDniCliente.getText().equals("")
                     || views.txtDirecionCliente.getText().equals("")
                     || views.txtTelefonoCliente.getText().equals("")
                     || views.txtRazonCliente.getText().equals("")){
                     JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
             }else{
                 cl.setNombre(views.txtNombreCliente .getText());
                 cl.setDni(Integer.parseInt(views.txtDniCliente.getText()));
                 cl.setDireccion(views.txtDirecionCliente.getText());
                 cl.setTelefono(Integer.parseInt(views.txtTelefonoCliente.getText()));
                 cl.setRazon(views.txtRazonCliente.getText());
                 if(clDao.RegistrarCliente(cl)){
                     limpiarTable();
                     listarClientes();
                     JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
                 }       
             }  
                
        }else if(e.getSource() == views.btnEditarCliente){
            if(views.txtIdCliente.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SELECCIONAR UNA FILA");
            }else{
                if(views.txtNombreCliente.getText().equals("")
                   || views.txtDniCliente.getText().equals("")
                   || views.txtDirecionCliente.getText().equals("")
                   || views.txtTelefonoCliente.getText().equals("")
                   || views.txtRazonCliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
                }else{
                 cl.setNombre(views.txtNombreCliente .getText());
                 cl.setDni(Integer.parseInt(views.txtDniCliente.getText()));
                 cl.setDireccion(views.txtDirecionCliente.getText());
                 cl.setTelefono(Integer.parseInt(views.txtTelefonoCliente.getText()));
                 cl.setRazon(views.txtRazonCliente.getText());
                 cl.setId(Integer.parseInt(views.txtIdCliente.getText()));
                 if(clDao.ModificarCliente(cl)){
                     limpiarTable();
                     listarClientes();
                     JOptionPane.showMessageDialog(null, "CLIENTE MODIFICADO");
                 }else{
                     JOptionPane.showMessageDialog(null, "ERROR AL EDITAR");
                 }
               }
            }
        } 
    }   
    
    public void listarClientes(){

        List<Cliente> ListarCl = clDao.ListarCliente(views.txtBuscarCli.getText()); 
        modelo = (DefaultTableModel) views.TableCliente.getModel();
        Object[] ob=new Object[6];
        for(int i=0;i<ListarCl.size();i++){
            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getNombre();
            ob[2] = ListarCl.get(i).getDni();
            ob[3] = ListarCl.get(i).getDireccion();
            ob[4] = ListarCl.get(i).getTelefono();
            ob[5] = ListarCl.get(i).getRazon();
            modelo.addRow(ob);
        }
        views.TableCliente.setModel(modelo);
    }
    
    public void limpiarTable(){
         for(int i=0; i < modelo.getRowCount(); i++){
             modelo.removeRow(i);
             i = i -1;
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableCliente){
        int fila=views.TableCliente.rowAtPoint(e.getPoint());
        views.txtIdCliente.setText(views.TableCliente.getValueAt(fila,0).toString());
        views.txtNombreCliente.setText(views.TableCliente.getValueAt(fila,1).toString());
        views.txtDniCliente.setText(views.TableCliente.getValueAt(fila,2).toString());
        views.txtDirecionCliente.setText(views.TableCliente.getValueAt(fila,3).toString());
        views.txtTelefonoCliente.setText(views.TableCliente.getValueAt(fila,4).toString());
        views.txtRazonCliente.setText(views.TableCliente.getValueAt(fila,5).toString());   
        views.btnGuardarCliente.setEnabled(false);
       }else if(e.getSource() == views.btnClientes){
            views.jTabbedPane1.setSelectedIndex(0);
            limpiarTable();
            listarClientes();
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
        if(e.getSource() == views.txtBuscarCli){
            limpiarTable();
            listarClientes();
        }
    }
    
    private void llenarClientes(){
        List<Cliente> Listarpr = clDao.ListarCliente(views.txtBuscarCli.getText()); 
        
        for(int i=0;i<Listarpr.size();i++){
            int id = Listarpr.get(i).getId();
            String nombre = Listarpr.get(i).getNombre();
            views.cbxClientesV.addItem(new Combo(id, nombre));
        }
    }
    
    
    
}
