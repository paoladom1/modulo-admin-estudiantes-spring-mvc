package com.uca.capas.services;

import com.uca.capas.domain.AlumnoMateria;
import com.uca.capas.repositories.AlumnoMateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlumnoMateriaServiceImpl implements AlumnoMateriaService{

    @Autowired
    AlumnoMateriaRepo alumnoMateriaRepo;

    @Override
    public List<AlumnoMateria> findAll() throws DataAccessException {
        return alumnoMateriaRepo.findAll();
    }

    @Override
    @Transactional
    public void save(AlumnoMateria am) throws DataAccessException {
        alumnoMateriaRepo.save(am);
    }
}
