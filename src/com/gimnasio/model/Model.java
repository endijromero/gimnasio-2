package com.gimnasio.model;

import com.gimnasio.model.enums.EEstadoPlan;
import com.gimnasio.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author emimaster16
 */
public class Model {

    private List<Object> listPersist;
    private Conexion conexion;

    public Model() {

    }

    /**
     *
     * @param idCliente
     * @param documento
     * @return
     * @throws java.sql.SQLException
     */
    public ClientePaqueteDto getPaqueteActivoCliente(String idCliente, String documento) throws SQLException {
        ClientePaqueteDto paquete = new ClientePaqueteDto();
        try {
            Statement stat;
            stat = this.conexion.getConexion().createStatement();
            String sql = "SELECT cp.id AS idClientePaquete, cp.* FROM cliente_paquete cp "
                    + " INNER JOIN clientes cl "
                    + " ON cp.cliente_id = cl.id "
                    + " INNER JOIN personas per "
                    + " ON cl.persona_id = per.id "
                    + " INNER JOIN paquetes pqt "
                    + " ON cp.paquete_id = pqt.id "
                    + "WHERE cp.estado=" + EEstadoPlan.ACTIVO.getId() + " ";
            if (!Util.getVacio(idCliente)) {
                sql += " AND cl.id =" + idCliente + " ";
            }
            if (!Util.getVacio(documento)) {
                sql += " AND per.numero_identificacion ='" + documento + "' ";
            }
            sql += "ORDER BY cp.fecha_inicia_paquete DESC ";
            sql += "LIMIT 1";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                paquete.setId(res.getLong("idClientePaquete"));
                paquete.setClienteId(res.getLong("cliente_id"));
                paquete.setPaqueteId(res.getLong("paquete_id"));
                paquete.setDescuentoId(res.getLong("descuento_id"));
                paquete.setValorTotal(res.getDouble("valor_total"));
                paquete.setPrecioBase(res.getDouble("precio_base"));
                paquete.setEstado(res.getShort("estado"));
                paquete.setFechaIniciaPaquete(res.getString("fecha_inicia_paquete"));
                paquete.setFechaFinalizaPaquete(res.getString("fecha_finaliza_paquete"));
            }
            stat.close();
        } catch (SQLException e) {
            this.conexion.rollback();
            throw e;
        } finally {
            this.conexion.commit();
        }
        return paquete;
    }

    public boolean setGuardaPagoPaqueteCliente(ClientePaqueteDto clientePaqueteDto) throws SQLException {
        boolean correcto;
        try {
            String sql = "";
            if (clientePaqueteDto.getId() != null && clientePaqueteDto.getId() > 0) {
                sql = "UPDATE  cliente_paquete  SET "
                        + "cliente_id = '" + clientePaqueteDto.getClienteId() + "', paquete_id = '" + clientePaqueteDto.getPaqueteId() + "', ";
                if (clientePaqueteDto.getDescuentoId() != null) {
                    sql += "descuento_id ='" + (clientePaqueteDto.getDescuentoId() == null ? "" : clientePaqueteDto.getDescuentoId()) + "', ";
                }
                sql += "numero_dias_tiquetera = '" + clientePaqueteDto.getNumeroDiasTiquetera() + "',  "
                        + "precio_base = '" + clientePaqueteDto.getPrecioBase() + "', valor_total = '" + clientePaqueteDto.getValorTotal() + "', "
                        + "estado = '" + clientePaqueteDto.getEstado() + "', fecha_inicia_paquete = '" + clientePaqueteDto.getFechaIniciaPaquete() + "', "
                        + "fecha_finaliza_paquete = '" + clientePaqueteDto.getFechaFinalizaPaquete() + "', usuario_id = '" + clientePaqueteDto.getUsuarioId() + "', "
                        + "fecha_modificacion = NOW() "
                        + "WHERE id = '" + clientePaqueteDto.getId() + "' ;";
            } else {
                sql = "INSERT INTO cliente_paquete "
                        + "(cliente_id, paquete_id, ";
                if (clientePaqueteDto.getDescuentoId() != null) {
                    sql += "descuento_id, ";
                }
                sql += "numero_dias_tiquetera, "
                        + "precio_base, valor_total, "
                        + "estado, fecha_inicia_paquete, "
                        + "fecha_finaliza_paquete, usuario_id, "
                        + "fecha_registro, fecha_modificacion )  "
                        + "VALUES ('" + clientePaqueteDto.getClienteId() + "','" + clientePaqueteDto.getPaqueteId() + "',";
                if (clientePaqueteDto.getDescuentoId() != null) {
                    sql += "'" + (clientePaqueteDto.getDescuentoId() == null ? "" : clientePaqueteDto.getDescuentoId()) + "',";
                }
                sql += "'" + clientePaqueteDto.getNumeroDiasTiquetera() + "',"
                        + "'" + clientePaqueteDto.getPrecioBase() + "',"
                        + "'" + clientePaqueteDto.getValorTotal() + "',"
                        + "'" + clientePaqueteDto.getEstado() + "',"
                        + "'" + clientePaqueteDto.getFechaIniciaPaquete() + "',"
                        + "'" + clientePaqueteDto.getFechaFinalizaPaquete() + "',"
                        + "'" + clientePaqueteDto.getUsuarioId() + "',"
                        + "NOW(), NOW())";

            }
            Statement stat = this.conexion.getConexion().createStatement();
            stat.execute(sql);
            stat.close();
        } catch (SQLException ex) {
            correcto = false;
            this.conexion.rollback();
            throw ex;
        } finally {
            correcto = true;
            this.conexion.commit();
        }
        return correcto;
    }

    /**
     *
     * @param fisioterapia
     * @return
     * @throws java.sql.SQLException
     */
    public boolean getSaveFisioterapia(FisioterapiaDto fisioterapia) throws SQLException {
        PreparedStatement stat;
        boolean correcto;
        try {
            stat = this.conexion.getConexion().prepareStatement("UPDATE clientes SET peso = ?, talla =?, muslo_ant =?, triceps =?, pectoral =?, siliaco =?, abdomen =?, test_mmss =?, test_mmii =?, test_uno=?, test_dos =?, test_tres =?, observaciones =? WHERE id =? ");
            stat.setDouble(1, fisioterapia.getPeso());
            stat.setDouble(2, fisioterapia.getTalla());
            stat.setDouble(3, fisioterapia.getMuslo_ant());
            stat.setDouble(4, fisioterapia.getTriceps());
            stat.setDouble(5, fisioterapia.getPectoral());
            stat.setDouble(6, fisioterapia.getSiliaco());
            stat.setDouble(7, fisioterapia.getAbdomen());
            stat.setDouble(8, fisioterapia.getTest_mmss());
            stat.setDouble(9, fisioterapia.getTest_mmii());
            stat.setDouble(10, fisioterapia.getTest_uno());
            stat.setDouble(11, fisioterapia.getTest_dos());
            stat.setDouble(12, fisioterapia.getTest_tres());
            stat.setString(13, fisioterapia.getObservaciones());
            stat.setLong(14, fisioterapia.getClienteDto().getId());
            stat.execute();
            stat.close();
        } catch (SQLException ex) {
            correcto = false;
            this.conexion.rollback();
            throw ex;
        } finally {
            correcto = true;
            this.conexion.commit();
        }
        return correcto;
    }

    /**
     *
     * @param documento
     * @return
     * @throws java.sql.SQLException
     */
    public FisioterapiaDto getFisioterapiaDto(String documento) throws SQLException {
        FisioterapiaDto fisioterapia = new FisioterapiaDto();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT "
                    + "cl.id as id_cliente, "
                    + "cl.peso, "
                    + "cl.talla, "
                    + "cl.muslo_ant, "
                    + "cl.triceps, "
                    + "cl.pectoral, "
                    + "cl.siliaco, "
                    + "cl.abdomen, "
                    + "cl.test_mmss, "
                    + "cl.test_mmii, "
                    + "cl.test_uno, "
                    + "cl.test_dos, "
                    + "cl.test_tres, "
                    + "cl.observaciones, "
                    + "ps.id, "
                    + "ps.numero_identificacion, "
                    + "ps.primer_nombre, "
                    + "ps.segundo_nombre, "
                    + "ps.primer_apellido, "
                    + "ps.segundo_apellido, "
                    + "ps.genero,"
                    + "ps.fecha_nacimiento"
                    + " FROM clientes cl"
                    + " INNER JOIN personas ps"
                    + " ON cl.persona_id = ps.id "
                    + " WHERE 1=1";
            if (!Util.getVacio(documento)) {
                sql += " AND ps.numero_identificacion LIKE '%" + documento + "%' ";
            }
            sql += " ORDER BY ps.id ASC ";
            ResultSet res = stat.executeQuery(sql);
            ClienteDto dto = new ClienteDto();
            PersonaDto persona = new PersonaDto();
            while (res.next()) {
                fisioterapia.setPeso(res.getDouble("peso"));
                fisioterapia.setTalla(res.getDouble("talla"));
                fisioterapia.setMuslo_ant(res.getDouble("muslo_ant"));
                fisioterapia.setTriceps(res.getDouble("triceps"));
                fisioterapia.setPectoral(res.getDouble("pectoral"));
                fisioterapia.setSiliaco(res.getDouble("siliaco"));
                fisioterapia.setAbdomen(res.getDouble("abdomen"));
                fisioterapia.setTest_uno(res.getDouble("test_uno"));
                fisioterapia.setTest_dos(res.getDouble("test_dos"));
                fisioterapia.setTest_tres(res.getDouble("test_tres"));
                fisioterapia.setTest_mmii(res.getDouble("test_mmii"));
                fisioterapia.setTest_mmss(res.getDouble("test_mmss"));
                fisioterapia.setObservaciones(res.getString("observaciones"));
                dto.setId(res.getLong("id_cliente"));
                persona.setId(res.getLong("id"));
                persona.setPrimerNombre(res.getString("primer_nombre"));
                persona.setSegundoNombre(res.getString("segundo_nombre"));
                persona.setPrimerApellido(res.getString("primer_apellido"));
                persona.setSegundoApellido(res.getString("segundo_apellido"));
                persona.setNumeroIdentificacion(res.getString("numero_identificacion"));
                persona.setFechaNacimiento(res.getString("fecha_nacimiento"));
                persona.setGenero(res.getShort("genero"));
            }
            dto.setPersonaDto(persona);
            fisioterapia.setClienteDto(dto);
        }
        return fisioterapia;
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param documento
     * @param limite
     * @return
     * @throws SQLException
     */
    public List<ClienteDto> getDatosClientes(String nombres, String apellidos, String documento, String limite) throws SQLException {
        List<ClienteDto> list = new ArrayList();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT cl.*, ps.*, cl.id AS idCliente, ps.id AS idPersona "
                    + " FROM clientes cl"
                    + " INNER JOIN personas ps"
                    + " ON cl.persona_id = ps.id "
                    + " WHERE 1 ";
            if (!Util.getVacio(nombres)) {
                sql += " AND UPPER(CONCAT(ps.primer_nombre,' ',COALESCE(ps.segundo_nombre,''))) LIKE '%" + nombres.toUpperCase() + "%' ";
            }
            if (!Util.getVacio(apellidos)) {
                sql += " AND UPPER(CONCAT(ps.primer_apellido,' ',COALESCE(ps.segundo_apellido,''))) LIKE '%" + apellidos.toUpperCase() + "%' ";
            }
            if (!Util.getVacio(documento)) {
                sql += " AND ps.numero_identificacion LIKE '%" + documento + "%' ";
            }
            sql += " ORDER BY ps.fecha_registro DESC, ps.primer_nombre, ps.segundo_nombre, ps.primer_apellido,ps.segundo_apellido ";
            if (limite != null && !limite.toLowerCase().equals("todos")) {
                sql += "LIMIT " + limite;
            }
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                ClienteDto dto = new ClienteDto();
                PersonaDto persona = new PersonaDto();
                dto.setId(res.getLong("idCliente"));
                persona.setId(res.getLong("idPersona"));
                persona.setPrimerNombre(res.getString("primer_nombre"));
                persona.setSegundoNombre(res.getString("segundo_nombre"));
                persona.setPrimerApellido(res.getString("primer_apellido"));
                persona.setSegundoApellido(res.getString("segundo_apellido"));
                persona.setNumeroIdentificacion(res.getString("numero_identificacion"));
                persona.setFechaNacimiento(res.getString("fecha_nacimiento"));
                persona.setGenero(res.getShort("genero"));
                persona.setMovil(res.getString("movil"));
                persona.setTelefono(res.getString("telefono"));
                persona.setEmail(res.getString("email"));
                dto.setPersonaDto(persona);
                list.add(dto);
            }
        }
        return list;
    }

    /**
     *
     * @param idProducto
     * @param idUsuario
     * @param cantidad
     * @param valor_total
     * @param fecha
     * @return
     * @throws SQLException
     */
    public boolean setGuardarCafeteria(int idProducto, long idUsuario, String cantidad, String valor_total, Date fecha) throws SQLException {
        boolean correcto = false;
        try {
            PreparedStatement stat;
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO producto_ventas (productos_id, cantidad, valor_total, fecha_registro, usuario_id) VALUES (?,?,?,?,?)");
            stat.setInt(1, idProducto);
            stat.setDouble(2, Double.valueOf(cantidad));
            stat.setDouble(3, Double.valueOf(valor_total));
            stat.setTimestamp(4, new java.sql.Timestamp(fecha.getTime()));
            stat.setInt(5, (int) idUsuario);
            correcto = stat.execute();
            stat.close();
        } catch (SQLException ex) {
            correcto = false;
            this.conexion.rollback();
            throw ex;
        } finally {
            correcto = true;
            this.conexion.commit();
        }
        return correcto;
    }

    /**
     *
     * @param idDescuentos
     * @return
     * @throws SQLException
     */
    public List<DescuentoDto> getDatosDescuentos(String idDescuentos) throws SQLException {
        List<DescuentoDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT * FROM descuentos WHERE 1";
        if (!Util.getVacio(idDescuentos)) {
            sql += " AND id=" + idDescuentos;

        }
        sql += " ORDER BY id ASC ";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            DescuentoDto dto = new DescuentoDto();
            dto.setId(res.getInt("id"));
            dto.setNombre(res.getString("nombre"));
            dto.setPorcentaje(res.getBigDecimal("porcentaje"));
            list.add(dto);
        }

        return list;
    }

    /**
     *
     * @param descuento
     * @return
     * @throws SQLException
     */
    public boolean setGuardarDescuento(DescuentoDto descuento) throws SQLException {
        PreparedStatement stat;
        try {
            if (descuento.getId() != null && descuento.getId() > 0) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE descuentos SET nombre = ?, porcentaje = ? WHERE id=?");
                stat.setInt(3, descuento.getId());
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO descuentos (nombre, porcentaje) VALUES (?,?)");
            }
            stat.setString(1, descuento.getNombre());
            stat.setDouble(2, descuento.getPorcentaje().doubleValue());
            stat.execute();
            stat.close();
        } catch (SQLException ex) {
            this.conexion.rollback();
            throw ex;
        } finally {
            this.conexion.commit();
        }
        return true;
    }

    /**
     *
     * @param producto
     * @return
     * @throws SQLException
     */
    public boolean setGuardarProducto(ProductoDto producto) throws SQLException {
        PreparedStatement stat;
        try {
            if (producto.getId() != null && producto.getId() > 0) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE productos SET nombre = ?, precio = ? WHERE id=?");
                stat.setInt(3, producto.getId());
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO productos (nombre, precio) VALUES (?,?)");
            }
            stat.setString(1, producto.getNombre());
            stat.setDouble(2, producto.getPrecio());
            stat.execute();
            stat.close();
        } catch (SQLException ex) {
            this.conexion.rollback();
            throw ex;
        } finally {
            this.conexion.commit();
        }
        return true;
    }

    /**
     *
     * @param idProducto
     * @return
     * @throws SQLException
     */
    public List<ProductoDto> getDatosProductos(String idProducto) throws SQLException {
        List<ProductoDto> list = new ArrayList();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT * FROM productos WHERE 1";
            if (!Util.getVacio(idProducto)) {
                sql += " AND id=" + idProducto;
            }
            sql += " ORDER BY id ASC ";
            ResultSet res = stat.executeQuery(sql);
            ProductoDto dto;
            while (res.next()) {
                dto = new ProductoDto();
                dto.setId(res.getInt("id"));
                dto.setNombre(res.getString("nombre"));
                dto.setPrecio(res.getDouble("precio"));
                list.add(dto);
            }
        }
        return list;
    }

    /**
     *
     * @param idCliente
     * @param numeroDocuemnto
     * @return
     * @throws SQLException
     */
    public List<ClienteDto> getClienteDatos(String idCliente, String numeroDocuemnto) throws SQLException {
        List<ClienteDto> list = new ArrayList();
        Statement stat;
        stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT cli.*, per.* FROM clientes cli INNER JOIN personas per ON cli.persona_id=per.id WHERE 1 ";
        if (!Util.getVacio(idCliente)) {
            sql += " AND cli.id=" + idCliente;
        }
        if (!Util.getVacio(numeroDocuemnto)) {
            sql += " AND per.numero_identificacion='" + numeroDocuemnto + "'";
        }
        sql += " ORDER BY cli.id ASC ";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            ClienteDto dto = new ClienteDto();
            dto.setId(res.getLong("id"));
            dto.getPersonaDto().setId(res.getLong("persona_id"));
            dto.getPersonaDto().setPrimerNombre(res.getString("primer_nombre"));
            dto.getPersonaDto().setSegundoNombre(res.getString("segundo_nombre"));
            dto.getPersonaDto().setPrimerApellido(res.getString("primer_apellido"));
            dto.getPersonaDto().setSegundoApellido(res.getString("segundo_apellido"));
            dto.getPersonaDto().setTipoIdentificacion(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setNumeroIdentificacion(res.getString("numero_identificacion"));
            dto.getPersonaDto().setGenero(res.getShort("genero"));
            dto.getPersonaDto().setFechaNacimiento(res.getString("fecha_nacimiento"));
            dto.getPersonaDto().setDireccion(res.getString("direccion"));
            dto.getPersonaDto().setBarrio(res.getString("barrio"));
            dto.getPersonaDto().setTelefono(res.getString("telefono"));
            dto.getPersonaDto().setMovil(res.getString("movil"));
            dto.getPersonaDto().setEmail(res.getString("email"));
            dto.getPersonaDto().setHuellaDactilar(res.getBytes("huella_dactilar"));
            dto.getPersonaDto().setFotoPerfil(res.getString("foto_perfil"));
            dto.getPersonaDto().setFechaRegistro(res.getString("fecha_registro"));
            dto.getPersonaDto().setFechaModificacion(res.getString("fecha_modificacion"));
            list.add(dto);
        }
        stat.close();
        return list;
    }

    /**
     *
     * @tutorial Method Description: valida que la informacion este correcta
     * @author Eminson Mendoza ~~ emimaster16@gmail.com
     * @date 08/07/2016
     * @param clienteDto
     * @throws java.sql.SQLException
     */
    public void setGuardarCliente(ClienteDto clienteDto) throws SQLException {
        PreparedStatement stat;
        ResultSet res;
        boolean correcto = false;
        try {
            if (clienteDto.getId() != null && !Util.getVacio(String.valueOf(clienteDto.getId()))) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE personas SET primer_nombre = ?, segundo_nombre = ?, "
                        + "primer_apellido = ?, segundo_apellido = ?, "
                        + "tipo_identificacion = ?, numero_identificacion = ?, "
                        + "genero = ?, fecha_nacimiento = ?, "
                        + "direccion = ?, barrio = ?, "
                        + "telefono = ?, movil = ?, "
                        + "email = ?, huella_dactilar = ?, "
                        + "foto_perfil = ?,fecha_modificacion=NOW() "
                        + "WHERE id=? ");
                stat.setString(1, clienteDto.getPersonaDto().getPrimerNombre());
                stat.setString(2, clienteDto.getPersonaDto().getSegundoNombre());
                stat.setString(3, clienteDto.getPersonaDto().getPrimerApellido());
                stat.setString(4, clienteDto.getPersonaDto().getSegundoApellido());
                stat.setShort(5, clienteDto.getPersonaDto().getTipoIdentificacion());
                stat.setString(6, clienteDto.getPersonaDto().getNumeroIdentificacion());
                stat.setShort(7, clienteDto.getPersonaDto().getGenero());
                stat.setString(8, clienteDto.getPersonaDto().getFechaNacimiento());
                stat.setString(9, clienteDto.getPersonaDto().getDireccion());
                stat.setString(10, clienteDto.getPersonaDto().getBarrio());
                stat.setString(11, clienteDto.getPersonaDto().getTelefono());
                stat.setString(12, clienteDto.getPersonaDto().getMovil());
                stat.setString(13, clienteDto.getPersonaDto().getEmail());
                stat.setBytes(14, clienteDto.getPersonaDto().getHuellaDactilar());
                stat.setString(15, clienteDto.getPersonaDto().getFotoPerfil());
                stat.setLong(16, clienteDto.getPersonaDto().getId());
                correcto = stat.executeUpdate() > 0;
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO personas (primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, "
                        + "tipo_identificacion, numero_identificacion, "
                        + "genero, fecha_nacimiento, "
                        + "direccion, barrio, "
                        + "telefono, movil, "
                        + "email, huella_dactilar, "
                        + "foto_perfil, fecha_registro, "
                        + "fecha_modificacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())", Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, clienteDto.getPersonaDto().getPrimerNombre());
                stat.setString(2, clienteDto.getPersonaDto().getSegundoNombre());
                stat.setString(3, clienteDto.getPersonaDto().getPrimerApellido());
                stat.setString(4, clienteDto.getPersonaDto().getSegundoApellido());
                stat.setShort(5, clienteDto.getPersonaDto().getTipoIdentificacion());
                stat.setString(6, clienteDto.getPersonaDto().getNumeroIdentificacion());
                stat.setShort(7, clienteDto.getPersonaDto().getGenero());
                stat.setString(8, clienteDto.getPersonaDto().getFechaNacimiento());
                stat.setString(9, clienteDto.getPersonaDto().getDireccion());
                stat.setString(10, clienteDto.getPersonaDto().getBarrio());
                stat.setString(11, clienteDto.getPersonaDto().getTelefono());
                stat.setString(12, clienteDto.getPersonaDto().getMovil());
                stat.setString(13, clienteDto.getPersonaDto().getEmail());
                stat.setBytes(14, clienteDto.getPersonaDto().getHuellaDactilar());
                stat.setString(15, clienteDto.getPersonaDto().getFotoPerfil());
                if (stat.executeUpdate() > 0) {
                    res = stat.getGeneratedKeys();
                    if (res.next()) {
                        correcto = true;
                        clienteDto.getPersonaDto().setId(res.getLong(1));
                    }
                }
            }
            if (correcto) {
                if (clienteDto.getId() != null && !Util.getVacio(String.valueOf(clienteDto.getId()))) {
                    stat = this.conexion.getConexion().prepareStatement("UPDATE  clientes SET persona_id = ? WHERE id = ?");
                    stat.setLong(1, clienteDto.getPersonaDto().getId());
                    stat.setLong(2, clienteDto.getId());
                    stat.executeUpdate();
                } else {
                    stat = this.conexion.getConexion().prepareStatement("INSERT INTO clientes(persona_id) " + "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    stat.setLong(1, clienteDto.getPersonaDto().getId());
                    if (stat.executeUpdate() > 0) {
                        res = stat.getGeneratedKeys();
                        if (res.next()) {
                            correcto = true;
                            clienteDto.setId(res.getLong(1));
                        }
                    }
                }
            }
            stat.close();
        } catch (SQLException ex) {
            this.conexion.rollback();
            throw ex;
        } finally {
            this.conexion.commit();
        }
    }

    public void setGuardarUsuario(UsuarioDto usuarioDto) throws SQLException {
    }

    public boolean setGuardarPaquete(PaqueteDto paquete) throws SQLException {
        boolean correcto = false;
        try {
            PreparedStatement stat;
            if (paquete.getId() > 0) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE paquetes SET nombre = ?, tipo = ?, precio_base = ?, yn_tiquetera = ?, dias_aplazamiento = ? WHERE id=?");
                stat.setInt(6, paquete.getId());
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO paquetes (nombre, tipo, precio_base, yn_tiquetera, dias_aplazamiento) VALUES (?,?,?,?,?)");
            }
            stat.setString(1, paquete.getNombre());
            stat.setShort(2, paquete.getTipo());
            stat.setDouble(3, paquete.getPrecioBase());
            stat.setShort(4, paquete.getYnTiquetera());
            stat.setShort(5, paquete.getDiasAplazamiento());
            correcto = stat.execute();
            stat.close();
        } catch (SQLException ex) {
            correcto = false;
            this.conexion.rollback();
            throw ex;
        } finally {
            correcto = true;
            this.conexion.commit();
        }
        return correcto;
    }

    public List<UsuarioDto> getUsuariosDatos(String loggin) throws SQLException {
        List<UsuarioDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT usu.*, per.*, per.id as idPersona FROM usuarios usu INNER JOIN personas per ON usu.persona_id=per.id WHERE loggin='" + loggin + "'";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            UsuarioDto dto = new UsuarioDto();
            dto.setId(res.getLong("id"));
            dto.setLoggin(res.getString("loggin"));
            dto.setPassword(res.getString("password"));
            dto.setPersonaId(res.getLong("persona_id"));
            dto.getPersonaDto().setId(res.getLong("idPersona"));
            dto.getPersonaDto().setPrimerNombre(res.getString("primer_nombre"));
            dto.getPersonaDto().setSegundoNombre(res.getString("segundo_nombre"));
            dto.getPersonaDto().setPrimerApellido(res.getString("primer_apellido"));
            dto.getPersonaDto().setSegundoApellido(res.getString("segundo_apellido"));
            dto.getPersonaDto().setTipoIdentificacion(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setNumeroIdentificacion(res.getString("tipo_identificacion"));
            dto.getPersonaDto().setGenero(res.getShort("genero"));
            dto.getPersonaDto().setGenero(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setFechaNacimiento(res.getString("fecha_nacimiento"));
            dto.getPersonaDto().setDireccion(res.getString("direccion"));
            dto.getPersonaDto().setBarrio(res.getString("barrio"));
            dto.getPersonaDto().setTelefono(res.getString("telefono"));
            dto.getPersonaDto().setMovil(res.getString("movil"));
            dto.getPersonaDto().setFotoPerfil(res.getString("foto_perfil"));
            dto.getPersonaDto().setFechaRegistro(res.getString("fecha_registro"));
            dto.getPersonaDto().setFechaModificacion(res.getString("fecha_modificacion"));
            list.add(dto);
        }
        stat.close();
        return list;
    }

    /**
     *
     * @param idPaquete
     * @return
     * @throws SQLException
     */
    public List<PaqueteDto> getPaquetesDatos(String idPaquete) throws SQLException {
        List<PaqueteDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT * FROM paquetes WHERE 1";
        if (!Util.getVacio(idPaquete)) {
            sql += " AND id=" + idPaquete;

        }
        sql += " ORDER BY id ASC ";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            PaqueteDto dto = new PaqueteDto();
            dto.setId(res.getInt("id"));
            dto.setNombre(res.getString("nombre"));
            dto.setTipo(res.getShort("tipo"));
            dto.setPrecioBase(res.getDouble("precio_base"));
            dto.setYnTiquetera(res.getShort("yn_tiquetera"));
            dto.setDiasAplazamiento(res.getShort("dias_aplazamiento"));
            list.add(dto);
        }
        stat.close();
        return list;
    }

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

}
