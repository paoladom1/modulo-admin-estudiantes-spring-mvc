package com.uca.capas.services;

import com.uca.capas.domain.Materia;
import com.uca.capas.repositories.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    private EntityManager entityManager;

    @Autowired
    MateriaRepo materiaRepo;

    @Override
    public List<Materia> findAll() throws DataAccessException {
        return materiaRepo.findAll();
    }


    @Override
    public Materia findOne(Integer code) throws DataAccessException {
      return materiaRepo.findByCodigoMateria(code);
    }

    @Override
    @Transactional
    public void save(Materia materia) throws DataAccessException {
        materiaRepo.save(materia);
    }

    @Override
    @Transactional
    public void delete(Integer codMateria) throws DataAccessException {
        materiaRepo.deleteById(codMateria);
    }

    @Override
    public List<Materia> findMateriasAlumno(Integer code) throws DataAccessException {
        return materiaRepo.findMateriasAlumno(code);
    }

    @Override
    public Float aprobar(Integer code) throws DataAccessException {
        return materiaRepo.aprobar(code);
    }

    @Override
    public Float reprobar(Integer code) throws DataAccessException {
        return materiaRepo.reprobar(code);
    }

    @Override
    public Float notas(Integer code) throws DataAccessException {
        return materiaRepo.notas(code);
    }
}
