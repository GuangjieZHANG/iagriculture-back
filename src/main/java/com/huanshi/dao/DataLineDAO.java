package com.huanshi.dao;

import com.huanshi.model.DataLine;
import com.huanshi.model.Node;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@DAO
public class DataLineDAO {

    @PersistenceContext
    private EntityManager em;

    public List<DataLine> getAll() {
        return em.createNamedQuery("DataLine.getAll", DataLine.class).getResultList();
    }

    public DataLine get(long id) {
        return em.find(DataLine.class, id);
    }

    public DataLine getTheLatest(String deviceName){
        return em.createNamedQuery("DataLine.getTheLatest", DataLine.class)
                .setParameter("deviceName", deviceName)
                .getSingleResult();
    }

    public DataLine create(float airTempreture, float airHumidity, String wind, float earthTempreture,
                           float earthHumidity, float earthPh, float nitrogen, float phosphorus, float potassium,
                           LocalDateTime time, Node device) {
        DataLine created = new DataLine(airTempreture, airHumidity, wind, earthTempreture, earthHumidity,
                earthPh, nitrogen, phosphorus, potassium, time, device);
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

}
