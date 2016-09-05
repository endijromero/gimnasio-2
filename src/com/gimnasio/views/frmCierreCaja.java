/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.UsuarioDto;
import com.gimnasio.util.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rodolfo
 */
public class frmCierreCaja extends javax.swing.JInternalFrame {

    protected Operaciones operacion;
    protected frmPrincipal padre;

    /**
     *
     * @param padre
     */
    public frmCierreCaja(frmPrincipal padre) {
        initComponents();
        
       // this.operacion = new Operaciones();
        this.padre = padre;  
        
        Date date = new Date();
        this.txtFecha_inicio.setDate(date);
        this.txtFecha_fin.setDate(date);
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        lblFecha_nacimiento = new javax.swing.JLabel();
        txtFecha_inicio = new com.toedter.calendar.JDateChooser();
        txtFecha_fin = new com.toedter.calendar.JDateChooser();
        lblFecha_nacimiento1 = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                setCloseIframeBusquedaCliente(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/Zoom-icon.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setConsultaClientes(evt);
            }
        });

        lblFecha_nacimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha_nacimiento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha_nacimiento.setText("Fecha Inicio");

        txtFecha_inicio.setDateFormatString("yyyy-MM-dd");
        txtFecha_inicio.setMaxSelectableDate(new java.util.Date(253370786466000L));

        txtFecha_fin.setDateFormatString("yyyy-MM-dd");
        txtFecha_fin.setMaxSelectableDate(new java.util.Date(253370786466000L));

        lblFecha_nacimiento1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha_nacimiento1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha_nacimiento1.setText("Fecha Fin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha_nacimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(lblFecha_nacimiento1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblFecha_nacimiento))
                        .addComponent(txtFecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblFecha_nacimiento1))
                        .addComponent(txtFecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void setConsultaClientes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setConsultaClientes
        //this.setConsultarTableClientes();
        
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicio = data.format(this.txtFecha_inicio.getDate().getTime());
        String fechaFin = data.format(this.txtFecha_fin.getDate().getTime());
        
        String ruta = "cieerre_de_caja.jrxml";
        Map params = new HashMap<>();        

        params.put("FECHA_INICIO", fechaInicio);
        params.put("FECHA_FIN", fechaFin);
        Util.generarReportes(ruta, params);
    }//GEN-LAST:event_setConsultaClientes
    

    /**
     *
     * @param evt
     */
    private void setCloseIframeBusquedaCliente(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_setCloseIframeBusquedaCliente
        
    }//GEN-LAST:event_setCloseIframeBusquedaCliente
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFecha_nacimiento;
    private javax.swing.JLabel lblFecha_nacimiento1;
    private com.toedter.calendar.JDateChooser txtFecha_fin;
    private com.toedter.calendar.JDateChooser txtFecha_inicio;
    // End of variables declaration//GEN-END:variables
}
