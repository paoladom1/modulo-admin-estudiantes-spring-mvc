package com.uca.capas.repositories;

import com.uca.capas.domain.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByNombreUsuario(Usuario usuario) throws DataAccessException;

    public Usuario findByIdUsuario(Integer codigo) throws DataAccessException;

    @Query(nativeQuery = true, value = "select * from public.usuario")
    public List<Usuario> showAll() throws DataAccessException;

    @Query(nativeQuery = true, value = "select * from public.usuario where nombre = :cadena")
    public List<Usuario> showByName(String cadena) throws DataAccessException;


}