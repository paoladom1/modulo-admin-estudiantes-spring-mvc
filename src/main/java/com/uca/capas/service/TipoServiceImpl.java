package com.uca.capas.service;

import com.uca.capas.domain.Tipo;
import com.uca.capas.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoServiceImpl implements TipoService {
    @Autowired
    TipoRepository tipoRepository;

    @Override
    public List<Tipo> findAll() throws DataAccessException {
        return tipoRepository.showAll();
    }
}
