package com.uca.capas.repositories;

import com.uca.capas.domain.Alumno;
import com.uca.capas.domain.AlumnoMateria;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoMateriaRepo extends JpaRepository<AlumnoMateria, Integer> {

    public AlumnoMateria findByIdAlumn(Integer code) throws DataAccessException;
    public AlumnoMateria findByIdMat(Integer code) throws DataAccessException;
}
