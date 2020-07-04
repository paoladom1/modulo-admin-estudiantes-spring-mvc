package com.uca.capas.services;

import com.uca.capas.domain.Authorities;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthorityService {
    public void save(Authorities authority) throws DataAccessException;
    public List<Authorities> findAll() throws DataAccessException;
}
