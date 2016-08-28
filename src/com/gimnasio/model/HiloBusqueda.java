package com.gimnasio.model;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import com.gimnasio.controller.Operaciones;
import com.gimnasio.views.frmClientesIngresos;
import com.gimnasio.views.frmHuella;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

/**
 *
 * @author Administrador
 */
public class HiloBusqueda extends Thread {

    private frmClientesIngresos frmClienteIngreso;
    private DPFPFeatureSet featuresverificacion;
    private List<ClienteDto> listPlantillas;
    private DPFPVerification Verificador;
    private List<HiloBusqueda> listHilos;
    private UsuarioDto usuarioSessionDto;
    private boolean ingresaAsistencia;
    private Operaciones operacion;
    private JLabel lblEstudiante;
    private boolean corriendo;
    private JLabel lblCodigo;
    private boolean continua;
    private frmHuella cerrar;

    public HiloBusqueda(frmHuella cerrar, Operaciones operacion, frmClientesIngresos frmClienteIngreso) {
        this.frmClienteIngreso = frmClienteIngreso;
        this.operacion = operacion;
        this.cerrar = cerrar;
        this.continua = true;
        this.corriendo = true;
    }

    @Override
    public void run() {
        boolean entra = false;
        DPFPVerificationResult result = null;
        DPFPVerification verificator = null;
        try {
            verificator = DPFPGlobal.getVerificationFactory().createVerification();
            verificator.setFARRequested(DPFPVerification.LOW_SECURITY_FAR);
        } catch (Exception e) {
            for (HiloBusqueda h : this.listHilos) {
                if (h != this) {
                    h.setContinua(false);
                }
            }
            System.err.println("Error al iniciar verificador");
            verificator = null;
        }
        for (ClienteDto dto : this.listPlantillas) {
            if (entra || !this.continua) {
                break;
            }
            if (this.continua) {
                try {
                    if (verificator != null && this.featuresverificacion != null && dto != null && dto.getPersonaDto().getTemplateHuella() != null) {
                        result = verificator.verify(featuresverificacion, dto.getPersonaDto().getTemplateHuella());
                        if (result.isVerified()) {
                            System.out.println(result.getFalseAcceptRate());
                            entra = true;
                            if (!this.ingresaAsistencia) {
                                this.lblCodigo.setText(dto.getId().toString());
                                this.lblEstudiante.setText(dto.getPersonaDto().getNombreCompleto());
                            } else if (this.ingresaAsistencia) {
                                for (HiloBusqueda h : this.listHilos) {
                                    if (h != this) {
                                        h.setContinua(false);
                                    }
                                }
                                if (dto.getId() > 0 && !dto.getId().equals("")) {
                                    this.setGuardaAsistencia(dto);
                                }
                                break;
                            }
                        }
                    } else {
                        for (HiloBusqueda h : this.listHilos) {
                            if (h != this) {
                                h.setContinua(false);
                            }
                        }
                        this.continua = false;
                        break;
                    }
                } catch (Exception e) {
                    for (HiloBusqueda h : this.listHilos) {
                        if (h != this) {
                            h.setContinua(false);
                        }
                    }
                    this.continua = false;
                    result = null;
                    System.out.println(e.getMessage() + "Verificacion");
                    break;
                }
            }
            result = null;
        }

        if (!this.ingresaAsistencia) {
            this.corriendo = false;
            if (entra) {
                this.cerrar.setCambia(true);
                JOptionPane.showMessageDialog(this.cerrar, "El estudiante ha sido identificado.", "CORRECTO!", JOptionPane.INFORMATION_MESSAGE);
                this.cerrar.dispose();
            } else if (!this.cerrar.isCambia()) {
                int contador = 0;
                for (HiloBusqueda h : this.listHilos) {
                    if (!h.isCorriendo() && h.isContinua()) {
                        contador++;
                    }
                }
                if (contador == this.listHilos.size()) {
                    this.cerrar.setCambia(true);
                    JOptionPane.showMessageDialog(null, "No se han encontrado huellas.", "CORRECTO!", JOptionPane.INFORMATION_MESSAGE);
                    this.cerrar.dispose();
                }
            }
        } else if (!entra) {
            if (!this.cerrar.isCambia()) {
                this.continua = false;
                int cant = 0;
                for (HiloBusqueda h : this.listHilos) {
                    if (!h.isContinua()) {
                        cant++;
                    }
                }
                if (cant == this.listHilos.size()) {
                    this.cerrar.setCambia(true);
                    // alerta usuario incorrecto
                    this.cerrar.start();
                    this.cerrar.getBtnCancelar().setEnabled(true);
                }
            }
        } else {
            this.cerrar.setCambia(true);
            for (HiloBusqueda h : this.listHilos) {
                h.setContinua(false);
            }
        }
        verificator = null;
        System.gc();
    }

