/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.util.Util;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public class frmPrincipal extends javax.swing.JFrame {

    static frmRegistrarPagos frmRegistrarPagos;
    static frmCliente frmCliente;

    //private UsuariosDto usuarioSesion;
    private frmPaquetes paqueteView;
    private frmDescuentos descuentoView;
    private frmProductos productoView;
    private frmBuscarCliente buscarClienteView;
    private frmPagos pagosView;
    private frmCafeteria cafeteriaView;
    public frmRegistrarPagos registrarPagosView;
    public frmCliente clienteView;

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        //this.setLocationRelativeTo(null);  
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1250, 700);
        Util.setCentrarJFrame(this, null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Util.setFuncionesJFrame(this, false);

        this.paqueteView = null;
        this.descuentoView = null;
        this.productoView = null;
        this.buscarClienteView = null;
        this.clienteView = null;
        this.pagosView = null;
        this.registrarPagosView = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdstPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuPaquetes = new javax.swing.JMenuItem();
        menuDescuentos = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenu();
        menuCrear_cliente = new javax.swing.JMenuItem();
        menuBuscar_cliente = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuPagos = new javax.swing.JMenuItem();
        menuCafeteria = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jdstPrincipalLayout = new javax.swing.GroupLayout(jdstPrincipal);
        jdstPrincipal.setLayout(jdstPrincipalLayout);
        jdstPrincipalLayout.setHorizontalGroup(
            jdstPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        jdstPrincipalLayout.setVerticalGroup(
            jdstPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/cog-icon.png"))); // NOI18N
        jMenu1.setText("Administración");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menuPaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/folder-icon.png"))); // NOI18N
        menuPaquetes.setText("Paquetes");
        menuPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPaquetesActionPerformed(evt);
            }
        });
        jMenu1.add(menuPaquetes);

        menuDescuentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/box-in-icon.png"))); // NOI18N
        menuDescuentos.setText("Descuentos");
        menuDescuentos.setToolTipText("");
        menuDescuentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDescuentosActionPerformed(evt);
            }
        });
        jMenu1.add(menuDescuentos);

        menuProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/apple-2-icon.png"))); // NOI18N
        menuProductos.setText("Productos");
        menuProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductosActionPerformed(evt);
            }
        });
        jMenu1.add(menuProductos);

        jMenuBar1.add(jMenu1);

        menuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/user-male-alt-icon.png"))); // NOI18N
        menuCliente.setText("Clientes");
        menuCliente.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuClienteMenuSelected(evt);
            }
        });
        menuCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuClienteMouseClicked(evt);
            }
        });
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });

        menuCrear_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/user-male-alt-icon.png"))); // NOI18N
        menuCrear_cliente.setText("Crear");
        menuCrear_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setCrearCliente(evt);
            }
        });
        menuCliente.add(menuCrear_cliente);

        menuBuscar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Zoom-icon.png"))); // NOI18N
        menuBuscar_cliente.setText("Buscar");
        menuBuscar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuscar_clienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuBuscar_cliente);

        jMenuBar1.add(menuCliente);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/money-icon.png"))); // NOI18N
        jMenu3.setText("Pagos");

        menuPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/money-icon.png"))); // NOI18N
        menuPagos.setText("Pagos");
        menuPagos.setToolTipText("");
        menuPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPagosActionPerformed(evt);
            }
        });
        jMenu3.add(menuPagos);

        menuCafeteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Juice-icon.png"))); // NOI18N
        menuCafeteria.setText("Cafeteria");
        menuCafeteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCafeteriaActionPerformed(evt);
            }
        });
        jMenu3.add(menuCafeteria);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Hand-Touch-2-icon.png"))); // NOI18N
        jMenu4.setText("Registro");
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/medical-suitecase-icon.png"))); // NOI18N
        jMenu5.setText("Fisioterapia");
        jMenuBar1.add(jMenu5);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/file-powerpoint-icon.png"))); // NOI18N
        jMenu2.setText("Reportes");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdstPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdstPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPaquetesActionPerformed
        if (this.paqueteView == null) {
            try {
                this.paqueteView = new frmPaquetes(this);
                frmPrincipal.jdstPrincipal.add(this.paqueteView);
                this.paqueteView.setTitle("Formulario para el registro de paquetes");
                this.paqueteView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
                this.paqueteView.setClosable(true);
                this.paqueteView.setResizable(true);
                this.paqueteView.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(frmPrincipal.this, "El formulario para el registro de paquetes, ya se encuentra abierto", "Mensaje de información", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_menuPaquetesActionPerformed

    private void menuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductosActionPerformed
        // TODO add your handling code here:
        this.productoView = new frmProductos();
        frmPrincipal.jdstPrincipal.add(this.productoView);

        this.productoView.setSize(frmPrincipal.jdstPrincipal.getWidth(), frmPrincipal.jdstPrincipal.getHeight() - 1);
        this.productoView.setResizable(true);
        this.productoView.setClosable(true);
        this.productoView.setVisible(true);

    }//GEN-LAST:event_menuProductosActionPerformed

    private void menuDescuentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDescuentosActionPerformed
        try {
            // TODO add your handling code here:
            this.descuentoView = new frmDescuentos(this);
            frmPrincipal.jdstPrincipal.add(this.descuentoView);

            this.descuentoView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
            this.descuentoView.setResizable(true);
            this.descuentoView.setClosable(true);
            this.descuentoView.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuDescuentosActionPerformed

    private void menuPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagosActionPerformed
        // TODO add your handling code here:
        this.pagosView = new frmPagos();
        frmPrincipal.jdstPrincipal.add(this.pagosView);

        this.pagosView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
        this.pagosView.setResizable(true);
        this.pagosView.setClosable(true);
        this.pagosView.setVisible(true);
    }//GEN-LAST:event_menuPagosActionPerformed

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_menuClienteActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void menuClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClienteMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_menuClienteMouseClicked

    private void menuClienteMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuClienteMenuSelected
        // TODO add your handling code here:

    }//GEN-LAST:event_menuClienteMenuSelected

    private void setCrearCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setCrearCliente

        if (this.clienteView == null) {
            try {
                this.clienteView = new frmCliente(this);
                frmPrincipal.jdstPrincipal.add(this.clienteView);
                this.clienteView.setTitle("Formulario para el registro de clientes");
                this.clienteView.setSize(this.getWidth(), this.getHeight() - 1);
                this.clienteView.setResizable(true);
                this.clienteView.setClosable(true);
                this.clienteView.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(frmPrincipal.this, "El formulario para el registro de clientes, ya se encuentra abierto", "Mensaje de información", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_setCrearCliente

    private void menuBuscar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuscar_clienteActionPerformed
        // TODO add your handling code here:
        this.buscarClienteView = new frmBuscarCliente();
        frmPrincipal.jdstPrincipal.add(this.buscarClienteView);

        this.buscarClienteView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
        this.buscarClienteView.setResizable(true);
        this.buscarClienteView.setClosable(true);
        this.buscarClienteView.setVisible(true);
    }//GEN-LAST:event_menuBuscar_clienteActionPerformed

    private void menuCafeteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCafeteriaActionPerformed
        // TODO add your handling code here:
        this.cafeteriaView = new frmCafeteria();
        frmPrincipal.jdstPrincipal.add(this.cafeteriaView);

        this.cafeteriaView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
        this.cafeteriaView.setResizable(true);
        this.cafeteriaView.setClosable(true);
        this.cafeteriaView.setVisible(true);
    }//GEN-LAST:event_menuCafeteriaActionPerformed

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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmPrincipal principal = new frmPrincipal();
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);
            }
        });
    }

    public frmPaquetes getPaqueteView() {
        return paqueteView;
    }

    public void setPaqueteView(frmPaquetes paqueteView) {
        this.paqueteView = paqueteView;
    }

    public frmProductos getProductoView() {
        return productoView;
    }

    public void setProductoView(frmProductos productoView) {
        this.productoView = productoView;
    }

    public frmRegistrarPagos getPagosView() {
        return registrarPagosView;
    }

    public void setPagosView(frmRegistrarPagos registrarPagosView) {
        this.registrarPagosView = registrarPagosView;
    }

    public frmCliente getClienteView() {
        return clienteView;
    }

    public void setClienteView(frmCliente clienteView) {
        this.clienteView = clienteView;
    }

    public void setPago() {
        this.buscarClienteView = new frmBuscarCliente();
        frmPrincipal.jdstPrincipal.add(this.buscarClienteView);

        this.buscarClienteView.setSize(this.jdstPrincipal.getWidth(), this.jdstPrincipal.getHeight() - 1);
        this.buscarClienteView.setResizable(true);
        this.buscarClienteView.setClosable(true);
        this.buscarClienteView.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JDesktopPane jdstPrincipal;
    private javax.swing.JMenuItem menuBuscar_cliente;
    private javax.swing.JMenuItem menuCafeteria;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenuItem menuCrear_cliente;
    private javax.swing.JMenuItem menuDescuentos;
    private javax.swing.JMenuItem menuPagos;
    private javax.swing.JMenuItem menuPaquetes;
    private javax.swing.JMenuItem menuProductos;
    // End of variables declaration//GEN-END:variables
}
