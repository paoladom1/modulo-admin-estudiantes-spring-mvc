package com.uca.capas.domain;

import javax.persistence.*;

@Entity
@Table( schema = "public", name = "departamento")
public class Departamento {
    @Id
    @Column(name = "idDepartamento")
    private Integer idDepartamento;

    @Column(name = "nombreDepartamento")
    private String nombreDepartamento;

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}
