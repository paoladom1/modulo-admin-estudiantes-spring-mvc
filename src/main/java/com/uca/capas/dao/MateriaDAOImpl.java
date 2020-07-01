package com.uca.capas.dao;

import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class MateriaDAOImpl implements MateriaDAO {

    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Override
    public List<Materia> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.Materia");
        Query query = entityManager.createNativeQuery(sb.toString(), Materia.class);
        List<Materia> resulset = query.getResultList();

        return resulset;
    }

    @Override
    public Materia findOne(Integer code) throws DataAccessException {

        Materia materia = entityManager.find(Materia.class, code);
        return materia;
    }

    @Override
    @Transactional
    public void save(Materia materia) throws DataAccessException {

        try {
            if(materia.getCodMateria()==null){
                entityManager.persist(materia);
            }else{
                entityManager.merge(materia);
                entityManager.flush();
            }

        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    @Override
    @Transactional
    public void delete(Integer codMateria) throws DataAccessException {

        Materia materia = entityManager.find(Materia.class, codMateria);
        entityManager.remove(materia);
    }
}
