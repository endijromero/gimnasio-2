package com.gimnasio.model;
// Generated ago 2, 2016 10:46:44 p.m. by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Pagos generated by hbm2java
 */
public class Pagos  implements java.io.Serializable {


     private Long id;
     private ClientePaquete clientePaquete;
     private Usuarios usuarios;
     private BigDecimal valor;
     private Date fechaPago;

    public Pagos() {
    }

    public Pagos(ClientePaquete clientePaquete, Usuarios usuarios, BigDecimal valor, Date fechaPago) {
       this.clientePaquete = clientePaquete;
       this.usuarios = usuarios;
       this.valor = valor;
       this.fechaPago = fechaPago;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public ClientePaquete getClientePaquete() {
        return this.clientePaquete;
    }
    
    public void setClientePaquete(ClientePaquete clientePaquete) {
        this.clientePaquete = clientePaquete;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Date getFechaPago() {
        return this.fechaPago;
    }
    
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }




}

