package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float pulse;
    private Float temperature;
    private Float respiratoryRate;
    private Float bloodPreasureD;
    private Float bloodPreasureS;
    private Float weight;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPulse() {
        return pulse;
    }

    public void setPulse(Float pulse) {
        this.pulse = pulse;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Float respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public Float getBloodPreasureD() {
        return bloodPreasureD;
    }

    public void setBloodPreasureD(Float bloodPreasureD) {
        this.bloodPreasureD = bloodPreasureD;
    }

    public Float getBloodPreasureS() {
        return bloodPreasureS;
    }

    public void setBloodPreasureS(Float bloodPreasureS) {
        this.bloodPreasureS = bloodPreasureS;
    }

    public MedicalForm getMedicalForm() {
        return medicalForm;
    }

    public void setMedicalForm(MedicalForm medicalForm) {
        this.medicalForm = medicalForm;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "VitalSigns{" +
                "id=" + id +
                ", pulse='" + pulse + '\'' +
                ", temperature=" + temperature +
                ", respiratoryRate='" + respiratoryRate + '\'' +
                ", bloodPreasureD='" + bloodPreasureD + '\'' +
                ", bloodPreasureS='" + bloodPreasureS + '\'' +
                ", weight=" + weight +
                ", date=" + date +
                ", user=" + user +
                ", medicalForm=" + medicalForm +
                '}';
    }
}
