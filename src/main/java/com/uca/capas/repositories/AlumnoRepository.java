package com.uca.capas.repositories;

import com.uca.capas.domain.Alumno;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    public List<Alumno> findByNombres(String nombres) throws DataAccessException;

    public List<Alumno> findByApellidos(String apellidos) throws DataAccessException;

    public Alumno findByCodigoEstudiante(Integer codigo) throws DataAccessException;
}
