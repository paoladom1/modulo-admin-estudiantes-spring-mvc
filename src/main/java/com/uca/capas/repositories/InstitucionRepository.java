package com.uca.capas.repositories;

import com.uca.capas.domain.Institucion;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {
    public List<Institucion> findByMunicipio(Integer id) throws DataAccessException;
}
