/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.model;

/**
 *
 * @author rodolfo
 */
public class FisioterapiaDto implements java.io.Serializable  {
    private double test_mmss;
    private double test_mmii;
    
    private double test_uno;
    private double test_dos;
    private double test_tres;
    
    private double medida_uno;
    private double medida_dos;
    private double medida_tres;
    private double medida_cuatro;
    private double medida_cinco;
    
    private double talla;
    private double peso;
    
    private String observaciones;
    
    private ClienteDto clienteDto;
    private PersonaDto personaDto;

    /**
     * 
     */
    public FisioterapiaDto() {
        this.clienteDto = new ClienteDto();
        this.personaDto = new PersonaDto();
    }
    
    public double getTest_mmss() {
        return test_mmss;
    }

    public void setTest_mmss(double test_mmss) {
        this.test_mmss = test_mmss;
    }

    public double getTest_mmii() {
        return test_mmii;
    }

    public void setTest_mmii(double test_mmii) {
        this.test_mmii = test_mmii;
    }

    public double getTest_uno() {
        return test_uno;
    }

    public void setTest_uno(double test_uno) {
        this.test_uno = test_uno;
    }

    public double getTest_dos() {
        return test_dos;
    }

    public void setTest_dos(double test_dos) {
        this.test_dos = test_dos;
    }

    public double getTest_tres() {
        return test_tres;
    }

    public void setTest_tres(double test_tres) {
        this.test_tres = test_tres;
    }

    public double getMedida_uno() {
        return medida_uno;
    }

    public void setMedida_uno(double medida_uno) {
        this.medida_uno = medida_uno;
    }

    public double getMedida_dos() {
        return medida_dos;
    }

    public void setMedida_dos(double medida_dos) {
        this.medida_dos = medida_dos;
    }

    public double getMedida_tres() {
        return medida_tres;
    }

    public void setMedida_tres(double medida_tres) {
        this.medida_tres = medida_tres;
    }

    public double getMedida_cuatro() {
        return medida_cuatro;
    }

    public void setMedida_cuatro(double medida_cuatro) {
        this.medida_cuatro = medida_cuatro;
    }

    public double getMedida_cinco() {
        return medida_cinco;
    }

    public void setMedida_cinco(double medida_cinco) {
        this.medida_cinco = medida_cinco;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

    public PersonaDto getPersonaDto() {
        return personaDto;
    }

    public void setPersonaDto(PersonaDto personaDto) {
        this.personaDto = personaDto;
    }
    
    
    
    
}
