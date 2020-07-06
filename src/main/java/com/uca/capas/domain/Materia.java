package com.uca.capas.domain;

import org.hibernate.annotations.Type;

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
    @NotNull(message = "*Campo Obligatorio")
    @Size( min= 4,max = 4,message = "El campo solo debe tener 4 digitos")
    private Integer year;

    @Column(name = "ciclo")
    @NotNull(message = "*Campo Obligatorio*")
    @Size(min=1, max = 1, message = "El campo solo debe tener 2 digitos")
    private Integer cicle;

    @Column(name = "nota")
    @NotEmpty(message = "*Campo Obligatorio")
    @Size(min =3, max = 3,message = "El campo debe tener 3 digitos maximo")
    private Integer notaMateria;

    @Column(name = "descripcion")
    @NotEmpty(message = "*Campo Obligatorio*")
    @Size(message = "El campo no debe tener más de 3 dígitos", max = 4)
    private String description;

    @Column(name = "resultado")
    @Type(type = "string")
    private String resultado;


    @OneToOne
    @NotNull(message = "*Campo Obligatorio*")
    @JoinColumn(name = "codigoMateria")
    private CatalogoMateria catalogoMateria;

    @OneToOne
    @JoinColumn(name = "codigoestudiante")
    private Alumno alumnoMateria;


    public Materia(Integer codMateria, Integer year, Integer cicle, String description, Integer notaMateria, String resultado, CatalogoMateria catalogoMateria, Alumno alumnoMateria) {
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
