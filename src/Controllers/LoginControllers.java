
package Controllers;

import Modelo.Login;
import Modelo.LoginDAO;
import Vista.SistemaInventario;
import Vista.frmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControllers implements ActionListener{
    
    private Login log;
    private LoginDAO logDao;
    private frmLogin views;

    public LoginControllers(Login log, LoginDAO logDao, frmLogin views) {
        this.log = log;
        this.logDao = logDao;
        this.views = views;
        this.views.btnLogin.addActionListener(this);
        this.views.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == views.btnLogin){
            if(views.txtCorreo.getText().equals("") 
            || String.valueOf(views.txtPass.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "LOS CAMPOS ESTÁN VACÍOS");
            }else{
                 String correo = views.txtCorreo.getText();
                 String pass = String.valueOf(views.txtPass.getPassword());
                 log = logDao.log(correo, pass);
                 if(log.getCorreo() != null){
                     SistemaInventario sistema = new SistemaInventario(log);
                     sistema.setVisible(true);
                     this.views.dispose();
                 }else {
                  JOptionPane.showMessageDialog(null, "CORREO O CONTRASEÑA INCORRECTA");
                 }
            }
        }
    }
    
    
}
