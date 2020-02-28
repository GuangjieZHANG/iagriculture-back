package com.huanshi.dao;

import com.huanshi.model.DataLine;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@DAO
public class DataLineDAO {

    @PersistenceContext
    private EntityManager em;

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
        DataLine created = new DataLine(dataLine.getAirTempreture(), dataLine.getAirHumidity(), dataLine.getWind(),
                dataLine.getEarthTempreture(), dataLine.getEarthHumidity(), dataLine.getEarthPh(),
                dataLine.getNitrogen(), dataLine.getPhosphorus(), dataLine.getPotassium(),
                dataLine.getTime(), dataLine.getDevice());
        em.persist(created);
        return created;
    }

    public DataLine delete(long id) {
        DataLine dataLine = em.find(DataLine.class, id);
        if (dataLine != null) {
            em.remove(dataLine);
        } else
            throw new EntityNotFoundException();
        return dataLine;
    }

    public void update(DataLine dataLine) {
        DataLine toUpdate = em.find(DataLine.class, dataLine.getId());
        toUpdate.setAirTempreture(dataLine.getAirTempreture());
        toUpdate.setAirHumidity(dataLine.getAirHumidity());
        toUpdate.setWind(dataLine.getWind());
        toUpdate.setEarthTempreture(dataLine.getEarthTempreture());
        toUpdate.setEarthHumidity(dataLine.getEarthHumidity());
        toUpdate.setEarthPh(dataLine.getEarthPh());
        toUpdate.setNitrogen(dataLine.getNitrogen());
        toUpdate.setPhosphorus(dataLine.getPhosphorus());
        toUpdate.setPotassium(dataLine.getPotassium());
        toUpdate.setTime(dataLine.getTime());
        toUpdate.setDevice(dataLine.getDevice());
    }

    public List<DataLine> getHistoryByDevice(String deviceName) {
        String query = "select dataLine from DataLine dataLine where dataLine.device = :deviceName order by dataLine.id desc";
        return em.createQuery(query, DataLine.class)
                .setParameter("deviceName", deviceName)
                .setMaxResults(20)
                .getResultList();
    }



}
