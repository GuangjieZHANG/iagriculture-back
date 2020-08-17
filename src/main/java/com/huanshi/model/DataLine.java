package com.huanshi.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "DataLine.getAll", query = "select dataLine from DataLine dataLine"),
        @NamedQuery(name = "DataLine.getAllByDevice", query = "select dataLine from DataLine dataLine where dataLine.device = :deviceName"),
        @NamedQuery(name = "DataLine.getTheLatest", query = "select dataLine from DataLine dataLine " +
                "where dataLine.device = :deviceName and dataLine.id = (select max(d.id) from DataLine d where d.device = :deviceName)")
})
public class DataLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private float airTempreture;
    private float airHumidity;
    private String wind;
    private float earthTempreture;
    private float earthHumidity;
    private float earthPh;
    private float nitrogen; //N
    private float phosphorus; //P
    private float potassium; //K

    private LocalDateTime time;

    private String device;

    public DataLine() {
    }

    public DataLine(float airTempreture, float airHumidity, String wind, float earthTempreture,
                    float earthHumidity, float earthPh, float nitrogen, float phosphorus, float potassium,
                    LocalDateTime time, String device) {
        this.airTempreture = airTempreture;
        this.airHumidity = airHumidity;
        this.wind = wind;
        this.earthTempreture = earthTempreture;
        this.earthHumidity = earthHumidity;
        this.earthPh = earthPh;
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.time = time;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAirTempreture() {
        return airTempreture;
    }

    public void setAirTempreture(float airTempreture) {
        this.airTempreture = airTempreture;
    }

    public float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(float airHumidity) {
        this.airHumidity = airHumidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public float getEarthTempreture() {
        return earthTempreture;
    }

    public void setEarthTempreture(float earthTempreture) {
        this.earthTempreture = earthTempreture;
    }

    public float getEarthHumidity() {
        return earthHumidity;
    }

    public void setEarthHumidity(float earthHumidity) {
        this.earthHumidity = earthHumidity;
    }

    public float getEarthPh() {
        return earthPh;
    }

    public void setEarthPh(float earthPh) {
        this.earthPh = earthPh;
    }

    public float getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(float nitrogen) {
        this.nitrogen = nitrogen;
    }

    public float getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(float phosphorus) {
        this.phosphorus = phosphorus;
    }

    public float getPotassium() {
        return potassium;
    }

    public void setPotassium(float potassium) {
        this.potassium = potassium;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataLine)) return false;
        DataLine dataLine = (DataLine) o;
        return getId() == dataLine.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "id=" + id +
                ", airTempreture=" + airTempreture +
                ", airHumidity=" + airHumidity +
                ", wind='" + wind + '\'' +
                ", earthTempreture=" + earthTempreture +
                ", earthHumidity=" + earthHumidity +
                ", earthPh=" + earthPh +
                ", nitrogen=" + nitrogen +
                ", phosphorus=" + phosphorus +
                ", potassium=" + potassium +
                ", time=" + time +
                ", device=" + device +
                '}';
    }
}
