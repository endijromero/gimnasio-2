package com.gimnasio.controller;

import com.gimnasio.model.*;
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
public class Operaciones1 {

    private Model1 model;
    private Conexion conexion;

    public Operaciones1() {

        this.conexion = new Conexion();
        this.conexion.connect();
        this.model = new Model1();
        this.model.setConexion(this.conexion);
    }
    
    public List<TablaDto> getDescuentosDatosTablaDto(String idDescuento) throws SQLException {
        List<TablaDto> listTable = new ArrayList();
        List<DescuentoDto> listPaquetes = this.model.getDatosDescuentos(idDescuento);
        listPaquetes.stream().map((paquete) -> new TablaDto(
                String.valueOf(paquete.getId()),
                Util.getQuitaNULL(paquete.getNombre()),
                String.valueOf(paquete.getPorcentaje()))).forEach((tabla) -> {
                    listTable.add(tabla);
                });
        return listTable;
    }
    
    /**
     * 
     * @param descuento
     * @return 
     */
    public boolean setSaveUpdateDescuentos(DescuentoDto descuento){
        if(descuento.getId() != null && descuento.getId() > 0 ){
            
        }else {
            
        }
        return true;
    }

    public Model1 getModel() {
        return model;
    }

    public void setModel(Model1 model) {
        this.model = model;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

}
