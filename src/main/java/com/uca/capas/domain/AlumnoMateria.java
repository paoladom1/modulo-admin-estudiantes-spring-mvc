package com.uca.capas.domain;

import sun.rmi.rmic.Generator;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

@Entity
@Table(schema = "public", name ="alumnoxmateria")
public class AlumnoMateria {

    @Id
    @Column(name="idalumnoxmateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumnoMateria;

    public Integer getIdAlumnoMateria() {
        return idAlumnoMateria;
    }

    public void setIdAlumnoMateria(Integer idAlumnoMateria) {
        this.idAlumnoMateria = idAlumnoMateria;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idalumno")
    private Alumno idAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmateria")
    private Materia idMateria;

    @Column(name = "notamateria")
    private Double nota;

    public AlumnoMateria(Alumno idAlumno, Materia idMateria, Double nota) {
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.nota = nota;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
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
