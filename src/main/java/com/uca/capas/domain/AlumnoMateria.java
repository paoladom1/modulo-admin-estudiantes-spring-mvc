package com.uca.capas.domain;

import sun.rmi.rmic.Generator;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

@Entity
@Table(schema = "public", name ="alumnoxmateria")
public class AlumnoMateria {


    @Id
    @Column(name = "idalumnomateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumnoMateria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idalumno")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmateria")
    private Materia materia;

    @Column(name = "notamateria")
    private Double nota;

    public Integer getIdAlumnoMateria() {
        return idAlumnoMateria;
    }

    public void setIdAlumnoMateria(Integer idAlumnoMateria) {
        this.idAlumnoMateria = idAlumnoMateria;
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

    public AlumnoMateria(Integer idAlumnoMateria, Alumno alumno, Materia materia, Double nota) {
        this.idAlumnoMateria = idAlumnoMateria;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public AlumnoMateria(){

    }

    public String delegateResultado(){
        if(this.nota >=6.0){
            return "aprobada";
        }
        return "reprobada";
    }


}
