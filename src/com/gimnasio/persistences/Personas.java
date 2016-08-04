package com.gimnasio.persistences;
// Generated ago 3, 2016 8:37:57 p.m. by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Personas generated by hbm2java
 */
public class Personas  implements java.io.Serializable {


     private long id;
     private String primerNombre;
     private String segundoNombre;
     private String primerApellido;
     private String segundoApellido;
     private short tipoIdentificacion;
     private String numeroIdentificacion;
     private String lugaExpedicion;
     private short genero;
     private Short estadoCivil;
     private Date fechaNacimiento;
     private String direccion;
     private String barrio;
     private String telefono;
     private String movil;
     private String email;
     private String fotoPerfil;
     private Date fechaRegistro;
     private Date fechaModificacion;
     private Set clienteses = new HashSet(0);
     private Set usuarioses = new HashSet(0);

    public Personas() {
    }

	
    public Personas(long id, String primerNombre, String primerApellido, short tipoIdentificacion, String numeroIdentificacion, short genero, Date fechaNacimiento, String movil, String email, String fotoPerfil, Date fechaRegistro, Date fechaModificacion) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.movil = movil;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }
    public Personas(long id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, short tipoIdentificacion, String numeroIdentificacion, String lugaExpedicion, short genero, Short estadoCivil, Date fechaNacimiento, String direccion, String barrio, String telefono, String movil, String email, String fotoPerfil, Date fechaRegistro, Date fechaModificacion, Set clienteses, Set usuarioses) {
       this.id = id;
       this.primerNombre = primerNombre;
       this.segundoNombre = segundoNombre;
       this.primerApellido = primerApellido;
       this.segundoApellido = segundoApellido;
       this.tipoIdentificacion = tipoIdentificacion;
       this.numeroIdentificacion = numeroIdentificacion;
       this.lugaExpedicion = lugaExpedicion;
       this.genero = genero;
       this.estadoCivil = estadoCivil;
       this.fechaNacimiento = fechaNacimiento;
       this.direccion = direccion;
       this.barrio = barrio;
       this.telefono = telefono;
       this.movil = movil;
       this.email = email;
       this.fotoPerfil = fotoPerfil;
       this.fechaRegistro = fechaRegistro;
       this.fechaModificacion = fechaModificacion;
       this.clienteses = clienteses;
       this.usuarioses = usuarioses;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getPrimerNombre() {
        return this.primerNombre;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    public String getSegundoNombre() {
        return this.segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    public String getPrimerApellido() {
        return this.primerApellido;
    }
    
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public String getSegundoApellido() {
        return this.segundoApellido;
    }
    
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public short getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }
    
    public void setTipoIdentificacion(short tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public String getNumeroIdentificacion() {
        return this.numeroIdentificacion;
    }
    
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public String getLugaExpedicion() {
        return this.lugaExpedicion;
    }
    
    public void setLugaExpedicion(String lugaExpedicion) {
        this.lugaExpedicion = lugaExpedicion;
    }
    public short getGenero() {
        return this.genero;
    }
    
    public void setGenero(short genero) {
        this.genero = genero;
    }
    public Short getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public void setEstadoCivil(Short estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getBarrio() {
        return this.barrio;
    }
    
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getMovil() {
        return this.movil;
    }
    
    public void setMovil(String movil) {
        this.movil = movil;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFotoPerfil() {
        return this.fotoPerfil;
    }
    
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Set getClienteses() {
        return this.clienteses;
    }
    
    public void setClienteses(Set clienteses) {
        this.clienteses = clienteses;
    }
    public Set getUsuarioses() {
        return this.usuarioses;
    }
    
    public void setUsuarioses(Set usuarioses) {
        this.usuarioses = usuarioses;
    }




}

