package com.gimnasio.controller;

import com.gimnasio.model.*;
import com.gimnasio.model.enums.EEstadoCivil;
import com.gimnasio.model.enums.ESiNo;
import com.gimnasio.model.enums.ETipoDocumento;
import com.gimnasio.util.Util;
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

    public boolean setValidaDocumentoCliene(String idPersona, String numeroDocuemnto) throws SQLException {
        boolean correcto = true;
        List<ClienteDto> listClientes = this.model.getClienteDatos(null, numeroDocuemnto);
        for (ClienteDto dto : listClientes) {
            if (!idPersona.equals(dto.getPersonaDto().getId()) && numeroDocuemnto.equals(dto.getPersonaDto().getNumeroIdentificacion())) {
                correcto = false;
                break;
            }
        }
        return correcto;
    }

    public List<ClienteDto> getClienteDatos(String idCliente) throws SQLException {
        List<ClienteDto> list = this.model.getClienteDatos(idCliente, null);
        return list;
    }

    /**
     * @tutorial Method Description: consulta los datos del cliente
     * @author Eminson Mendoza ~~ emimaster16@gmail.com
     * @date 09/07/2016
     * @param idCliente
     * @param numeroDocuemnto
     * @throws java.sql.SQLException
     * @return
     */
    public List<ClienteDto> getClienteDatos(String idCliente, String numeroDocuemnto) throws SQLException {
        List<ClienteDto> list = this.model.getClienteDatos(idCliente, numeroDocuemnto);
        return list;
    }

    /**
     * @tutorial Method Description: valida que la informacion este correcta
     * @author Eminson Mendoza ~~ emimaster16@gmail.com
     * @date 08/07/2016
     * @param clienteDto
     * @param guarda
     * @return
     * @throws SQLException
     */
    public List<String> setGuardarCliente(ClienteDto clienteDto, boolean guarda) throws SQLException {
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
        if (Util.getVacio(clienteDto.getPersonaDto().getNumeroIdentificacion())) {
            listMessages.add("<li>Número de documento</li>");
        }
        if (clienteDto.getPersonaDto().getEstadoCivil() == null) {
            listMessages.add("<li>Estado civil</li>");
        }
        if (clienteDto.getPersonaDto().getGenero() == 0) {
            listMessages.add("<li>Género</li>");
        }
        if (Util.getVacio(clienteDto.getPersonaDto().getFechaNacimiento())) {
            listMessages.add("<li>Fecha de nacimiento</li>");
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
        if (clienteDto.getPersonaDto().getHuellaDactilar() == null && guarda) {//HABILITAR CUANDO SE TENGA EL HUELLERO
            // listMessages.add("<li>Huella dactilar</li>");
        }
        if (listMessages.size() < 1 && guarda) {
            this.model.setGuardarCliente(clienteDto);
        }
        return listMessages;
    }

    public List<String> setGuardarUsuario(UsuarioDto usuarioDto) throws SQLException {
        List<String> listMessages = new ArrayList();
        if (Util.getVacio(usuarioDto.getPersonaDto().getPrimerNombre())) {
            listMessages.add("<li>Primer nombre</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getPrimerApellido())) {
            listMessages.add("<li>Primer appelido</li>");
        }
        if (Util.getVacio(String.valueOf(usuarioDto.getPersonaDto().getTipoIdentificacion()))) {
            listMessages.add("<li>Tipo documento</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getNumeroIdentificacion())) {
            listMessages.add("<li>Número de documento</li>");
        }
        if (usuarioDto.getPersonaDto().getEstadoCivil() == null) {
            listMessages.add("<li>Estado civil</li>");
        }
        if (usuarioDto.getPersonaDto().getGenero() == 0) {
            listMessages.add("<li>Género</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getFechaNacimiento())) {
            listMessages.add("<li>Fecha de nacimiento</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getMovil())) {
            listMessages.add("<li>Número móvil</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getDireccion())) {
            listMessages.add("<li>Dirección domicilio</li>");
        }
        if (Util.getVacio(usuarioDto.getPersonaDto().getBarrio())) {
            listMessages.add("<li>Barrio domicilio</li>");
        }
        if (usuarioDto.getPersonaDto().getHuellaDactilar() == null) {
            listMessages.add("<li>Huella dactilar</li>");
        }
        if (listMessages.size() < 1) {
            this.model.setGuardarUsuario(usuarioDto);
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

    public List<ComboDto> getYnActivo() throws Exception {
        List<ComboDto> lista = new ArrayList();
        for (ESiNo tip : ESiNo.getValues()) {
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
    public UsuarioDto setValidateIngreso(String loggin, String password) throws SQLException {
        UsuarioDto user = new UsuarioDto();
        List<UsuarioDto> listUsuarios = this.model.getUsuariosDatos(loggin);
        if (listUsuarios.size() > 0) {
            user = listUsuarios.get(0);
            if (Util.getEncriptarMD5(password).equals(user.getPassword())) {
                return user;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
        return user;
    }

    /**
     *
     * @param idPaquete
     * @return
     * @throws SQLException
     */
    public List<TablaDto> getPaquetesDatosTablaDto(String idPaquete) throws SQLException {
        List<TablaDto> listTable = new ArrayList();
        List<PaqueteDto> listPaquetes = this.model.getPaquetesDatos(idPaquete);
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
