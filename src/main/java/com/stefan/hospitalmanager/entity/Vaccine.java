package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String codeCIM;
    private Date date;
    private String observations;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    @JsonIgnore
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public Vaccine() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeCIM() {
        return codeCIM;
    }

    public void setCodeCIM(String codeCIM) {
        this.codeCIM = codeCIM;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public MedicalForm getMedicalForm() {
        return medicalForm;
    }

    public void setMedicalForm(MedicalForm medicalForm) {
        this.medicalForm = medicalForm;
    }


    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", codeCIM='" + codeCIM + '\'' +
                ", date=" + date +
                ", observations='" + observations + '\'' +
                ", status=" + status +
                ", pacient=" + pacient +
                ", medicalForm=" + medicalForm +
                '}';
    }
}
