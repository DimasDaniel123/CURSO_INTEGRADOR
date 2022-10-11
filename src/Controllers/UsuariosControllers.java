
package Controllers;

import Modelo.Login;
import Modelo.LoginDAO;
import Vista.SistemaInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UsuariosControllers implements ActionListener, MouseListener{
    
    private Login log;
    private LoginDAO logDAO;
    private SistemaInventario views;
    
    DefaultTableModel modelo = new DefaultTableModel();
    public UsuariosControllers(Login log, LoginDAO logDAO, SistemaInventario views) {
        this.log = log;
        this.logDAO = logDAO;
        this.views = views;
        this.views.btnGuardarUsuario.addActionListener(this);
        this.views.btnEditarUsuario.addActionListener(this);
        this.views.TableUsuarios.addMouseListener(this);
        this.views.btnUsuarios.addMouseListener(this);
        listarUsuarios();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarUsuario){
            if (views.txtNombreUsuario.getText().equals("") 
                    || views.txtCorreo.getText().equals("")
                    || String.valueOf(views.txtClave.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
            }else { 
                log.setNombre(views.txtNombreUsuario.getText());
                log.setCorreo(views.txtCorreo.getText());
                log.setPass(String.valueOf(views.txtClave.getPassword()));
                log.setRol(views.cbxRol.getSelectedItem().toString());
                if(logDAO.registrar(log)){
                    limpiarTable();
                    listarUsuarios();
                    JOptionPane.showMessageDialog(null, "USUARIO REGISTRADO CORRECTAMENTE");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR USUARIO");
                }
            }
            
        }else if (e.getSource() == views.btnEditarUsuario){
            if (views.txtNombreUsuario.getText().equals("") 
                    || views.txtCorreo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
            }else { 
                log.setNombre(views.txtNombreUsuario.getText());
                log.setCorreo(views.txtCorreo.getText());
                log.setRol(views.cbxRol.getSelectedItem().toString());
                log.setId(Integer.parseInt(views.txtIdUsuario.getText()));
                if(logDAO.Modificar(log)){
                    limpiarTable();
                    listarUsuarios();
                    JOptionPane.showMessageDialog(null, "USUARIO EDITADO CORRECTAMENTE");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR AL EDITAR USUARIO");
                }
            }
            
        }
        
    }
    
    
    public void listarUsuarios(){
        List<Login> lista = logDAO.ListaUsuarios(); 
        modelo = (DefaultTableModel) views.TableUsuarios.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getCorreo();
            ob[3] = lista.get(i).getRol();
            modelo.addRow(ob);
        }
        views.TableUsuarios.setModel(modelo);
    }
    
    public void limpiarTable(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableUsuarios){
            int fila = views.TableUsuarios.rowAtPoint(e.getPoint());
            views.txtIdUsuario.setText(views.TableUsuarios.getValueAt(fila, 0).toString());
            views.txtNombreUsuario.setText(views.TableUsuarios.getValueAt(fila, 1).toString());
            views.txtCorreo.setText(views.TableUsuarios.getValueAt(fila, 2).toString());
            views.cbxRol.setSelectedItem(views.TableUsuarios.getValueAt(fila, 3).toString());
            views.txtClave.setEnabled(false);
            views.btnGuardarUsuario.setEnabled(false);
        }else if(e.getSource() == views.btnUsuarios){
            views.jTabbedPane1.setSelectedIndex(9);
            limpiarTable();
            listarUsuarios();
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
    
    
    
    
}
