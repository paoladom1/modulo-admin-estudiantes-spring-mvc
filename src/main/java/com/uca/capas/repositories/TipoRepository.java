package com.uca.capas.repositories;

import com.uca.capas.domain.Tipo;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    @Query(nativeQuery = true, value = "select * from public.tipoUsuario")
    public List<Tipo> showAll() throws DataAccessException;
}
