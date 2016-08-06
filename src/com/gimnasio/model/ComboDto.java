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

    public ComboDto(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return this.descripcion;
    }
}
