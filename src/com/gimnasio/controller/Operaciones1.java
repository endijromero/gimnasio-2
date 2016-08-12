package com.gimnasio.controller;

import com.gimnasio.model.*;
import com.gimnasio.model.enums.EGenero;
import com.gimnasio.util.Util;
import com.gimnasio.views.frmPrincipal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * 
     * @param idDescuento
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * 
     * @param idProducto
     * @param idUsuario
     * @param cantidad
     * @param valor_total
     * @return 
     */
    public boolean setSaveUpdateCafeteria(int idProducto, long idUsuario, String cantidad, String valor_total){        
        boolean guarda = false;
        try {
            if(idProducto > 0 && idUsuario > 0 && !cantidad.equals("") && !valor_total.equals("")) {            
                java.util.Date fecha = new Date();
                guarda = this.model.setGuardarCafeteria(idProducto, idUsuario, cantidad, valor_total, fecha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guarda;
    }
    
    
    /**
     * 
     * @param fisioterapia
     * @return 
     */
    public List<String> setGuardarFisioterapia(FisioterapiaDto fisioterapia) {
        List<String> listMessages = new ArrayList();
        if(fisioterapia.getTest_mmss()> 0){} else {
            listMessages.add("<li>TEST DE MMSS: Número de repeticiones</li>");
        }
        if(fisioterapia.getTest_mmii()> 0){} else {
            listMessages.add("<li>TEST DE MMII: Número de repeticiones</li>");
        }
        
        if(fisioterapia.getTest_uno()> 0){} else {
            listMessages.add("<li>TEST DE FLEXIBILIDAD: Test uno</li>");
        }
        if(fisioterapia.getTest_dos()> 0){} else {
            listMessages.add("<li>TEST DE FLEXIBILIDAD: Test dos</li>");
        }
        if(fisioterapia.getTest_tres()> 0){} else {
            listMessages.add("<li>TEST DE FLEXIBILIDAD: Test tres</li>");
        }      
        
        if( fisioterapia.getClienteDto().getPersonaDto().getGenero() == EGenero.FEMENIMO.getId() ) { //HOMBRE
            if(fisioterapia.getTriceps()>0){}else{
                listMessages.add("<li>PORCENTAJE DE GRASA: Triceps</li>");    
            }
            if(fisioterapia.getSiliaco()>0){}else{
                listMessages.add("<li>PORCENTAJE DE GRASA: Siliaco</li>");    
            }
        }
        
        if( fisioterapia.getClienteDto().getPersonaDto().getGenero() == EGenero.MASCULINO.getId() ) { //HOMBRE
            if(fisioterapia.getPectoral()>0){}else{
                listMessages.add("<li>PORCENTAJE DE GRASA: Pectoral</li>");    
            }               
            if(fisioterapia.getAbdomen()>0){}else{
                listMessages.add("<li>PORCENTAJE DE GRASA: Abdomen</li>");    
            }
        }
        if(fisioterapia.getMuslo_ant()>0){}else{
            listMessages.add("<li>PORCENTAJE DE GRASA: Muslo-ant</li>");    
        }
        if(fisioterapia.getPeso()>0){}else{
            listMessages.add("<li>INDICE DE MASA CORPORAL: Peso</li>");
        }
        if(fisioterapia.getPeso()>0){}else{
            listMessages.add("<li>INDICE DE MASA CORPORAL: Talla</li>");
        }
        if(listMessages.size() < 1) {
            
        }
        return listMessages;
    }
    
    /**
     * 
     * @param nombres
     * @param apellidos
     * @param documento
     * @return
     * @throws SQLException 
     */
    public List<TablaDto> getClientesDatosTablaDto(String nombres, String apellidos, String documento) throws SQLException {
        List<TablaDto> listTable = new ArrayList();
        //"Documento", "Nombres", "Apellidos", "Edad", "Genero", "Movil", "Fijo", "Correo"
        List<ClienteDto> listClientes = this.model.getDatosClientes(nombres, apellidos, documento);
        listClientes.stream().map((cliente) -> new TablaDto(
                String.valueOf(cliente.getPersonaDto().getNumeroIdentificacion()),
                Util.getQuitaNULL(cliente.getPersonaDto().getPrimerNombre()+" "+Util.getQuitaNULL(cliente.getPersonaDto().getSegundoNombre())),
                Util.getQuitaNULL(cliente.getPersonaDto().getPrimerApellido()+" "+Util.getQuitaNULL(cliente.getPersonaDto().getSegundoApellido())),
                String.valueOf(cliente.getPersonaDto().getEdad()),
                Util.getQuitaNULL(EGenero.getResult(cliente.getPersonaDto().getGenero()).getNombre()),
                String.valueOf(Util.getQuitaNULL(cliente.getPersonaDto().getMovil())),
                String.valueOf(Util.getQuitaNULL(cliente.getPersonaDto().getTelefono())),
                String.valueOf(Util.getQuitaNULL(cliente.getPersonaDto().getEmail()))
                )).forEach((tabla) -> {
                    listTable.add(tabla);
                });
        return listTable;
    }
    
    
    public FisioterapiaDto getFisioterapiaDto(String documento) throws SQLException{        
        //"Documento", "Nombres", "Apellidos", "Edad", "Genero", "Movil", "Fijo", "Correo"
        FisioterapiaDto fisioterapia;
        fisioterapia = this.model.getFisioterapiaDto(documento);
        return fisioterapia;
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

    private short getGenero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
