package com.uca.capas.domain;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "Materia")
public class Materia {

    @Id
    @Column(name = "idMateria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codMateria;

    @Size(message = "El campo no debe tener más de 15 digitos", max = 15)
    @NotEmpty(message = "*Campo Obligatorio*")
    @Column(name = "nombreMateria")
    private String nomMateria;

    @Size(message = "El campo solo debe tener 4 digitos", max = 4)
    @NotEmpty(message = "*Campo Obligatorio")
    @Column(name = "anio")
    private Integer year;

    @Size(message = "El campo solo debe tener 2 digitos", max = 2)
    @NotEmpty(message = "*Campo Obligatorio*")
    @Column(name = "ciclo")
    private Integer cicle;

    @Size(message = "El campo no debe tener más de 3 dígitos", max = 4)
    @NotEmpty(message = "*Campo Obligatorio*")
    @Column(name = "descripcion")
    private String description;

    @OneToMany(mappedBy = "materia")
    private List<AlumnoMateria> materiaList = new ArrayList<>();


    public Materia(Integer codMateria, String nomMateria, Integer year, Integer cicle, String description) {
        this.codMateria = codMateria;
        this.nomMateria = nomMateria;
        this.year = year;
        this.cicle = cicle;
        this.description = description;
    }

    public Integer getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(Integer codMateria) {
        this.codMateria = codMateria;
    }

    public String getNomMateria() {
        return nomMateria;
    }

    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
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

    public Materia() {


}
}
