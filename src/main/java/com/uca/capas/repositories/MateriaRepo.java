package com.uca.capas.repositories;

import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaRepo extends JpaRepository<Materia, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.materia")
    public List<Materia> findAll() throws DataAccessException;

    @Query(nativeQuery = true, value = "SELECT * FROM public.materia where idalumno = ?")
    public List<Materia> findMateriasAlumno(Integer code) throws DataAccessException;

    @Query(nativeQuery = true, value = "SELECT COUNT(resultado) FROM public.materia where resultado = ?")
    public Float aprobar(Integer code) throws DataAccessException;

    @Query(nativeQuery = true, value = "SELECT COUNT(resultado) FROM public.materia where resultado = ?")
    public Float reprobar(Integer code) throws DataAccessException;

    @Query(nativeQuery = true, value = "SELECT AVG(nota) FROM public.materia where codigoEstudiante = ?")
    public Float notas(Integer code) throws DataAccessException;

    public Materia findByCodigoMateria(Integer code);
}
