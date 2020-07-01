package com.uca.capas.dao;

import com.uca.capas.domain.AlumnoMateria;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlumnoMateriaDAO {

    public List<AlumnoMateria> findAll() throws DataAccessException;
    public  void save(AlumnoMateria am) throws DataAccessException;

}
