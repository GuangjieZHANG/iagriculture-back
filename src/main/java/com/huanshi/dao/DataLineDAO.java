package com.huanshi.dao;

import com.huanshi.model.DataLine;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@DAO
public class DataLineDAO {

    @PersistenceContext
    EntityManager em;

    public List<DataLine> getAll() {
        return em.createNamedQuery("DataLine.getAll", DataLine.class).getResultList();
    }

    public List<DataLine> getAllByDevice(String deviceName) {
        return em.createNamedQuery("DataLine.getAllByDevice", DataLine.class)
                .setParameter("deviceName", deviceName).getResultList();
    }

    public DataLine get(long id) {
        return em.find(DataLine.class, id);
    }

    public DataLine getTheLatest(String deviceName){
        return em.createNamedQuery("DataLine.getTheLatest", DataLine.class)
                .setParameter("deviceName", deviceName)
                .getSingleResult();
    }

    public DataLine create(DataLine dataLine) {
        em.persist(dataLine);
        return dataLine;
    }

    public DataLine delete(long id) {
        DataLine dataLine = em.find(DataLine.class, id);
        if (dataLine != null) {
            em.remove(dataLine);
        } else
            throw new EntityNotFoundException();
        return dataLine;
    }

    public DataLine update(DataLine dataLine) {
        DataLine toUpdate = em.find(DataLine.class, dataLine.getId());
        if (toUpdate == null)
            throw new EntityNotFoundException();
        // Device will not change when update
        dataLine.setDevice(toUpdate.getDevice());
        return em.merge(dataLine);
    }

    public List<DataLine> getHistoryByDevice(String deviceName) {
        String query = "select dataLine from DataLine dataLine where dataLine.device = :deviceName order by dataLine.id asc ";
        return em.createQuery(query, DataLine.class)
                .setParameter("deviceName", deviceName)
                .setMaxResults(20)
                .getResultList();
    }



}
