package com.uca.capas.services;

import com.uca.capas.domain.Authorities;
import com.uca.capas.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository tipoRepository;

    @Override
    public List<Authorities> findAll() throws DataAccessException {
        return tipoRepository.showAll();
    }

    @Override
    public void save(Authorities authority) throws DataAccessException {
        tipoRepository.save(authority);
    }
}
