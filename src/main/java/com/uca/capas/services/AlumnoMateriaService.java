package com.uca.capas.services;

import com.uca.capas.domain.AlumnoMateria;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlumnoMateriaService {

    public List<AlumnoMateria> findAll() throws DataAccessException;
    public  void save(AlumnoMateria am) throws DataAccessException;
}
