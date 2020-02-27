package com.huanshi.dao;

import com.huanshi.model.Node;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@DAO
public class NodeDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Node> getAll() {
        return em.createNamedQuery("Node.getAll", Node.class).getResultList();
    }

    public Node get(long id) {
        return em.find(Node.class, id);
    }

    public Node getByName(String name) {
        return em.createNamedQuery("Node.getByName", Node.class)
                .setParameter("name", name).getSingleResult();
    }

    public Node create(String name) {
        Node node = new Node(name);
        em.persist(node);
        return node;
    }

    public Node deleteByName(String name) {
        Node node = getByName(name);
        if(node != null) {
            em.remove(node);
        } else
            throw new EntityNotFoundException();
        return node;
    }

    public void update(Node node) {
        Node toUpdate = em.find(Node.class, node.getId());
        toUpdate.setDeviceName(node.getDeviceName());
    }
}