    public void setGuardaAsistencia(ClienteDto clienteDto) {
        try {
            boolean ingresa = false;
            boolean existe = false;
            this.lblCodigo.setText(clienteDto.getId().toString());
            this.lblEstudiante.setText(clienteDto.getPersonaDto().getNombreCompleto());
            List<ClienteIngresoDto> listIngresos = this.operacion.getClientesIngresosDia(clienteDto.getId().toString());
            if (listIngresos.size() < 1) {
                ingresa = true;
                List<TablaDto> listCliente = this.operacion.getClientesDatosTablaDto(null, null, clienteDto.getPersonaDto().getNumeroIdentificacion());
                this.frmClienteIngreso.setAsignarDatoTabla(listCliente.get(0));
                this.operacion.setInsertarIngresoCliente(clienteDto);
            } else {
                existe = true;
            }

            if (existe) {
                // alert error registrado
            } else if (ingresa) {
                // alert correcto
            } else {
                //JOptionPane.showMessageDialog(this,"El estudiante no se ha identificado.","INCORRECTO!",JOptionPane.ERROR_MESSAGE);                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " guarda asistencia ");
        }
        this.cerrar.start();
        this.cerrar.getBtnCancelar().setEnabled(true);
    }

    public DPFPVerification getVerificador() {
        return Verificador;
    }

    public void setVerificador(DPFPVerification Verificador) {
        this.Verificador = Verificador;
    }

    public List<ClienteDto> getListPlantillas() {
        return listPlantillas;
    }

    public void setListPlantillas(List<ClienteDto> listPlantillas) {
        this.listPlantillas = listPlantillas;
    }

    public List<HiloBusqueda> getListHilos() {
        return listHilos;
    }

    public void setListHilos(List<HiloBusqueda> listHilos) {
        this.listHilos = listHilos;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblEstudiante() {
        return lblEstudiante;
    }

    public void setLblEstudiante(JLabel lblEstudiante) {
        this.lblEstudiante = lblEstudiante;
    }

    public DPFPFeatureSet getFeaturesverificacion() {
        return featuresverificacion;
    }

    public void setFeaturesverificacion(DPFPFeatureSet featuresverificacion) {
        this.featuresverificacion = featuresverificacion;
    }

    public boolean isContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public frmHuella getCerrar() {
        return cerrar;
    }

    public void setCerrar(frmHuella cerrar) {
        this.cerrar = cerrar;
    }

    public boolean isCorriendo() {
        return corriendo;
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }

    public Operaciones getOperacion() {
        return operacion;
    }

    public void setOperacion(Operaciones operacion) {
        this.operacion = operacion;
    }

    public frmClientesIngresos getFrmClienteIngreso() {
        return frmClienteIngreso;
    }

    public void setFrmClienteIngreso(frmClientesIngresos frmClienteIngreso) {
        this.frmClienteIngreso = frmClienteIngreso;
    }

    public UsuarioDto getUsuarioSessionDto() {
        return usuarioSessionDto;
    }

    public void setUsuarioSessionDto(UsuarioDto usuarioSessionDto) {
        this.usuarioSessionDto = usuarioSessionDto;
    }

    public boolean isIngresaAsistencia() {
        return ingresaAsistencia;
    }

    public void setIngresaAsistencia(boolean ingresaAsistencia) {
        this.ingresaAsistencia = ingresaAsistencia;
    }

}
