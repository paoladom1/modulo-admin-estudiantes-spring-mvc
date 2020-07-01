package com.uca.capas.service;

import com.uca.capas.domain.Alumno;
import com.uca.capas.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno findOne(Integer code) throws DataAccessException {
        return alumnoRepository.findByCodigoEstudiante(code);
    }

    @Override
    public List<Alumno> findByNombres(String nombre) throws DataAccessException {
        return alumnoRepository.findByNombres(nombre);
    }
    @Override
    public List<Alumno> findByApellidos(String apellido) throws DataAccessException {
        return alumnoRepository.findByApellidos(apellido);
    }

    @Override
    public void save(Alumno alumno) throws DataAccessException {
        alumnoRepository.save(alumno);
    }
}
