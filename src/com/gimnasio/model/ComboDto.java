/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.model;

/**
 *
 * @author user
 */
public class ComboDto {

    private String codigo;
    private String descripcion;
    private String auxiliar;

    public ComboDto(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public ComboDto(String codigo, String descripcion, String auxiliar) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.auxiliar = auxiliar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}
