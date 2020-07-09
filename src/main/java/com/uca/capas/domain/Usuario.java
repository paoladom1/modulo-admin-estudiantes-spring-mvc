package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(schema = "public", name = "users")
public class Usuario {
    @Column(name = "nombre")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 30, message = "No debe exceder los 30 caracteres")
    private String nombre;

    @Column(name = "apellido")
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 30, message = "No debe exceder los 30 caracteres")
    private String apellido;

    @NotNull(message = "Ingrese una fecha valida")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaDeNacimiento")
    private Date fechaNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @JoinColumn(name = "idDepartamento")
    @OneToOne
    private Departamento departamento;

    @JoinColumn(name = "idMunicipio")
    @OneToOne
    private Municipio municipio;

    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 100, message = "No debe exceder los 100 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "enabled")
    private Boolean estado;

    @Id
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(max = 30, message = "No debe exceder los 30 caracteres")
    @Column(name = "username", unique = true)
    private String nombreUsuario;

    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    @Column(name = "password")
    private String contrasenia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimientoS) {
    	System.out.println(fechaNacimientoS);
        try {
			this.fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getEdadDelegate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fechaNacimiento);
        if (this.fechaNacimiento == null) return 0;
        else {
            LocalDate localFNacimiento = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
            Integer edad = Period.between(localFNacimiento, LocalDate.now()).getYears();

            return  edad;
        }
    }
    public String getEstadoDelegate() {
    	if(estado) {
    		return "Activo";
    	}else {
    		return "Inactivo";
    	}
    }
}
