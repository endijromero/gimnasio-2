/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.UsuarioDto;
import com.gimnasio.model.enums.EPerfiles;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class frmLogin extends javax.swing.JFrame {

    Operaciones operacion;

    /**
     * Creates new form Login
     */
    public frmLogin() {
        initComponents();
        this.operacion = new Operaciones();
        this.setLocationRelativeTo(null);
        // this.txtUser.setText("1052573174");
        // this.txtPassword.setText("1234");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenidos a StreetGym");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocation(new java.awt.Point(300, 400));
        setMinimumSize(new java.awt.Dimension(360, 390));
        setResizable(false);
        getContentPane().setLayout(null);

        txtUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUser.setToolTipText("Usuario");
        getContentPane().add(txtUser);
        txtUser.setBounds(100, 190, 180, 30);
        getContentPane().add(txtPassword);
        txtPassword.setBounds(100, 250, 180, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/logo.PNG"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(110, 10, 150, 150);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Contraseña");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 230, 70, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 170, 45, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/sign.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(100, 290, 120, 40);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/textura.jpg"))); // NOI18N
        jLabel1.setToolTipText("Usuario");
        jLabel1.setMaximumSize(new java.awt.Dimension(520, 200));
        jLabel1.setMinimumSize(new java.awt.Dimension(520, 200));
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 360, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (this.txtUser.getText().equals("") || this.txtPassword.getText().equals("")) {
            JLabel label = new JLabel("Ingrese usario y contraseña");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                UsuarioDto userDto = this.operacion.setValidateIngreso(this.txtUser.getText(), this.txtPassword.getText());
                if (userDto.getId() != null) {
                    frmPrincipal principal = new frmPrincipal();
                    principal.setUsuarioSessionDto(userDto);
                    principal.setTitle("::: StreetGym :::");
                    principal.setOperacion(operacion);
                    principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    principal.setDefaultCloseOperation(principal.EXIT_ON_CLOSE);
                    if (userDto.getTipoUsuario() == EPerfiles.FISIOTERAPEUTA.getId()) {
                        principal.getJmAdministracion().setEnabled(false);
                        principal.getJmClientes().setEnabled(false);
                        principal.getJmIngresos().setEnabled(false);
                        principal.getJmPagos().setEnabled(false);
                        principal.getJmReportes().setEnabled(false);
                    } else if (userDto.getTipoUsuario() == EPerfiles.RECEPCION.getId()) {
                        principal.getJmFisioterapeuta().setEnabled(false);
                    }
                    principal.setVisible(true);
                    try {
                        this.operacion.setCambiarEstadosPaquetes(userDto);
                    } catch (Exception e) {
                        Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, e);
                    }
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                JLabel label = new JLabel("Se presentó un error validando el cliente, intentelo nuevamente");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
