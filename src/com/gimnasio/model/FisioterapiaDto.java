/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gimnasio.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    
    private double triceps;
    private double pectoral;
    private double siliaco;
    private double abdomen;
    private double muslo_ant;
    
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

    public double getTriceps() {
        return triceps;
    }

    public void setTriceps(double triceps) {
        this.triceps = triceps;
    }

    public double getPectoral() {
        return pectoral;
    }

    public void setPectoral(double pectoral) {
        this.pectoral = pectoral;
    }

    public double getSiliaco() {
        return siliaco;
    }

    public void setSiliaco(double siliaco) {
        this.siliaco = siliaco;
    }

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public double getMuslo_ant() {
        return muslo_ant;
    }

    public void setMuslo_ant(double muslo_ant) {
        this.muslo_ant = muslo_ant;
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
    
    /**
     * 
     * @return 
     */
    public double setCalculaTesFlexibilidad(){
       double promedio = 0;
       if (this.test_uno > 0 && this.test_dos > 0 && this.test_tres > 0 ) {
           promedio = (this.test_uno + this.test_dos + this.test_tres)/3;
       }
       BigDecimal flex = new BigDecimal(promedio);
       return flex.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    /**
     * 
     * @return 
     */
    public double setCalcularIMC(){
        double calculo = 0;
        if(this.peso > 0 && this.talla > 0) {
            calculo = this.peso/(Math.pow((this.talla/100),2));
        }
        BigDecimal flex = new BigDecimal(calculo);
        return flex.setScale(2, RoundingMode.HALF_UP).doubleValue();
    } 
    
    /**
     * 
     */
    public void getJacksonPollock() {
        double sumatoria = 0;
        double jp = 0;
        double edad = 0;
        //if() { Hombre
            if(this.getPectoral() > 0 && this.getMuslo_ant() >0 && this.getAbdomen() > 0) {
                double a = 1.1093800;
                double b = 0.0008267;
                double c = 0.0000016;
                double d = 0.0002574;
                
                sumatoria = this.getPectoral()+this.getMuslo_ant()+this.getAbdomen();
                
                jp = (a-(b*sumatoria)+(c*Math.pow(a,2))-(d*edad));
            
            }
        //} else if() {//mujer
            if(this.getTriceps()> 0 && this.getMuslo_ant() >0 && this.getSiliaco() > 0) {
                double a = 1.0994921;
                double b = 0.0009929;
                double c = 0.0000023;
                double d = 0.0001392;
                
                jp = (a-(b*sumatoria)+(c*Math.pow(a,2))-(d*edad));
            }                
        //}  
    }
}
