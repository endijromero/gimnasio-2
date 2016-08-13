package com.gimnasio.views;

import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.ComboDto;
import com.gimnasio.model.ComboModel;
import com.gimnasio.model.MiRender;
import com.gimnasio.model.PaqueteDto;
import com.gimnasio.model.TablaDto;
import com.gimnasio.model.TablaModelo;
import com.gimnasio.model.enums.ESiNo;
import com.gimnasio.util.Util;
import com.google.common.base.Joiner;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfo
 */
public final class frmPaquetes extends javax.swing.JInternalFrame {
    
    private List<ComboDto> listComboTipoPlan;
    private final ComboModel comboTipoPlan;
    private final frmPrincipal padre;
    private final String[] headTable;
    private final TablaModelo table;
    private PaqueteDto paqueteDto;
    private Operaciones operacion;

    /**
     *
     * @param padre
     * @param operacion
     * @throws SQLException
     */
    public frmPaquetes(frmPrincipal padre, Operaciones operacion) throws Exception {
        initComponents();
        this.operacion = operacion;
        this.paqueteDto = new PaqueteDto();
        
        ComboDto inicio;
        this.comboTipoPlan = new ComboModel();
        this.comboTipoPlan.getLista().clear();
        this.listComboTipoPlan = this.operacion.getTipoPlanEnumerado();
        inicio = new ComboDto("", "-------------");
        this.listComboTipoPlan.add(0, inicio);
        this.comboTipoPlan.getLista().addAll(this.listComboTipoPlan);
        this.comboTipoPlan.setSelectedItem(inicio);
        this.cmbTipo_plan.setModel(this.comboTipoPlan);
        
        this.headTable = new String[]{"Id", "Nombre", "Tipo", "Precio", "Tiquetera", "Diaz de aplazamiento"};
        int widthColumna[] = {50, 200, 100, 100, 100, 150};
        this.table = new TablaModelo(this.headTable);
        this.tblPaquetes.setModel(this.table);
        
        this.padre = padre;
        int columnas = this.tblPaquetes.getColumnCount();
        for (int i = 0; i < columnas; i++) {
            this.tblPaquetes.getColumnModel().getColumn(i).setPreferredWidth(widthColumna[i]);
        }
        this.setConsultarTablePaquetes();
    }

    /**
     *
     * @throws SQLException
     */
    public void setConsultarTablePaquetes() throws SQLException {
        List<TablaDto> lista = this.operacion.getPaquetesDatosTablaDto(null);
        this.table.getData().clear();
        this.lblCantidad_paquetes.setText(String.valueOf(lista.size()));
        for (TablaDto dto : lista) {
            this.table.setAgregar(dto);
        }
        setCleanFormulario();
        this.tblPaquetes.setDefaultRenderer(Object.class, new MiRender(this.table));
        this.tblPaquetes.repaint();
    }
    
    private void setCleanFormulario() {
        this.paqueteDto.setId(0);
        this.txtNombre_paquete.setText(null);
        this.txtDias_aplazamiento.setText(null);
        this.txtPrecio.setText(null);
        this.cmbTipo_plan.setSelectedIndex(0);
        this.cmbTipo_plan.repaint();
        this.rbtTiqutera.setSelected(false);
    }
    
    public Operaciones getOperacion() {
        return operacion;
    }
    
