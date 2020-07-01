package com.uca.capas.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name ="AlumnoXmateria")
@IdClass(AlumnoMateriaKey.class)
public class AlumnoMateria {

    @Id
    private Integer idAlumn;

    @Id
    private Integer idMat;

    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "codigoEstudiante", insertable = false, updatable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria", insertable=false, updatable=false)
    private Materia materia;

    @Column(name = "notaMateria")
    private Integer nota;

    public AlumnoMateria(Alumno alumno, Materia materia, Integer nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public AlumnoMateria(){

    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
