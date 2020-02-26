package com.huanshi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DAO
public class NodeDAO {

    @PersistenceContext
    private EntityManager em;


}
