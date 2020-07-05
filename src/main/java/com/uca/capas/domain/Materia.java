package com.uca.capas.domain;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "materia")
public class Materia {

    @Id
    @Column(name = "idMateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoMateria;

    @Column(name = "anio")
    @Size(message = "El campo solo debe tener 4 digitos", max = 4)
    @NotEmpty(message = "*Campo Obligatorio")
    private Integer year;

    @Column(name = "ciclo")
    @Size(message = "El campo solo debe tener 2 digitos", max = 2)
    @NotEmpty(message = "*Campo Obligatorio*")
    private Integer cicle;

    @Column(name = "nota")
    @Size(message = "El campo debe tener 3 digitos maximo", max = 3)
    @NotNull(message = "*Campo Obligatorio")
    private Double notaMateria;

    @Column(name = "descripcion")
    @Size(message = "El campo no debe tener más de 3 dígitos", max = 4)
    @NotEmpty(message = "*Campo Obligatorio*")
    private String description;

    @Column(name = "resultado")
    private String resultado;


    @OneToOne
    @NotEmpty(message = "*Campo Obligatorio*")
    @JoinColumn(name = "codigoMateria")
    private CatalogoMateria catalogoMateria;

    @OneToOne
    @JoinColumn(name = "codigoestudiante")
    private Alumno alumnoMateria;


    public Materia(Integer codMateria, Integer year, Integer cicle, String description, Double notaMateria, String resultado, CatalogoMateria catalogoMateria, Alumno alumnoMateria) {
        this.codigoMateria = codMateria;
        this.year = year;
        this.cicle = cicle;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNotaMateria() {
        return notaMateria;
    }

    public void setNotaMateria(Double notaMateria) {
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
