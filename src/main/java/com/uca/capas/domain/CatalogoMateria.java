package com.uca.capas.domain;

import com.sun.tools.javac.jvm.Gen;
import sun.rmi.rmic.Generator;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name ="catalogomaterias")
public class CatalogoMateria {

    @Id
    @Column(name = "codigomateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCatalogo;

    @Size(message = "El campo no debe tener más de 15 digitos", max = 15)
    @NotEmpty(message = "*Campo Obligatorio*")
    @Column(name = "nombre")
    private String nomMateria;

    @Column(name = "estado")
    private Boolean estadoMateria;


    public CatalogoMateria(Integer codigoCatalogo, String nomMateria, Boolean estadoMateria) {
        this.codigoCatalogo = codigoCatalogo;
        this.nomMateria = nomMateria;
        this.estadoMateria = estadoMateria;
    }
    public CatalogoMateria(){

    }

    public Integer getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(Integer codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public String getNomMateria() {
        return nomMateria;
    }

    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
    }

    public Boolean getEstadoMateria() {
        return estadoMateria;
    }

    public void setEstadoMateria(Boolean estadoMateria) {
        this.estadoMateria = estadoMateria;
    }
}
