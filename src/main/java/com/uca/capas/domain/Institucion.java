package com.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "centroEscolar")
public class Institucion {
    @Id
    @Column(name = "idCentroEscolar")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idInstitucion;

    @Size(max = 30, message = "No debe exceder los 30 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Column(name = "nombreCentroEscolar")
    private String nombreInstitucion;

    @Size(max = 70, message = "No debe exceder los 70 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Size(max = 100, message = "No debe exceder los 100 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Column(name = "direccion")
    private String direccion;

    @OneToOne
    @JoinColumn(name = "idMunicipio")
    private Municipio municipio;

    @OneToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;


    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public String getEstadoDelegate() {
    	if(estado) {
    		return "Activo";
    	}else {
    		return "Inactivo";
    	}
    }
}
