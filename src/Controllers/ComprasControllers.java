
package Controllers;


import Modelo.Compras;
import Modelo.ComprasDao;
import Modelo.ProductoDao;
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

/**
 *
 * @author MIGUEL
 */
public class ComprasControllers implements MouseListener, KeyListener, ActionListener{
    private Compras comp;
    private ComprasDao  compDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ComprasControllers(Compras comp, ComprasDao compDao, SistemaInventario views) {
        this.comp = comp;
        this.compDao = compDao;
        this.views = views;
        this.views.btnCompras.addMouseListener(this);
        this.views.txtBuscarCompra.addKeyListener(this);
        this.views.btnReporte.addActionListener(this);
        this.views.TableIngresos.addMouseListener(this);
        listarCompras();
    }
    
    
    public void listarCompras(){

        List<Compras> lista = compDao.ListaCompras(views.txtBuscarCompra.getText()); 
        modelo = (DefaultTableModel) views.TableIngresos.getModel();
        Object[] ob=new Object[4];
        for(int i=0;i<lista.size();i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNom_proveedor();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob);
        }
        views.TableIngresos.setModel(modelo);
    }
    
    public void limpiarTable(){
         for(int i=0; i < modelo.getRowCount(); i++){
             modelo.removeRow(i);
             i = i -1;
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getSource() == views.btnCompras){
            views.jTabbedPane1.setSelectedIndex(8);
            limpiarTable();
            listarCompras();
        }else if(e.getSource() == views.TableIngresos){
            int fila=views.TableIngresos.rowAtPoint(e.getPoint());
            views.txtIdIngresos.setText(views.TableIngresos.getValueAt(fila,0).toString());

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
        if(e.getSource() == views.txtBuscarCompra){
            limpiarTable();
            listarCompras();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnReporte){

            if(views.txtIdIngresos.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SELECCIONA UNA FILA");
            }else { 
                int id_compra = Integer.parseInt(views.txtIdIngresos.getText());
                ProductoDao prodDao = new ProductoDao();
                prodDao.generarReporte(id_compra);
            }
        }
    }

    
    
    
}
