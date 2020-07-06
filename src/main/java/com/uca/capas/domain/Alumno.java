package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(schema = "public", name = "alumno")
public class Alumno {
    @Id
    @SequenceGenerator(name = "alumno_codigoestudiante_seq", sequenceName = "public.alumno_codigoestudiante_seq", allocationSize = 1)
    @GeneratedValue(generator="alumno_codigoestudiante_seq", strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "idCentroEscolar")
    private Institucion centroEscolar;

    @Column(name = "nombremadre")
    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    private String nombreMadre;

    @Size(max = 50, message = "No debe exceder los 50 caracteres")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Column(name = "nombrepadre")
    private String nombrePadre;


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

    public Integer getEdadDelegate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fechaNacimiento);
        if (this.fechaNacimiento == null) return 0;
        else {
            LocalDate localFNacimiento = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
            Integer edad = Period.between(localFNacimiento, LocalDate.now()).getYears();

            return edad;
        }
    }
}
