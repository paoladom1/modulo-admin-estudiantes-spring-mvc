package com.uca.capas.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "tipoUsuario")
public class Tipo {
    @Id
    @Column(name = "idTipoUsuario")
    private Integer codigoTipo;

    @Column(name = "tipoUsuario")
    private String tipo;

    public Integer getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(Integer codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
