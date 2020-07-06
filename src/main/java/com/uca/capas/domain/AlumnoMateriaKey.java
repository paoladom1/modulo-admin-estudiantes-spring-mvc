package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;


public class AlumnoMateriaKey implements Serializable {

    private static final long serialVersionUID = 1l;

    @Column(name = "codigoEstudiante")
    private Integer idAlumno;

    @Column(name = "idMateria")
    private Integer idMateria;

    public Integer getCodEst() {
        return idAlumno;
    }

    public void setCodEst(Integer codEst) {
        this.idAlumno = codEst;
    }

    public Integer getCodMat() {
        return idMateria;
    }

    public void setCodMat(Integer codMat) {
        this.idMateria = codMat;
    }

    public AlumnoMateriaKey(Integer codEst, Integer codMat) {
        this.idAlumno = codEst;
        this.idMateria = codMat;
    }

    public  AlumnoMateriaKey(){

    }
}
