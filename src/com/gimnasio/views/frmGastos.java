/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.gimnasio.controller.*;
import com.gimnasio.model.*;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public final class frmGastos extends javax.swing.JInternalFrame {

    private final frmPrincipal padre;
    private final String[] headTable;
    private final TablaModelo table;
    private final Operaciones operacion;
    private final GastoDto gastoDto;
    private UsuarioDto usuarioSessionDto;

    /**
     * Creates new form frmDescuentos
     *
     * @param padre
     * @param operacion
     * @throws java.sql.SQLException
     */
    public frmGastos(frmPrincipal padre, Operaciones operacion) throws SQLException {
        initComponents();
        this.gastoDto = new GastoDto();
        this.operacion = operacion;
        this.usuarioSessionDto = new UsuarioDto();
        

        this.headTable = new String[]{"Id", "Descripción", "Valor"};
        int widthColumna[] = {50, 200, 100};
        this.table = new TablaModelo(this.headTable);
        this.tblGastos.setModel(this.table);

        this.padre = padre;
        int columnas = this.tblGastos.getColumnCount();
        for (int i = 0; i < columnas; i++) {
            this.tblGastos.getColumnModel().getColumn(i).setPreferredWidth(widthColumna[i]);
        }
        this.setConsultarTableDescuentos();
        this.setLimpiar();
    }

    /**
     *
     * @throws SQLException
     */
    public void setConsultarTableDescuentos() throws SQLException {
        List<TablaDto> lista = this.operacion.getGastosDatosTablaDto(null);
        this.table.getData().clear();
        this.lblCantidad_descuentos.setText(String.valueOf(lista.size()));
        lista.stream().forEach((dto) -> {
            this.table.setAgregar(dto);
        });
        this.tblGastos.setDefaultRenderer(Object.class, new MiRender(this.table));
        this.tblGastos.repaint();
    }

    public void setLimpiar() {
        this.txtNombre_gasto.setText("");
        this.txtValor.setText("");
        this.gastoDto.setId(null);
        this.gastoDto.setDescripcion("");
        this.gastoDto.setValor(0);
        this.gastoDto.setFechaRegistro("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNombre_gasto = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        lblResultado = new javax.swing.JLabel();
        lblCantidad_descuentos = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                setCloseIframeDescuento(evt);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AGREGAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(500, 500));

        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidarSoloNumero(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/floppy-icon.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setGuardarDescuento(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Valor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Descripcón");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombre_gasto))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGuardar))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE GASTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(500, 500));

        tblGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setEditarDescuento(evt);
            }
        });
        jScrollPane1.setViewportView(tblGastos);

        lblResultado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblResultado.setText("Resultados");

        lblCantidad_descuentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCantidad_descuentos.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblResultado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad_descuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultado)
                    .addComponent(lblCantidad_descuentos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setGuardarDescuento(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setGuardarDescuento
        try {
            if (!this.txtNombre_gasto.getText().equals("") && !this.txtValor.getText().equals("")) {
                if (Double.parseDouble(this.txtValor.getText()) > 0) {
                    boolean guarda = false;
                    if (!this.txtNombre_gasto.getText().equals(this.gastoDto.getDescripcion())) {
                        guarda = true;
                        this.gastoDto.setDescripcion(this.txtNombre_gasto.getText());
                    }
                    double valor = Double.valueOf(this.txtValor.getText());
                    if (this.gastoDto.getValor() != valor) {
                        guarda = true;
                        this.gastoDto.setValor(valor);
                    }
                    if (guarda) {
                        boolean save = this.operacion.setSaveGastos(this.gastoDto, String.valueOf(this.usuarioSessionDto.getId()));
                        if (save) {
                            JLabel label = new JLabel("<html>Los datos para el Gasto: <b>" + this.gastoDto.getDescripcion()+ "</b>, fueron guardados correctamente</html>");
                            label.setFont(new Font("consolas", Font.PLAIN, 14));
                            JOptionPane.showMessageDialog(this, label, "Información", JOptionPane.INFORMATION_MESSAGE);
                            this.setConsultarTableDescuentos();
                            this.setLimpiar();
                        }
                    }
                } else {
                    JLabel label = new JLabel("El Valor debe ser mayor a 0");
                    label.setFont(new Font("consolas", Font.PLAIN, 14));
                    JOptionPane.showMessageDialog(this, label, "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JLabel label = new JLabel("Debe ingresar Descripción y Valor");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmPaquetes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_setGuardarDescuento

    /**
     *
     * @param evt
     */
    private void setValidarSoloNumero(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setValidarSoloNumero
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JLabel label = new JLabel("Ingrese solo numeros");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Error de datos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_setValidarSoloNumero

    private void setEditarDescuento(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setEditarDescuento
        if (evt.getClickCount() == 2) {
            int fila = this.tblGastos.getSelectedRow();
            TablaDto dto = (TablaDto) this.table.getData().get(fila);
            this.gastoDto.setId(Integer.parseInt(dto.getDato1()));
            this.txtNombre_gasto.setText(dto.getDato2());
            this.txtValor.setText(dto.getDato3());
        }
    }//GEN-LAST:event_setEditarDescuento

    private void setCloseIframeDescuento(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_setCloseIframeDescuento
        this.padre.setDescuentoView(null);
    }//GEN-LAST:event_setCloseIframeDescuento

     public UsuarioDto getUsuarioSessionDto() {
        return usuarioSessionDto;
    }
    
    public void setUsuarioSessionDto(UsuarioDto usuarioSessionDto) {
        this.usuarioSessionDto = usuarioSessionDto;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad_descuentos;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTable tblGastos;
    private javax.swing.JTextField txtNombre_gasto;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