    public void setOperacion(Operaciones operacion) {
        this.operacion = operacion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre_paquete = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        lblNombre_paquete = new javax.swing.JLabel();
        rbtTiqutera = new javax.swing.JRadioButton();
        txtDias_aplazamiento = new javax.swing.JTextField();
        lblPrecio1 = new javax.swing.JLabel();
        lblPrecio2 = new javax.swing.JLabel();
        cmbTipo_plan = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaquetes = new javax.swing.JTable();
        lblResultado = new javax.swing.JLabel();
        lblCantidad_paquetes = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                limpiarfrmPaquetes(evt);
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

        txtNombre_paquete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidaSoloLetras(evt);
            }
        });

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidaNumero(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gimnasio/files/floppy-icon.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setGuardarPaquete(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio.setText("Precio");

        lblNombre_paquete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombre_paquete.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre_paquete.setText("Nombre");

        rbtTiqutera.setText("Tiquetera");

        txtDias_aplazamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setValidaNumero(evt);
            }
        });

        lblPrecio1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecio1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio1.setText("Aplazamiento");

        lblPrecio2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecio2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio2.setText("Tipo");

        cmbTipo_plan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar)
                    .addComponent(rbtTiqutera)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre_paquete, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipo_plan, 0, 213, Short.MAX_VALUE)
                            .addComponent(txtNombre_paquete)
                            .addComponent(txtPrecio)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPrecio1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDias_aplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_paquete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre_paquete))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo_plan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio2))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDias_aplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio1))
                .addGap(15, 15, 15)
                .addComponent(rbtTiqutera)
                .addGap(15, 15, 15)
                .addComponent(btnGuardar)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE PAQUETES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(500, 500));

        tblPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Precio base", "Días de aplazamiento", "Tiquetera"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Short.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setEditarPaquete(evt);
            }
        });
        jScrollPane1.setViewportView(tblPaquetes);

        lblResultado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblResultado.setText("Resultados");

        lblCantidad_paquetes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCantidad_paquetes.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblResultado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad_paquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultado)
                    .addComponent(lblCantidad_paquetes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarfrmPaquetes(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_limpiarfrmPaquetes
        this.padre.setPaqueteView(null);
    }//GEN-LAST:event_limpiarfrmPaquetes

    private void setGuardarPaquete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setGuardarPaquete
        try {
            ComboDto comboTipo = (ComboDto) this.cmbTipo_plan.getSelectedItem();
            List<String> listMessages = new ArrayList();
            if (this.txtNombre_paquete.getText().equals("")) {
                listMessages.add("<li>Nombre del paquete</li>");
            }
            if (Util.getVacio(comboTipo.getCodigo())) {
                listMessages.add("<li>Tipo del paquete</li>");
            }
            if (this.txtPrecio.getText().equals("")) {
                listMessages.add("<li>Precio del paquete</li>");
            } else {
                double precioBase = Double.parseDouble(this.txtPrecio.getText());
                if (precioBase < 0) {
                    listMessages.add("<li>El precio base del paquete debe ser mayor a 0</li>");
                }
            }
            if (!this.txtDias_aplazamiento.getText().equals("")) {
                short dias = Short.parseShort(this.txtDias_aplazamiento.getText());
                if (dias < 0) {
                    listMessages.add("<li>Los días de aplazamiento no pueden ser menores que 0</li>");
                }
            }
            if (listMessages.size() < 1) {
                this.paqueteDto.setNombre(this.txtNombre_paquete.getText());
                this.paqueteDto.setPrecioBase(Double.parseDouble(this.txtPrecio.getText()));
                this.paqueteDto.setYnTiquetera(this.rbtTiqutera.isSelected() ? ESiNo.SI.getId() : ESiNo.NO.getId());
                this.paqueteDto.setTipo(Short.parseShort(comboTipo.getCodigo()));
                if (!this.txtDias_aplazamiento.getText().equals("")) {
                    this.paqueteDto.setDiasAplazamiento(Short.parseShort(this.txtDias_aplazamiento.getText()));
                } else {
                    this.paqueteDto.setDiasAplazamiento(Short.parseShort("0"));
                }
                boolean correct = this.operacion.setGuardarPaquete(this.paqueteDto);
                if (correct) {
                    Util.setShowMessage(this, "<html>El paquete <b>" + this.paqueteDto.getNombre().toUpperCase() + "</b> se ha guardado correctamente</html>", "Información", JOptionPane.INFORMATION_MESSAGE);
                    this.setConsultarTablePaquetes();
                } else {
                    Util.setShowMessage(this, "<html>Error: el paquete <b>" + this.paqueteDto.getNombre().toUpperCase() + "</b> no se guardó</html>", "Alerta de error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JLabel label = new JLabel("<html>Verífique la siguiente lista de campos obligatorios:\n<ol>" + Joiner.on("\n").join(listMessages) + "</ol></html>");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de verificación de datos", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Util.setShowMessage(this, "<html>Error: el paquete <b>" + this.paqueteDto.getNombre().toUpperCase() + "</b> no se guardó</html>", "Alerta de error", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(frmPaquetes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_setGuardarPaquete

    private void setEditarPaquete(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setEditarPaquete
        if (evt.getClickCount() == 2) {
            try {
                int fila = this.tblPaquetes.getSelectedRow();
                TablaDto tableDto = (TablaDto) this.table.getData().get(fila);
                
                PaqueteDto dto = new PaqueteDto();
                List<PaqueteDto> lista = this.operacion.getPaquetesDatos(tableDto.getDato1());
                if (lista.size() > 0) {
                    dto = lista.get(0);
                }
                this.paqueteDto = dto;
                this.txtNombre_paquete.setText(this.paqueteDto.getNombre());
                this.txtPrecio.setText(String.valueOf(this.paqueteDto.getPrecioBase()));
                this.cmbTipo_plan.setSelectedIndex(this.paqueteDto.getTipo());
                this.cmbTipo_plan.repaint();
                this.rbtTiqutera.setSelected(this.paqueteDto.getYnTiquetera() == ESiNo.SI.getId());
                this.txtDias_aplazamiento.setText(String.valueOf(this.paqueteDto.getDiasAplazamiento()));
            } catch (SQLException ex) {
                JLabel label = new JLabel("Error: los datos no se consultaron de forma correcta");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                //Util.setShowMessage(this, "Error: los datos no se consultaron de forma correcta", "Alerta de error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(frmPaquetes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_setEditarPaquete

    private void setValidaNumero(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setValidaNumero
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JLabel label = new JLabel("Error: Ingrese solo números");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_setValidaNumero

    private void setValidaSoloLetras(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setValidaSoloLetras
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
            JLabel label = new JLabel("Error: Ingrese solo letras");
            label.setFont(new Font("consolas", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_setValidaSoloLetras


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbTipo_plan;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad_paquetes;
    private javax.swing.JLabel lblNombre_paquete;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecio1;
    private javax.swing.JLabel lblPrecio2;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JRadioButton rbtTiqutera;
    private javax.swing.JTable tblPaquetes;
    private javax.swing.JTextField txtDias_aplazamiento;
    private javax.swing.JTextField txtNombre_paquete;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
