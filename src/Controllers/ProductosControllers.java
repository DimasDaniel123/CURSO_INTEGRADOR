package Controllers;

import Modelo.Combo;
import Modelo.Producto;
import Modelo.ProductoDao;
import Vista.SistemaInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ProductosControllers implements ActionListener, MouseListener, KeyListener {

    private Producto pro;
    private ProductoDao prodDao;
    private SistemaInventario views;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp;

    public ProductosControllers(Producto pro, ProductoDao prodDao, SistemaInventario views) {
        this.pro = pro;
        this.prodDao = prodDao;
        this.views = views;
        this.views.btnGuardarpro.addActionListener(this);
        this.views.btnEditarpro.addActionListener(this);
        this.views.TableProducto.addMouseListener(this);
        this.views.btnProductos.addMouseListener(this);
        this.views.txtBuscarProducto.addKeyListener(this);
        this.views.txtCodigoCompra.addKeyListener(this);
        this.views.txtCantidadCompra.addKeyListener(this);
        this.views.txtCodigoVenta.addKeyListener(this);
        this.views.txtCantidadVenta.addKeyListener(this);
        this.views.jtxtMontoPagoC.addKeyListener(this);
        this.views.btnGenerarCompra.addActionListener(this);
        this.views.btnGenerarVenta.addActionListener(this);
        this.views.btnNuevaCompra.addMouseListener(this);
        this.views.btnNuevaVenta.addMouseListener(this);
        listarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarpro) {
            if (views.txtCodigoPro.getText().equals("")
                    || views.txtDesPro.getText().equals("")
                    || views.txtPrecioPro.getText().equals("")
                    || views.txtPrecioV.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
            } else {
                pro.setCodigo(views.txtCodigoPro.getText());
                pro.setNombre(views.txtDesPro.getText());
                pro.setPrecio(Double.parseDouble(views.txtPrecioPro.getText()));
                pro.setPrecioV(Double.parseDouble(views.txtPrecioV.getText()));
                Combo itemP = (Combo) views.cbxProveedorPro.getSelectedItem();
                Combo itemC = (Combo) views.cbxCategoria.getSelectedItem();
                pro.setId_proveedor(itemP.getId());
                pro.setId_categoria(itemC.getId());
                if (prodDao.RegistrarProductos(pro)) {
                    limpiarTable();
                    listarProductos();
                    JOptionPane.showMessageDialog(null, "PRODUCTO REGISTRADO");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
                }
            }

        } else if (e.getSource() == views.btnEditarpro) {
            if (views.txtIdpro.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "SELECCIONAR UNA FILA");
            } else {
                if (views.txtCodigoPro.getText().equals("")
                        || views.txtDesPro.getText().equals("")
                        || views.txtPrecioPro.getText().equals("")
                        || views.txtPrecioV.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
                } else {
                    pro.setCodigo(views.txtCodigoPro.getText());
                    pro.setNombre(views.txtDesPro.getText());
                    pro.setPrecio(Double.parseDouble(views.txtPrecioPro.getText()));
                    pro.setPrecioV(Double.parseDouble(views.txtPrecioV.getText()));
                    Combo itemP = (Combo) views.cbxProveedorPro.getSelectedItem();
                    Combo itemC = (Combo) views.cbxCategoria.getSelectedItem();
                    pro.setId_proveedor(itemP.getId());
                    pro.setId_categoria(itemC.getId());
                    pro.setId(Integer.parseInt(views.txtIdpro.getText()));
                    if (prodDao.ModificarProductos(pro)) {
                        limpiarTable();
                        listarProductos();
                        JOptionPane.showMessageDialog(null, "PRODUCTO MODIFICADO");
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL EDITAR");
                    }
                }
            }
        } else if (e.getSource() == views.btnGenerarCompra) {
            insertarCompra();
        } else if (e.getSource() == views.btnGenerarVenta) {
            insertarVenta();
        }
    }

    public void listarProductos() {

        List<Producto> ListarPro = prodDao.ListarProductos(views.txtBuscarProducto.getText());
        modelo = (DefaultTableModel) views.TableProducto.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getNombre();
            ob[3] = ListarPro.get(i).getStock();
            ob[4] = ListarPro.get(i).getPrecio();
            ob[5] = ListarPro.get(i).getPrecioV();
            modelo.addRow(ob);
        }
        views.TableProducto.setModel(modelo);
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiarTableDetalleCompra() {
        for (int i = 0; i < tmp.getRowCount(); i++) {
            tmp.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableProducto) {
            int fila = views.TableProducto.rowAtPoint(e.getPoint());
            views.txtIdpro.setText(views.TableProducto.getValueAt(fila, 0).toString());
            pro = prodDao.buscarPro(Integer.parseInt(views.txtIdpro.getText()));
            views.txtCodigoPro.setText(pro.getCodigo());
            views.txtDesPro.setText(pro.getNombre());
            views.txtPrecioPro.setText("" + pro.getPrecio());
            views.txtPrecioV.setText("" + pro.getPrecioV());
            views.cbxProveedorPro.setSelectedItem(new Combo(pro.getId_proveedor(), pro.getProveedor()));
            views.cbxCategoria.setSelectedItem(new Combo(pro.getId_categoria(), pro.getCat()));
            views.btnGuardarpro.setEnabled(false);
        } else if (e.getSource() == views.btnProductos) {
            views.jTabbedPane1.setSelectedIndex(2);
            limpiarTable();
            listarProductos();
        } else if (e.getSource() == views.btnNuevaCompra) {
            views.jTabbedPane1.setSelectedIndex(7);
        } else if (e.getSource() == views.btnNuevaVenta) {
            views.jTabbedPane1.setSelectedIndex(3);
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
        if (e.getSource() == views.txtCodigoCompra) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = views.txtCodigoCompra.getText();
                buscarProducto(views.txtCodigoCompra, cod, views.txtIdCompra, views.txtDescripcionCompra, views.txtPrecioCompra, views.txtCantidadCompra, 0);
            }
        } else if (e.getSource() == views.txtCodigoVenta) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = views.txtCodigoVenta.getText();
                buscarProducto(views.txtCodigoVenta, cod, views.txtIdV, views.txtDescripcionVenta, views.txtPrecioVenta, views.txtCantidadVenta, 1);
            }
        }  else if (e.getSource() == views.txtCantidadCompra) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int cant = Integer.parseInt(views.txtCantidadCompra.getText());
                String desc = views.txtDescripcionCompra.getText();
                double precio = Double.parseDouble(views.txtPrecioCompra.getText());
                int id = Integer.parseInt(views.txtIdCompra.getText());
                agregarTemp(cant, desc, precio, id, views.TableCompra, views.txtCodigoCompra);
                limpiarCompras();
                calcularTotal(views.TableCompra, views.jLabelTotalC);
            }
        } else if (e.getSource() == views.txtCantidadVenta) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(views.txtCantidadVenta.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD");
                } else{
                    int cant = Integer.parseInt(views.txtCantidadVenta.getText());
                    int stock = Integer.parseInt(views.txtStockDisponible.getText());
                    if(cant > stock){
                        JOptionPane.showMessageDialog(null, "STOCK NO DISPONIBLE");
                    } else{
                        String desc = views.txtDescripcionVenta.getText();
                        double precio = Double.parseDouble(views.txtPrecioVenta.getText());
                        int id = Integer.parseInt(views.txtIdV.getText());
                        agregarTemp(cant, desc, precio, id, views.TableVenta, views.txtCodigoVenta);
                        limpiarVentas();
                        calcularTotal(views.TableVenta, views.TotalVenta);
                    }
                
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarProducto) {
            limpiarTable();
            listarProductos();
        }

        if (e.getSource() == views.txtCantidadCompra) {
            int cantidad;
            double precio;
            if (views.txtCantidadCompra.getText().equals("")) {
                cantidad = 1;
                precio = Double.parseDouble(views.txtPrecioCompra.getText());
                views.txtTotalCompra.setText("" + precio);
            } else {
                cantidad = Integer.parseInt(views.txtCantidadCompra.getText());
                precio = Double.parseDouble(views.txtPrecioCompra.getText());
                views.txtTotalCompra.setText("" + cantidad * precio);
            }
        } if (e.getSource() == views.txtCantidadVenta) {
            int cantidad;
            double precio;
            if (views.txtCantidadVenta.getText().equals("")) {
                cantidad = 1;
                precio = Double.parseDouble(views.txtPrecioVenta.getText());
                views.txtTotalVenta.setText("" + precio);
            } else {
                cantidad = Integer.parseInt(views.txtCantidadVenta.getText());
                precio = Double.parseDouble(views.txtPrecioVenta.getText());
                views.txtTotalVenta.setText("" + cantidad * precio);
            }
        } else if (e.getSource() == views.jtxtMontoPagoC) {
            int pagar;
            if (views.jtxtMontoPagoC.getText().equals("")) {
                views.jtxtVueltoCompra.setText("");
            } else {
                pagar = Integer.parseInt(views.jtxtMontoPagoC.getText());
                double total = Double.parseDouble(views.jLabelTotalC.getText());
                views.jtxtVueltoCompra.setText("" + (pagar - total));
            }

        }
    }

    private void limpiarCompras() {
        views.txtCodigoCompra.setText("");
        views.txtIdCompra.setText("");
        views.txtDescripcionCompra.setText("");
        views.txtCantidadCompra.setText("");
        views.txtPrecioCompra.setText("");
        views.txtTotalCompra.setText("");
    }
    
    private void limpiarVentas() {
        views.txtCodigoVenta.setText("");
        views.txtIdV.setText("");
        views.txtDescripcionVenta.setText("");
        views.txtCantidadVenta.setText("");
        views.txtPrecioVenta.setText("");
        views.txtTotalVenta.setText("");
        views.txtStockDisponible.setText("");
    }

    private void calcularTotal(JTable tabla, JLabel totalPagar) {
        double total = 0.00;
        int numFila = tabla.getRowCount();
        for (int i = 0; i < numFila; i++) {
            total = total + Double.parseDouble(String.valueOf(tabla.getValueAt(i, 4)));
        }
        totalPagar.setText("" + total);
    }

    private void insertarCompra() {
        Combo id_p = (Combo) views.cbxProveedor.getSelectedItem();
        int id_proveedor = id_p.getId();
        String total = views.jLabelTotalC.getText();
        if (prodDao.registrarCompra(id_proveedor, total)) {
            int id_compra = prodDao.getUltimoId("compras");
            for (int i = 0; i < views.TableCompra.getRowCount(); i++) {
                double precio = Double.parseDouble(views.TableCompra.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(views.TableCompra.getValueAt(i, 2).toString());
                int id = Integer.parseInt(views.TableCompra.getValueAt(i, 0).toString());
                double sub_total = precio * cantidad;
                prodDao.registrarCompraDetalle(id_compra, id, precio, cantidad, sub_total);
                pro = prodDao.buscarId(id);
                int stockActual = pro.getStock() + cantidad;
                prodDao.ActualizarStock(stockActual, id);
            }
            limpiarTableDetalleCompra();
            JOptionPane.showMessageDialog(null, "COMPRA GENERADA");
            prodDao.generarReporte(id_compra);

        }
    }

    //agregar productos para la compra y venta
    private void agregarTemp(int cant, String desc, double precio, int id, JTable tabla, JTextField codigo) {
        if (cant > 0) {
            tmp = (DefaultTableModel) tabla.getModel();
            ArrayList lista = new ArrayList();
            int item = 1;
            lista.add(item);
            lista.add(id);
            lista.add(desc);
            lista.add(cant);
            lista.add(precio);
            lista.add(cant * precio);
            Object[] obj = new Object[5];
            obj[0] = lista.get(1);
            obj[1] = lista.get(2);
            obj[2] = lista.get(3);
            obj[3] = lista.get(4);
            obj[4] = lista.get(5);
            tmp.addRow(obj);
            tabla.setModel(tmp);
            codigo.requestFocus();
        }

    }

    //buscar productos para la compra y venta
    private void buscarProducto(JTextField campo, String cod, JTextField id, JTextField producto, JTextField precio, JTextField cantidad, int accion) {
        if (campo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "INGRESE EL CODIGO");
        } else {
            pro = prodDao.buscarCodigo(cod);          
            id.setText("" + pro.getId());
            producto.setText(pro.getNombre());
            if(accion == 0){
                precio.setText("" + pro.getPrecio());
            }else{
                precio.setText("" + pro.getPrecioV());
                views.txtStockDisponible.setText("" + pro.getStock());
            }
            cantidad.requestFocus();
        }
    }
    
    //insertar venta
    private void insertarVenta() {
        Combo cliente = (Combo) views.cbxClientesV.getSelectedItem();
        int id_cliente = cliente.getId();
        String total = views.TotalVenta.getText();
        if (prodDao.registrarVenta(id_cliente, total)) {
            int id = prodDao.getUltimoId("ventas");
            for (int i = 0; i < views.TableVenta.getRowCount(); i++) {
                double precio = Double.parseDouble(views.TableVenta.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(views.TableVenta.getValueAt(i, 2).toString());
                int id_producto = Integer.parseInt(views.TableVenta.getValueAt(i, 0).toString());
                double sub_total = precio * cantidad;
                prodDao.registrarVentaDetalle(id, id_producto, precio, cantidad, sub_total);
                pro = prodDao.buscarId(id_producto);
                int stockActual = pro.getStock() - cantidad;
                prodDao.ActualizarStock(stockActual, id_producto);
            }
            limpiarTableDetalleCompra();
            JOptionPane.showMessageDialog(null, "VENTA GENERADA");
            prodDao.generarReporteVenta(id);

        }
    }
}
