package com.huanshi.dao;

import com.huanshi.model.DataLine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DAO
public class DataLineDAO {

    @PersistenceContext
    private EntityManager em;

    public List<DataLine> getAll() {
        String query = "SELECT dataLine FROM DataLine dataLine";
        return em.createQuery(query).getResultList();
    }

    public DataLine get(long id) {
        return em.find(DataLine.class, id);
    }

    

}
