package com.uca.capas.services;

import com.uca.capas.domain.Institucion;
import com.uca.capas.repositories.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstitucionServiceImpl implements InstitucionService {
    @Autowired
    InstitucionRepository institucionRepository;

    @Override
    public List<Institucion> findAll() throws DataAccessException {
        return institucionRepository.findAll();
    }

    @Override
    public List<Institucion> findByMunicipio(Integer id) throws DataAccessException {
        return institucionRepository.findByMunicipio(id);
    }

	@Override
	public void save(Institucion institucion) throws DataAccessException {
		institucionRepository.save(institucion);
	}
}
