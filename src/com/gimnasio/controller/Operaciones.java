package com.gimnasio.controller;

import com.gimnasio.model.*;
import com.gimnasio.model.enums.EEstadoCivil;
import com.gimnasio.model.enums.ETipoDocumento;
import com.gimnasio.util.Util;
import com.gimnasio.views.frmPrincipal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author emimaster16
 */
public class Operaciones {

    private Model model;
    private Conexion conexion;

    public Operaciones() {

        this.conexion = new Conexion();
        this.conexion.connect();
        this.model = new Model();
        this.model.setConexion(this.conexion);
    }

    /**
     *
     * @param clienteDto
     * @return
     * @throws SQLException
     */
    public List<String> setGuardarCliente(ClienteDto clienteDto) throws SQLException {
        List<String> listMessages = new ArrayList();
        if (Util.getVacio(clienteDto.getPersonaDto().getPrimerNombre())) {
            listMessages.add("<li>Primer nombre</li>");
        }
        if (Util.getVacio(clienteDto.getPersonaDto().getPrimerApellido())) {
            listMessages.add("<li>Primer appelido</li>");
        }
        if (Util.getVacio(String.valueOf(clienteDto.getPersonaDto().getTipoIdentificacion()))) {
            listMessages.add("<li>Tipo documento</li>");
        }
        if (Util.getVacio(String.valueOf(clienteDto.getPersonaDto().getNumeroIdentificacion()))) {
            listMessages.add("<li>Número de documento</li>");
        }
        if (Util.getVacio(String.valueOf(clienteDto.getPersonaDto().getEstadoCivil()))) {
            listMessages.add("<li>Estado civil</li>");
        }
        if (clienteDto.getPersonaDto().getFechaNacimiento() == null) {
            listMessages.add("<li>La fecha de nacimiento</li>");
        }
        if (Util.getVacio(clienteDto.getPersonaDto().getMovil())) {
            listMessages.add("<li>Número móvil</li>");
        }
        if (Util.getVacio(clienteDto.getPersonaDto().getDireccion())) {
            listMessages.add("<li>Dirección domicilio</li>");
        }
        if (Util.getVacio(clienteDto.getPersonaDto().getBarrio())) {
            listMessages.add("<li>Barrio domicilio</li>");
        }
        if (listMessages.size() < 1) {
            this.model.setGuardarPaquete(null);
        }
        return listMessages;
    }

    public List<ComboDto> getTipoDocumentos() throws Exception {
        List<ComboDto> lista = new ArrayList();
        for (ETipoDocumento tip : ETipoDocumento.getValues()) {
            ComboDto dto = new ComboDto(String.valueOf(tip.getId()), tip.getNombre());
            lista.add(dto);
        }
        return lista;
    }

    public List<ComboDto> getEstadosCiviles() throws Exception {
        List<ComboDto> lista = new ArrayList();
        for (EEstadoCivil tip : EEstadoCivil.getValues()) {
            ComboDto dto = new ComboDto(String.valueOf(tip.getId()), tip.getNombre());
            lista.add(dto);
        }
        return lista;
    }

    /**
     *
     * @param paquete
     * @return
     * @throws SQLException
     */
    public boolean setGuardarPaquete(PaqueteDto paquete) throws SQLException {
        boolean guarda = false;
        if (Util.getVacio(paquete.getNombre())) {
            JOptionPane.showMessageDialog(null, "El campo para el nombre del paquete es obligatorio");
        } else if (Util.getVacio(String.valueOf(paquete.getPrecioBase()))) {
            JOptionPane.showMessageDialog(null, "El campo para el precio del paquete es obligatorio");
        } else if (Util.getVacio(String.valueOf(paquete.getDiasAplazamiento()))) {
            JOptionPane.showMessageDialog(null, "El campo para los días de aplazamiento del paquete es obligatorio");
        } else {
            guarda = this.model.setGuardarPaquete(paquete);
        }
        return guarda;
    }

    /**
     * @throws java.sql.SQLException
     * @tutorial valida el ingreso de los usuarios a ala plataforma
     * @param loggin
     * @param password
     * @return
     */
    public Boolean setValidateIngreso(String loggin, String password) throws SQLException {
        Boolean valido = false;
        List<UsuarioDto> listUsuarios = this.model.getDatosUsuarios(loggin);
        if (listUsuarios.size() > 0) {
            UsuarioDto user = listUsuarios.get(0);
            if (Util.getEncriptarMD5(password).equals(user.getPassword())) {
                valido = true;
                frmPrincipal principal = new frmPrincipal();
                //principal.setUsuarioSesion
                principal.setTitle("Titulo");
                principal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valido;
    }

    /**
     *
     * @param idPaquete
     * @return
     * @throws SQLException
     */
    public List<TablaDto> getPaquetesDatosTablaDto(String idPaquete) throws SQLException {
        List<TablaDto> listTable = new ArrayList();
        List<PaqueteDto> listPaquetes = this.model.getDatosPaquetes(idPaquete);
        for (PaqueteDto paquete : listPaquetes) {
            TablaDto tabla = new TablaDto(
                    String.valueOf(paquete.getId()),
                    Util.getQuitaNULL(paquete.getNombre()),
                    String.valueOf(paquete.getPrecioBase()),
                    String.valueOf(paquete.getYnTiquetera()),
                    String.valueOf(paquete.getDiasAplazamiento()));
            listTable.add(tabla);
        }
        return listTable;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

}
