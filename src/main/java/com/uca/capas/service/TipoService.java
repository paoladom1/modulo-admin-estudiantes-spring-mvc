package com.uca.capas.service;

import com.uca.capas.domain.Tipo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TipoService {
    public List<Tipo> findAll() throws DataAccessException;
}
