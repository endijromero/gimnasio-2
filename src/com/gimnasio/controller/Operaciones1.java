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
        List<DescuentoDto> listDescuentos = this.model.getDatosDescuentos(idDescuento);
        listDescuentos.stream().map((descuento) -> new TablaDto(
                String.valueOf(descuento.getId()),
                Util.getQuitaNULL(descuento.getNombre()),
                String.valueOf(descuento.getPorcentaje()))).forEach((tabla) -> {
                    listTable.add(tabla);
                });
        return listTable;
    }
    
    /**
     * 
     * @param idProducto
     * @return
     * @throws SQLException 
     */
    public List<ProductoDto> getProductosDatosDto(String idProducto) throws SQLException {        
        List<ProductoDto> listProductos = this.model.getDatosProductos(idProducto);        
        return listProductos;
    }
    
    /**
     * 
     * @param idProducto
     * @return
     * @throws SQLException 
     */
    public List<TablaDto> getProductosDatosTablaDto(String idProducto) throws SQLException {
        List<TablaDto> listTable = new ArrayList();
        List<ProductoDto> listProductos = this.getProductosDatosDto(idProducto);
        listProductos.stream().map((producto) -> new TablaDto(
                String.valueOf(producto.getId()),
                Util.getQuitaNULL(producto.getNombre()),
                String.valueOf(producto.getPrecio()))).forEach((tabla) -> {
                    listTable.add(tabla);
                });
        return listTable;
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<ComboDto> getProductosDatosComboDto() throws SQLException {
        List<ComboDto> listTable = new ArrayList();
        List<ProductoDto> listProductos = this.getProductosDatosDto(null);
        listProductos.stream().map((producto) -> new ComboDto(String.valueOf(producto.getId()),
                Util.getQuitaNULL(producto.getNombre()))).forEach((tabla) -> {
            listTable.add(tabla);
                });
        return listTable;        
    }
    
    /**
     * 
     * @param descuento
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean setSaveUpdateDescuentos(DescuentoDto descuento) throws SQLException{
        boolean guarda = false;
        if (Util.getVacio(descuento.getNombre())) {
            JOptionPane.showMessageDialog(null, "El campo para el nombre del descuento es obligatorio");
        } else if (Util.getVacio(String.valueOf(descuento.getPorcentaje()))) {
            JOptionPane.showMessageDialog(null, "El campo para el porcentaje del descuento es obligatorio");
        }  
        else {
            guarda = this.model.setGuardarDescuento(descuento);
        }
        return guarda;
    }
    
    /**
     * 
     * @param producto
     * @return
     * @throws SQLException 
     */
    public boolean setSaveUpdateProductos(ProductoDto producto) throws SQLException{
        boolean guarda = false;
        if (Util.getVacio(producto.getNombre())) {
            JOptionPane.showMessageDialog(null, "El campo para el nombre del descuento es obligatorio");
        } else if (Util.getVacio(String.valueOf(producto.getPrecio()))) {
            JOptionPane.showMessageDialog(null, "El campo para el porcentaje del descuento es obligatorio");
        }  
        else {
            guarda = this.model.setGuardarProducto(producto);
        }
        return guarda;
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
