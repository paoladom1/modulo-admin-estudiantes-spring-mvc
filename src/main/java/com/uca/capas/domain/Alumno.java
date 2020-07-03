package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "public", name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoestudiante")
    private Integer codigoEstudiante;

    @Column(name = "nombreestudiante")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    private String nombres;

    @Column(name = "apellidoestudiante")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    private String apellidos;

    @Column(name = "carnetminoridad")
    @Size(min = 9, max = 9, message = "Debe contener 9 caracteres alfanumericos")
    private String carnetMinoridad;

    @NotNull(message = "Ingrese una fecha valida")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechanacimiento")
    private Date fechaNacimiento;

    private Integer edad;

    @Column(name = "direccion")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 100, message = "No debe exceder los 100 caracteres")
    private String direccion;

    @Column(name = "telefonofijo")
    @Size(min = 8, max = 8, message = "Debe contener 8 digitos")
    private String telFijo;

    @Column(name = "telefonocelular")
    @Size(min = 8, max = 8, message = "Debe contener 8 digitos")
    private String telMovil;

    @OneToOne
    @JoinColumn(name = "idmunicipio")
    private Municipio municipio;

    @OneToOne
    @JoinColumn(name = "idcentroescolar")
    private Institucion centroEscolar;

    @Column(name = "nombremadre")
    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    private String nombreMadre;

    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Column(name = "nombrepadre")
    private String nombrePadre;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AlumnoMateria> alumnoMaterias;

    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarnetMinoridad() {
        return carnetMinoridad;
    }

    public void setCarnetMinoridad(String carnetMinoridad) {
        this.carnetMinoridad = carnetMinoridad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    public Institucion getCentroEscolar() {
        return centroEscolar;
    }

    public void setCentroEscolar(Institucion centroEscolar) {
        this.centroEscolar = centroEscolar;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getEdad(Date fechaNac) {
        int years = 0;
        int months = 0;
        int days = 0;

        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(fechaNac.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        //Get difference between months
        months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0)
        {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
            years--;
            months = 11;
        }

        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        }
        else
        {
            days = 0;
            if (months == 12)
            {
                years++;
                months = 0;
            }
        }

        return years;
    }
}
