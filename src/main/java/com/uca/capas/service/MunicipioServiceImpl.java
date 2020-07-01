package com.uca.capas.service;

import com.uca.capas.domain.Municipio;
import com.uca.capas.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MunicipioServiceImpl implements MunicipioService {
    @Autowired
    MunicipioRepository municipioRepository;

    @Override
    public List<Municipio> findAll() throws DataAccessException {
        return municipioRepository.findAll();
    }
}
