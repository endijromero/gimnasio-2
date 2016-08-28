/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.ClienteIngresoDto;
import com.gimnasio.model.ComboDto;
import com.gimnasio.model.ComboModel;
import com.gimnasio.model.TablaDto;
import com.gimnasio.model.TablaModelo;
import com.gimnasio.model.UsuarioDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class frmClientesIngresos extends javax.swing.JInternalFrame {

    private UsuarioDto usuarioSessionDto;
    private final String[] headTable;
    private final TablaModelo table;
    protected Operaciones operacion;
    protected frmPrincipal padre;

    /**
     *
     * @param padre
     * @param operacion
     */
    public frmClientesIngresos(frmPrincipal padre, Operaciones operacion) {
        try {
            initComponents();
            this.operacion = operacion;
            this.padre = padre;
            this.lblTotalRetardos.setText(null);
            this.headTable = new String[]{"Documento", "Nombres", "Apellidos", "Edad", "Genero", "Movil", "Fijo", "Correo"};
            int widthColumna[] = {50, 250, 250, 250, 250, 50};
            this.table = new TablaModelo(this.headTable);
            this.txtTablaRegistroDia.setModel(this.table);
            int columnas = this.txtTablaRegistroDia.getColumnCount();
            for (int i = 0; i < columnas; i++) {
                this.txtTablaRegistroDia.getColumnModel().getColumn(i).setPreferredWidth(widthColumna[i]);
            }
            Calendar c = Calendar.getInstance();
            this.lblFechaActual.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
            List<TablaDto> resultado = this.operacion.getClientesIngresoTableDto();
            this.lblTotalRetardos.setText("TOTAL: " + resultado.size());
            for (TablaDto dto : resultado) {
                this.table.setAgregar(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmClientesIngresos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAsignarDatoTabla(TablaDto dto) {
        this.table.setAgregar(dto);
        this.lblTotalRetardos.setText("TOTAL: " + this.table.getRowCount());
        this.txtTablaRegistroDia.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtTablaRegistroDia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCapturaHuella = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblFechaActual = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalRetardos = new javax.swing.JLabel();

        txtTablaRegistroDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(txtTablaRegistroDia);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Registros del Día");

        btnCapturaHuella.setText("Registrar Huella");
        btnCapturaHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaHuellaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("La fecha actual para registrar el retardo es:");

        lblFechaActual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFechaActual.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("REGISTRO DE RETARDOS DE LOS ESTUDIANTES");

        lblTotalRetardos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalRetardos.setForeground(new java.awt.Color(153, 0, 0));
        lblTotalRetardos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalRetardos.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapturaHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRetardos, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapturaHuella)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaActual)
                    .addComponent(lblTotalRetardos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapturaHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaHuellaActionPerformed
//        frmHuella fh = new frmHuella(this.padre, true, new TablaDto(), Short.parseShort("3"), this);
//        fh.setUsuarioSesion(this.usuarioSessionDto);
//        fh.setVisible(true);
    }//GEN-LAST:event_btnCapturaHuellaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapturaHuella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaActual;
    private javax.swing.JLabel lblTotalRetardos;
    private javax.swing.JTable txtTablaRegistroDia;
    // End of variables declaration//GEN-END:variables
}
