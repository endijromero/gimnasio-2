package com.gimnasio.controller;

import com.gimnasio.model.*;
import com.gimnasio.persistences.*;
import com.gimnasio.util.Util;
import com.gimnasio.views.frmPrincipal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author emimaster16
 */
public class Operaciones {

    GimnasioModel model;

    public Operaciones() {
        this.model = new GimnasioModel();
    }

    /**
     * @tutorial valida el ingreso de los usuarios a ala plataforma
     * @param loggin
     * @param password
     * @return
     */
    public Boolean setValidateIngreso(String loggin, String password) {
        Boolean valido = false;
        List<Usuarios> listUsuarios = this.model.getDatosUsurios(loggin);
        if (listUsuarios.size() > 0) {
            Usuarios user = listUsuarios.get(0);
            if (Util.getEncriptarMD5(password).equals(user.getPassword())) {
                valido = true;
                frmPrincipal principal = new frmPrincipal();
                //principal.setUsuarioSesion
                principal.setTitle("Titulo");
                principal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valido;
    }
    
    //public void setRegistrarPaquetes(String nombrePaquete, )
}
