package com.uca.capas.service;

import com.uca.capas.domain.Departamento;
import com.uca.capas.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoServiceImpl implements DepartamentoService {
    @Autowired
    DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> findAll() throws DataAccessException {
        return departamentoRepository.findAll();
    }
}
