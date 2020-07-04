package com.uca.capas.services;

import com.uca.capas.domain.Usuario;
import com.uca.capas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() throws DataAccessException {
        return usuarioRepository.showAll();
    }

    @Override
    public Usuario findByNombreUsuario(String usuario) throws DataAccessException {
        return usuarioRepository.findByNombreUsuario(usuario);
    }

    @Override
    public void save(Usuario usuario) throws DataAccessException {
        Usuario user = usuario;
        user.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        usuarioRepository.save(user);
    }

    @Override
    public void delete(Integer code) throws DataAccessException {
        usuarioRepository.deleteById(code);
    }

    @Override
    public List<Usuario> filterBy(String cadena) throws DataAccessException {
        return usuarioRepository.showByName(cadena);
    }

}

