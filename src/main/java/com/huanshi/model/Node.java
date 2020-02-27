package com.huanshi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Node.getAll", query = "select node from Node node"),
        @NamedQuery(name = "Node.getByName", query = "select node from Node node where node.deviceName = :name")
})
public class Node {

    @Id
    @GeneratedValue
    private long id;
    private String deviceName;

    public Node() {
    }

    public Node(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getId() == node.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
