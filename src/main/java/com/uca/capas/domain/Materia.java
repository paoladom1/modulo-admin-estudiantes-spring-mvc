package com.uca.capas.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(schema = "public", name = "materia")
public class Materia {

    @Id
    @Column(name = "idMateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoMateria;

    @Column(name = "anio")
    @NotNull(message = "*Campo Obligatorio")
    @Range(min = 2005, max = 2020, message = "Año entre 2005 y 2020")
    //@Size( min= 4,max = 4,message = "El campo solo debe tener 4 digitos")
    private Integer year;

    @Column(name = "ciclo")
    @NotNull(message = "*Campo Obligatorio*")
    @Range(min = 0, max = 3)
    private Integer cicle;

    @Column(name = "nota")
    @NotNull(message = "*Campo Obligatorio*")
    @Range(min = 0, max = 10, message = "Nota entre 0 y 10")
    private Integer notaMateria;

    @Column(name = "resultado")
    private String resultado;

    @OneToOne
    @NotNull(message = "*Campo Obligatorio*")
    @JoinColumn(name = "idcatalogo")
    private CatalogoMateria catalogoMateria;

   /* @OneToOne
    @JoinColumn(name = "idalumno")
    private Alumno alumnoMateria;*/

    @JoinColumn(name = "idalumno", referencedColumnName = "codigoestudiante")
    @ManyToOne(fetch = FetchType.LAZY)
    private Alumno alumnoMateria;


    public Materia(Integer codMateria, Integer year, Integer cicle, String description, Integer notaMateria, String resultado, CatalogoMateria catalogoMateria, Alumno alumnoMateria) {
        this.codigoMateria = codMateria;
        this.year = year;
        this.cicle = cicle;
        this.notaMateria = notaMateria;
        this.resultado = resultado;
        this.catalogoMateria = catalogoMateria;
        this.alumnoMateria = alumnoMateria;
    }

    public Alumno getAlumnoMateria() {
        return alumnoMateria;
    }

    public void setAlumnoMateria(Alumno alumnoMateria) {
        this.alumnoMateria = alumnoMateria;
    }

    public Integer getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Integer codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public CatalogoMateria getCatalogoMateria() {
        return catalogoMateria;
    }

    public void setCatalogoMateria(CatalogoMateria catalogoMateria) {
        this.catalogoMateria = catalogoMateria;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCicle() {
        return cicle;
    }

    public void setCicle(Integer cicle) {
        this.cicle = cicle;
    }


    public Integer getNotaMateria() {
        return notaMateria;
    }

    public void setNotaMateria(Integer notaMateria) {
        this.notaMateria = notaMateria;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Materia() {
    }
}
