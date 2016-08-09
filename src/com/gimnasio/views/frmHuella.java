/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.views;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_READY;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.gimnasio.controller.Operaciones;
import com.gimnasio.model.ClienteDto;
import com.gimnasio.model.HiloBusqueda;
import com.gimnasio.model.UsuarioDto;
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.gimnasio.util.Util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author user
 */
public class frmHuella extends javax.swing.JDialog {

    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private static String TEMPLATE_PROPERTY = "template";
    private DPFPFeatureSet featuresverificacion;
    private DPFPFeatureSet featuresinscripcion;
    private List<DPFPTemplate> listTemplates;

    private List<List<ClienteDto>> listClientes;
    private List<ClienteDto> listClientesHuellas;
    private DPFPTemplate template;
    private Operaciones operacion;

    private BufferedInputStream usuarioIncorrectoCache;
    private BufferedInputStream registradoCache;
    private FileInputStream usuarioIncorrecto;
    private Player playUsuarioIncorrecto;
    private FileInputStream registrado;
    private Player playRegistrado;

    private List<HiloBusqueda> listaHilos;
    private boolean cambia = false;
    private UsuarioDto usuarioDto;
    private ClienteDto clienteDto;
    private int cantidadHuellas;
    private short tipoProceso;
    private frmClientes frmCliente;
    private frmUsuarios frmUsuario;

