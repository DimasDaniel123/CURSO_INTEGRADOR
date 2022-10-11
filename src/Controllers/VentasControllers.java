
package Controllers;


import Modelo.ProductoDao;
import Modelo.Ventas;
import Modelo.VentasDao;
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


public class VentasControllers implements MouseListener, KeyListener, ActionListener{
    private Ventas vent;
    private VentasDao  ventDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentasControllers(Ventas vent, VentasDao ventDao, SistemaInventario views) {
        this.vent = vent;
        this.ventDao = ventDao;
        this.views = views;
        this.views.btnVentas.addMouseListener(this);
        this.views.txtBuscarVenta.addKeyListener(this);
        this.views.btnReporteVenta.addActionListener(this);
        this.views.TableVentas.addMouseListener(this);
        listarVentas();
    }
    
    
    
    public void listarVentas(){

        List<Ventas> lista = ventDao.ListaVentas(views.txtBuscarVenta.getText()); 
        modelo = (DefaultTableModel) views.TableVentas.getModel();
        Object[] ob=new Object[4];
        for(int i=0;i<lista.size();i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNom_cliente();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        views.TableVentas.setModel(modelo);
    }
    
    public void limpiarTable(){
         for(int i=0; i < modelo.getRowCount(); i++){
             modelo.removeRow(i);
             i = i -1;
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getSource() == views.btnVentas){
            views.jTabbedPane1.setSelectedIndex(4);
            limpiarTable();
            listarVentas();
        }else if(e.getSource() == views.TableVentas){
            int fila=views.TableVentas.rowAtPoint(e.getPoint());
            views.txtIdVenta.setText(views.TableVentas.getValueAt(fila,0).toString());

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
        if(e.getSource() == views.txtBuscarVenta){
            limpiarTable();
            listarVentas();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnReporteVenta){

            if(views.txtIdVenta.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SELECCIONA UNA FILA");
            }else { 
                int id_venta = Integer.parseInt(views.txtIdVenta.getText());
                ProductoDao prodDao = new ProductoDao();
                prodDao.generarReporteVenta(id_venta);
            }
        }
    }

    
    
    
}
