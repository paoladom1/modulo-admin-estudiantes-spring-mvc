package com.uca.capas.repositories;

import com.uca.capas.domain.CatalogoMateria;
import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogoMateriaRepo extends JpaRepository<CatalogoMateria, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.catalogomaterias")
    public List<CatalogoMateria> findAll() throws DataAccessException;
}
