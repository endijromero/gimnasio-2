package com.gimnasio.model;
// Generated ago 3, 2016 11:34:53 p.m. by Hibernate Tools 4.3.1

/**
 * ClienteDto generated by hbm2java
 */
public class ClienteDto implements java.io.Serializable {

    private Long id;
    private ClientePaqueteDto clientePaqueteDto;
    private PersonaDto personaDto;

    public ClienteDto() {
        this.personaDto = new PersonaDto();
        this.clientePaqueteDto = new ClientePaqueteDto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientePaqueteDto getClientePaqueteDto() {
        return clientePaqueteDto;
    }

    public void setClientePaqueteDto(ClientePaqueteDto clientePaqueteDto) {
        this.clientePaqueteDto = clientePaqueteDto;
    }

    public PersonaDto getPersonaDto() {
        return personaDto;
    }

    public void setPersonaDto(PersonaDto personaDto) {
        this.personaDto = personaDto;
    }

}
