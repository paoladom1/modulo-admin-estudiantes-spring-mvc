package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getEstadoDelegate() {
        if(this.estado ==  null) return "";
        else {
            return estado == true ? "Activo" : "Inactivo";
        }
    }
}
