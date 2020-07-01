package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;


public class AlumnoMateriaKey implements Serializable {

    private static final long serialVersionUID = 1l;

    @Column(name = "codigoEstudiante")
    private Integer idAlumn;

    @Column(name = "idMateria")
    private Integer idMat;

    public Integer getCodEst() {
        return idAlumn;
    }

    public void setCodEst(Integer codEst) {
        this.idAlumn = codEst;
    }

    public Integer getCodMat() {
        return idMat;
    }

    public void setCodMat(Integer codMat) {
        this.idMat = codMat;
    }

    public AlumnoMateriaKey(Integer codEst, Integer codMat) {
        this.idAlumn = codEst;
        this.idMat = codMat;
    }

    public  AlumnoMateriaKey(){

    }
}
