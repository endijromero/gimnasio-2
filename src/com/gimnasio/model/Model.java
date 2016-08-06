package com.gimnasio.model;

import com.gimnasio.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public boolean setGuardarPaquete(PaqueteDto paquete) throws SQLException {
        PreparedStatement stat = null;
        if (paquete.getId() > 0) {
            stat = this.conexion.getConexion().prepareStatement("UPDATE paquetes SET nombre = ?, precio_base = ?, yn_tiquetera = ?, dias_aplazamiento = ? WHERE id=?");
            stat.setInt(5, paquete.getId());
        } else {
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO paquetes (nombre, precio_base, yn_tiquetera, dias_aplazamiento) VALUES (?,?,?,?)");

        }
        stat.setString(1, paquete.getNombre());
        stat.setDouble(2, paquete.getPrecioBase());
        stat.setShort(3, paquete.getYnTiquetera());
        stat.setShort(4, paquete.getDiasAplazamiento());
        stat.execute();
        stat.close();
        return true;
    }

    public List<UsuarioDto> getDatosUsuarios(String loggin) throws SQLException {
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
    public List<PaqueteDto> getDatosPaquetes(String idPaquete) throws SQLException {
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
