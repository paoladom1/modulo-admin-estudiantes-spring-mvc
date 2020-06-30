package com.uca.capas.service;

import com.uca.capas.domain.Alumno;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlumnoService {
    public List<Alumno> findByNombres(String nombre) throws DataAccessException;

    public List<Alumno> findByApellidos(String apellido) throws DataAccessException;

    public Alumno findOne(Integer id) throws DataAccessException;

    public void save(Alumno alumno) throws DataAccessException;
}
