package com.uca.capas.services;

import com.uca.capas.domain.Institucion;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface InstitucionService {
    public List<Institucion> findAll() throws DataAccessException;

    public List<Institucion> findByMunicipio(Integer id) throws DataAccessException;
    
    public void save(Institucion institucion) throws DataAccessException;
    
    public Institucion findOne(Integer code) throws DataAccessException;
}
