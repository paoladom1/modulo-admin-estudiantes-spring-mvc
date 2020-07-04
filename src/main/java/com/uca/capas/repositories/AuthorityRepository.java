package com.uca.capas.repositories;

import com.uca.capas.domain.Authorities;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
    @Query(nativeQuery = true, value = "select * from public.authorities")
    public List<Authorities> showAll() throws DataAccessException;
}
