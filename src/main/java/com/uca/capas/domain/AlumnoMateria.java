package com.uca.capas.domain;

import javax.persistence.*;

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
    private Double nota;

    public AlumnoMateria(Alumno alumno, Materia materia, Double nota) {
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

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


    public String delegateResultado(){
        if(this.nota >=6.0){
            return "aprobada";
        }
        return "reprobada";
    }


}
