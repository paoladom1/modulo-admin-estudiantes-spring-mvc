package com.uca.capas.service;

import com.uca.capas.domain.Usuario;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> findAll() throws DataAccessException;

    public Usuario findByNombreUsuario(String usuario) throws DataAccessException;

    public void save(Usuario usuario) throws DataAccessException;

    public void delete(Integer codigoCuenta) throws DataAccessException;

    public List<Usuario> filterBy(String cadena) throws DataAccessException;

}
