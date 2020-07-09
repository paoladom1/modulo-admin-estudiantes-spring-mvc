package com.uca.capas.services;

import com.uca.capas.domain.CatalogoMateria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CatalogoMateriaService {

    public List<CatalogoMateria> findAll() throws DataAccessException;
   // public  void save(CatalogoMateria am) throws DataAccessException;
    public void save(CatalogoMateria materia) throws DataAccessException;
}
