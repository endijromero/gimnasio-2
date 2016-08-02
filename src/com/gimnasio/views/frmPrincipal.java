/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import javax.swing.JFrame;

/**
 *
 * @author rodolfo
 */
public class frmPrincipal extends javax.swing.JFrame {
    private frmPaquetes paqueteView;
    private frmDescuentos descuentoView;
    private frmProductos productoView;
    private frmCliente clienteView;

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);  
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.paqueteView = null;
        this.descuentoView = null;
        this.productoView = null;
        
        this.clienteView = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dstPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuPaquetes = new javax.swing.JMenuItem();
        menuDescuentos = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenu();
        menuCrear_cliente = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuPagos = new javax.swing.JMenuItem();
        menuCafeteria = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout dstPrincipalLayout = new javax.swing.GroupLayout(dstPrincipal);
        dstPrincipal.setLayout(dstPrincipalLayout);
        dstPrincipalLayout.setHorizontalGroup(
            dstPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        dstPrincipalLayout.setVerticalGroup(
            dstPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                menuCrear_clienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuCrear_cliente);

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
        jMenu3.add(menuCafeteria);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Hand-Touch-2-icon.png"))); // NOI18N
        jMenu4.setText("Registro");
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/medical-suitecase-icon.png"))); // NOI18N
        jMenu5.setText("Fisioterapia");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dstPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dstPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPaquetesActionPerformed
        // TODO add your handling code here:
        this.paqueteView = new frmPaquetes();
        this.dstPrincipal.add(this.paqueteView);

        this.paqueteView.setSize(this.getWidth()-15, this.getHeight()-10);
        this.paqueteView.setResizable(true);
        this.paqueteView.setClosable(true);
        this.paqueteView.setVisible(true);
    }//GEN-LAST:event_menuPaquetesActionPerformed

    private void menuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductosActionPerformed
        // TODO add your handling code here:
        this.productoView = new frmProductos();
        this.dstPrincipal.add(this.productoView);

        this.productoView.setSize(this.getWidth()-15, this.getHeight()-10);
        this.productoView.setResizable(true);
        this.productoView.setClosable(true);
        this.productoView.setVisible(true);
        
    }//GEN-LAST:event_menuProductosActionPerformed

    private void menuDescuentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDescuentosActionPerformed
        // TODO add your handling code here:
        this.descuentoView = new frmDescuentos();
        this.dstPrincipal.add(this.descuentoView);

        this.descuentoView.setSize(this.getWidth()-15, this.getHeight()-10);
        this.descuentoView.setResizable(true);
        this.descuentoView.setClosable(true);
        this.descuentoView.setVisible(true);
    }//GEN-LAST:event_menuDescuentosActionPerformed

    private void menuPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagosActionPerformed
        // TODO add your handling code here:
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

    private void menuCrear_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrear_clienteActionPerformed
        // TODO add your handling code here:
        this.clienteView = new frmCliente();
        this.dstPrincipal.add(this.clienteView);

        this.clienteView.setSize(this.getWidth()-15, this.getHeight()-10);
        this.clienteView.setResizable(true);
        this.clienteView.setClosable(true);
        this.clienteView.setVisible(true);
    }//GEN-LAST:event_menuCrear_clienteActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dstPrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuCafeteria;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenuItem menuCrear_cliente;
    private javax.swing.JMenuItem menuDescuentos;
    private javax.swing.JMenuItem menuPagos;
    private javax.swing.JMenuItem menuPaquetes;
    private javax.swing.JMenuItem menuProductos;
    // End of variables declaration//GEN-END:variables
}
