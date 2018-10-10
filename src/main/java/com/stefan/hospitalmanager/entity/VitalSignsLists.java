package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VitalSignsLists {
    List<Float> pulse = new ArrayList<Float>();
    List<Float> temperature = new ArrayList<Float>();
    List<Float> respiratoryRate = new ArrayList<Float>();
    List<Float> bloodPreasureD = new ArrayList<Float>();
    List<Float> bloodPreasureS = new ArrayList<Float>();
    List<Float> weight = new ArrayList<Float>();

    public VitalSignsLists() {
    }

    public List<Float> getPulse() {
        return pulse;
    }

    public void setPulse(List<Float> pulse) {
        this.pulse = pulse;
    }

    public List<Float> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Float> temperature) {
        this.temperature = temperature;
    }

    public List<Float> getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(List<Float> respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public List<Float> getBloodPreasureD() {
        return bloodPreasureD;
    }

    public void setBloodPreasureD(List<Float> bloodPreasureD) {
        this.bloodPreasureD = bloodPreasureD;
    }

    public List<Float> getBloodPreasureS() {
        return bloodPreasureS;
    }

    public void setBloodPreasureS(List<Float> bloodPreasureS) {
        this.bloodPreasureS = bloodPreasureS;
    }

    public List<Float> getWeight() {
        return weight;
    }

    public void setWeight(List<Float> weight) {
        this.weight = weight;
    }
}
