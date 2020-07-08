package com.uca.capas.services;

import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MateriaService {
    public List<Materia> findAll() throws DataAccessException;

    public Materia findOne(Integer code) throws DataAccessException;

    public void save(Materia materia) throws DataAccessException;

    public void delete(Integer codMateria) throws DataAccessException;

    public List<Materia> findMateriasAlumno(Integer code) throws DataAccessException;

    public Float aprobar(Integer code) throws DataAccessException;

    public Float reprobar(Integer code) throws DataAccessException;

    public Float notas(Integer code) throws DataAccessException;

}