    public frmHuella(Operaciones operacion, javax.swing.JFrame parent, boolean modal, ClienteDto cliDto, short tipoProceso, frmClientes frmCliente) {
        super(parent, modal);
        initComponents();
        this.cambia = false;
        this.operacion = operacion;
        try {
            this.listTemplates = new ArrayList();
            this.listClientesHuellas = this.operacion.getClienteDatos(null);
            for (ClienteDto dto : this.listClientesHuellas) {
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate();
                referenceTemplate.deserialize(dto.getPersonaDto().getHuellaDactilar());
                dto.getPersonaDto().setTemplateHuella(referenceTemplate);
            }
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        if (this.listClientesHuellas.size() > 0) {
            this.listClientes = (List<List<ClienteDto>>) Util.getDivideArray(this.listClientesHuellas, Util.CANTIDAD_DIVIDE_ARRAY);
        }

        this.frmCliente = frmCliente;
        this.cantidadHuellas = 0;
        Util.setCentrarJFrame(null, this);
        this.setResizable(false);
        this.lblIndiceDerecho.setText(null);

        this.tipoProceso = tipoProceso;
        this.clienteDto = cliDto;

        if (this.clienteDto.getId() != null) {
            this.lblCodigo.setText(this.clienteDto.getId().toString());
        }
        switch (tipoProceso) {
            case 1: {
                this.btnGuardar.setEnabled(false);
                this.lblEstudiante.setText(this.clienteDto.getPersonaDto().getNombreCompleto());
            }
            break;
            case 2: {
                this.lblEstudiante.setText(null);
                this.btnGuardar.setVisible(false);
            }
            break;
            default: {
                this.lblEstudiante.setText(null);
                this.btnGuardar.setVisible(false);
                this.btnCancelar.setText("Cerrar");
            }
            break;
        }
    }

    public frmHuella(Operaciones operacion, javax.swing.JFrame parent, boolean modal, UsuarioDto usuarioDto, short tipoProceso, frmUsuarios frmUsuario) {
        super(parent, modal);
        initComponents();
        this.cambia = false;
        this.operacion = operacion;
        try {
            this.listTemplates = new ArrayList();
            this.listClientesHuellas = new ArrayList(); //this.operacion.getClientesDatos();
            for (ClienteDto dto : this.listClientesHuellas) {
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(dto.getPersonaDto().getHuellaDactilar());
                dto.getPersonaDto().setTemplateHuella(referenceTemplate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        this.listClientes = (List<List<ClienteDto>>) Util.getDivideArray(this.listClientesHuellas, Util.CANTIDAD_DIVIDE_ARRAY);

        this.frmUsuario = frmUsuario;
        this.cantidadHuellas = 0;
        Util.setCentrarJFrame(null, this);
        this.setResizable(false);
        this.lblIndiceDerecho.setText(null);

        this.tipoProceso = tipoProceso;
        this.usuarioDto = usuarioDto;

        if (this.clienteDto.getId() != null) {
            this.lblCodigo.setText(this.clienteDto.getId().toString());
        }
        switch (tipoProceso) {
            case 1: {
                this.btnGuardar.setEnabled(false);
                this.lblEstudiante.setText(this.clienteDto.getPersonaDto().getNombreCompleto());
            }
            break;
            case 2: {
                this.lblEstudiante.setText(null);
                this.btnGuardar.setVisible(false);
            }
            break;
            default: {
                this.lblEstudiante.setText(null);
                this.btnGuardar.setVisible(false);
                this.btnCancelar.setText("Cerrar");
            }
            break;
        }
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public void stop() {
        if (Lector.isStarted()) {
            Lector.stopCapture();
        }
        //this.frmCliente.setUsuarioSessionDto(usuarioSessionDto);
    }

    public void start() {
        if (!this.Lector.isStarted()) {
            Lector.startCapture();
        }
        //EnviarTexto("Utilizando el Lector de Huella Dactilar ");
    }

    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("La Huella Digital ha sido Capturada");
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
                    }
                });
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("El dedo ha sido quitado del Lector de Huella");
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //EnviarTexto("Error: "+e.getError());
                    }
                });
            }
        });
    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            this.btnCancelar.setEnabled(true);
            return null;
        }
    }

    public void ProcesarCaptura(DPFPSample sample) {
        this.listaHilos = null;
        boolean conErrores = false;
        this.listaHilos = null;
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        if (featuresinscripcion != null) {
            try {
                this.btnCancelar.setEnabled(true);
                Reclutador.addFeatures(featuresinscripcion);
                Image image = CrearImagenHuella(sample);
                Util.setDibujarHuella(image, this.lblIndiceDerecho, this);
            } catch (DPFPImageQualityException ex) {
                JOptionPane.showMessageDialog(frmHuella.this, "Las huellas no coinciden, Vuelva a intentarlo", "Error!", JOptionPane.ERROR_MESSAGE);
                this.Reclutador.clear();
                stop();
                this.btnCancelar.setEnabled(true);
                conErrores = true;
                System.err.println("Error: " + ex.getMessage());
            } finally {
                this.cantidadHuellas++;
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY: {
                        stop();
                        setTemplate(Reclutador.getTemplate());
                    }
                    break;
                    case TEMPLATE_STATUS_FAILED: {
                        Reclutador.clear();
                        stop();
                        setTemplate(null);
                        start();
                    }
                    break;
                }
            }
        }
        if (this.tipoProceso == 2) {
            // this.setBuscarEstudiante(sample);
        } else if (this.tipoProceso == 3) {
            if (!conErrores) {
                // this.setRegistraInasistencia();
            }
        } else if (this.tipoProceso == 4) {
            // this.setRegistrarSalida();
        } else if (this.cantidadHuellas == 4) {
            String ruta = "audios/";
            try {
                this.usuarioIncorrecto = new FileInputStream(ruta + "huella.mp3");
                this.usuarioIncorrectoCache = new BufferedInputStream(this.usuarioIncorrecto);
                this.playUsuarioIncorrecto = new Player(this.usuarioIncorrectoCache);
                this.playUsuarioIncorrecto.play();
                this.playUsuarioIncorrecto.close();
            } catch (FileNotFoundException | JavaLayerException ex) {
                this.btnCancelar.setEnabled(true);
                System.err.println(ex.getMessage() + " Error en los ingresos");
                Logger.getLogger(frmHuella.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.btnGuardar.setEnabled(true);
        }
        System.gc();
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public boolean isCambia() {
        return cambia;
    }

    public void setCambia(boolean cambia) {
        this.cambia = cambia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIndiceDerecho = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblEstudiante = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                getCerrarPanel(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                getCerrado(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                getAbrirPanel(evt);
            }
        });

        lblIndiceDerecho.setText("jLabel1");
        lblIndiceDerecho.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Código:");

        jLabel3.setText("Estudiante:");

        lblEstudiante.setText("jLabel2");
        lblEstudiante.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(0, 121, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar))
                            .addComponent(lblIndiceDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIndiceDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Reclutador.clear();
        stop();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void getAbrirPanel(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_getAbrirPanel
        Iniciar();
        start();
    }//GEN-LAST:event_getAbrirPanel

    private void getCerrarPanel(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_getCerrarPanel
        //Reclutador.clear();
        //stop();
    }//GEN-LAST:event_getCerrarPanel

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //start();
        Reclutador.clear();
        stop();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void getCerrado(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_getCerrado
        //Reclutador.clear();
        //stop();
    }//GEN-LAST:event_getCerrado

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEstudiante;
    private javax.swing.JLabel lblIndiceDerecho;
    // End of variables declaration//GEN-END:variables
}
