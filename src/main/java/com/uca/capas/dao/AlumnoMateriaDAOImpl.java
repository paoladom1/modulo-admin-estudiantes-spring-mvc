package com.uca.capas.dao;

import com.uca.capas.domain.AlumnoMateria;
import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AlumnoMateriaDAOImpl implements AlumnoMateriaDAO{

    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Override
    public List<AlumnoMateria> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.AlumnoXmateria");
        Query query = entityManager.createNativeQuery(sb.toString(), AlumnoMateria.class);
        List<AlumnoMateria> resulset = query.getResultList();

        return resulset;
    }

    @Override
    public void save(AlumnoMateria am) throws DataAccessException {

        try {
            if(am.getMateria()==null){
                entityManager.persist(am);
            }else {
                entityManager.merge(am);
                entityManager.flush();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
