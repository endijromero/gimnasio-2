/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.controller;

import com.gimnasio.persistences.Pagos;
import com.gimnasio.views.frmCliente;
import com.gimnasio.views.frmLogin;
import com.gimnasio.views.frmRegistrarPagos;
import com.gimnasio.views.frmPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class Principal {

    public Principal() {

    }

    /**
     *
     * @param login
     * @param user
     * @param password
     */
    public static void cambiarFrame(frmLogin login,
            String user,
            String password) {
        frmPrincipal principal = new frmPrincipal();
        login.setVisible(false);
        principal.setVisible(true);
    }
    
    /**
     *
     * @param cliente
     * @return
     */
    public static boolean setGuradarCliente(frmCliente cliente){
        return true;
    }

}
