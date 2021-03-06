package com.huanshi.dao;

import com.huanshi.model.Standard;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@DAO
public class StandardDAO {

    @PersistenceContext
    EntityManager em;

    public List<Standard> getAll() {
        return em.createNamedQuery("Standard.getAll", Standard.class).getResultList();
    }

    public Standard get(long id) {
        return em.find(Standard.class, id);
    }

    public Standard getTheLatest(String deviceName){
        return em.createNamedQuery("Standard.getTheLatest", Standard.class)
                .setParameter("deviceName", deviceName)
                .getSingleResult();
    }

    public Standard create(Standard standard) {
        em.persist(standard);
        return standard;
    }

    public Standard delete(long id) {
        Standard standard = em.find(Standard.class, id);
        if (standard != null) {
            em.remove(standard);
        } else
            throw new EntityNotFoundException();
        return standard;
    }

    public void update(Standard standard) {
        Standard toUpdate = em.find(Standard.class, standard.getId());
        toUpdate.setMaxAirTempreture(standard.getMaxAirTempreture());
        toUpdate.setMinAirTempreture(standard.getMinAirTempreture());
        toUpdate.setMaxAirHumidity(standard.getMaxAirHumidity());
        toUpdate.setMinAirHumidity(standard.getMinAirHumidity());
        toUpdate.setMaxEarthHumidity(standard.getMaxEarthHumidity());
        toUpdate.setMinEarthHumidity(standard.getMinEarthHumidity());
        toUpdate.setMaxEarthTempreture(standard.getMaxEarthTempreture());
        toUpdate.setMinEarthTempreture(standard.getMinEarthTempreture());
        toUpdate.setMaxEarthPh(standard.getMaxEarthPh());
        toUpdate.setMinEarthPh(standard.getMinEarthPh());
        toUpdate.setMaxNitrogen(standard.getMaxNitrogen());
        toUpdate.setMinNitrogen(standard.getMinNitrogen());
        toUpdate.setMaxPhosphorus(standard.getMaxPhosphorus());
        toUpdate.setMinPhosphorus(standard.getMinPhosphorus());
        toUpdate.setMaxPotassium(standard.getMaxPotassium());
        toUpdate.setMinPotassium(standard.getMinPotassium());
    }

}
