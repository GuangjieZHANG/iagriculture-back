package com.huanshi.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Standard.getAll", query = "select standard from Standard standard"),
        @NamedQuery(name = "Standard.getAllByDevice", query = "select standard from Standard standard where standard.device = :deviceName"),
        @NamedQuery(name = "Standard.getTheLatest", query = "select standard from Standard standard " +
                "where standard.device = :deviceName and standard.id = (select max(d.id) from Standard d where d.device = :deviceName)")
})
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private float maxAirTempreture;
    private float minAirTempreture;
    private float maxAirHumidity;
    private float minAirHumidity;
    private float maxEarthTempreture;
    private float minEarthTempreture;
    private float maxEarthHumidity;
    private float minEarthHumidity;
    private float maxEarthPh;
    private float minEarthPh;
    private float maxNitrogen; //N
    private float minNitrogen; //N
    private float maxPhosphorus; //P
    private float minPhosphorus; //P
    private float maxPotassium; //K
    private float minPotassium; //K

    private LocalDateTime time;

    private String device;

    public Standard() {
    }

    public Standard(float maxAirTempreture, float minAirTempreture, float maxAirHumidity, float minAirHumidity,
                    float maxEarthTempreture, float minEarthTempreture, float maxEarthHumidity, float minEarthHumidity,
                    float maxEarthPh, float minEarthPh, float maxNitrogen, float minNitrogen, float maxPhosphorus,
                    float minPhosphorus, float maxPotassium, float minPotassium, LocalDateTime time, String device) {
        this.maxAirTempreture = maxAirTempreture;
        this.minAirTempreture = minAirTempreture;
        this.maxAirHumidity = maxAirHumidity;
        this.minAirHumidity = minAirHumidity;
        this.maxEarthTempreture = maxEarthTempreture;
        this.minEarthTempreture = minEarthTempreture;
        this.maxEarthHumidity = maxEarthHumidity;
        this.minEarthHumidity = minEarthHumidity;
        this.maxEarthPh = maxEarthPh;
        this.minEarthPh = minEarthPh;
        this.maxNitrogen = maxNitrogen;
        this.minNitrogen = minNitrogen;
        this.maxPhosphorus = maxPhosphorus;
        this.minPhosphorus = minPhosphorus;
        this.maxPotassium = maxPotassium;
        this.minPotassium = minPotassium;
        this.time = time;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getMaxAirTempreture() {
        return maxAirTempreture;
    }

    public void setMaxAirTempreture(float maxAirTempreture) {
        this.maxAirTempreture = maxAirTempreture;
    }

    public float getMinAirTempreture() {
        return minAirTempreture;
    }

    public void setMinAirTempreture(float minAirTempreture) {
        this.minAirTempreture = minAirTempreture;
    }

    public float getMaxAirHumidity() {
        return maxAirHumidity;
    }

    public void setMaxAirHumidity(float maxAirHumidity) {
        this.maxAirHumidity = maxAirHumidity;
    }

    public float getMinAirHumidity() {
        return minAirHumidity;
    }

    public void setMinAirHumidity(float minAirHumidity) {
        this.minAirHumidity = minAirHumidity;
    }

    public float getMaxEarthTempreture() {
        return maxEarthTempreture;
    }

    public void setMaxEarthTempreture(float maxEarthTempreture) {
        this.maxEarthTempreture = maxEarthTempreture;
    }

    public float getMinEarthTempreture() {
        return minEarthTempreture;
    }

    public void setMinEarthTempreture(float minEarthTempreture) {
        this.minEarthTempreture = minEarthTempreture;
    }

    public float getMaxEarthHumidity() {
        return maxEarthHumidity;
    }

    public void setMaxEarthHumidity(float maxEarthHumidity) {
        this.maxEarthHumidity = maxEarthHumidity;
    }

    public float getMinEarthHumidity() {
        return minEarthHumidity;
    }

    public void setMinEarthHumidity(float minEarthHumidity) {
        this.minEarthHumidity = minEarthHumidity;
    }

    public float getMaxEarthPh() {
        return maxEarthPh;
    }

    public void setMaxEarthPh(float maxEarthPh) {
        this.maxEarthPh = maxEarthPh;
    }

    public float getMinEarthPh() {
        return minEarthPh;
    }

    public void setMinEarthPh(float minEarthPh) {
        this.minEarthPh = minEarthPh;
    }

    public float getMaxNitrogen() {
        return maxNitrogen;
    }

    public void setMaxNitrogen(float maxNitrogen) {
        this.maxNitrogen = maxNitrogen;
    }

    public float getMinNitrogen() {
        return minNitrogen;
    }

    public void setMinNitrogen(float minNitrogen) {
        this.minNitrogen = minNitrogen;
    }

    public float getMaxPhosphorus() {
        return maxPhosphorus;
    }

    public void setMaxPhosphorus(float maxPhosphorus) {
        this.maxPhosphorus = maxPhosphorus;
    }

    public float getMinPhosphorus() {
        return minPhosphorus;
    }

    public void setMinPhosphorus(float minPhosphorus) {
        this.minPhosphorus = minPhosphorus;
    }

    public float getMaxPotassium() {
        return maxPotassium;
    }

    public void setMaxPotassium(float maxPotassium) {
        this.maxPotassium = maxPotassium;
    }

    public float getMinPotassium() {
        return minPotassium;
    }

    public void setMinPotassium(float minPotassium) {
        this.minPotassium = minPotassium;
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
        if (!(o instanceof Standard)) return false;
        Standard standard = (Standard) o;
        return getId() == standard.getId() &&
                Float.compare(standard.getMinNitrogen(), getMinNitrogen()) == 0 &&
                Float.compare(standard.getMaxPhosphorus(), getMaxPhosphorus()) == 0 &&
                Float.compare(standard.getMinPhosphorus(), getMinPhosphorus()) == 0 &&
                Float.compare(standard.getMaxPotassium(), getMaxPotassium()) == 0 &&
                Float.compare(standard.getMinPotassium(), getMinPotassium()) == 0 &&
                Objects.equals(getTime(), standard.getTime()) &&
                Objects.equals(getDevice(), standard.getDevice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Standard{" +
                "id=" + id +
                ", maxAirTempreture=" + maxAirTempreture +
                ", minAirTempreture=" + minAirTempreture +
                ", maxAirHumidity=" + maxAirHumidity +
                ", minAirHumidity=" + minAirHumidity +
                ", maxEarthTempreture=" + maxEarthTempreture +
                ", minEarthTempreture=" + minEarthTempreture +
                ", maxEarthHumidity=" + maxEarthHumidity +
                ", minEarthHumidity=" + minEarthHumidity +
                ", maxEarthPh=" + maxEarthPh +
                ", minEarthPh=" + minEarthPh +
                ", maxNitrogen=" + maxNitrogen +
                ", minNitrogen=" + minNitrogen +
                ", maxPhosphorus=" + maxPhosphorus +
                ", minPhosphorus=" + minPhosphorus +
                ", maxPotassium=" + maxPotassium +
                ", minPotassium=" + minPotassium +
                ", time=" + time +
                ", device='" + device + '\'' +
                '}';
    }
}
