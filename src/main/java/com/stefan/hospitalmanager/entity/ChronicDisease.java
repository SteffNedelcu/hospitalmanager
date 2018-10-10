package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChronicDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String observations;
    private String codeCIM;
    private String code;
    private String name;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    @JsonIgnore
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public ChronicDisease() {}

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

    public MedicalForm getMedicalForm() {
        return medicalForm;
    }

    public void setMedicalForm(MedicalForm medicalForm) {
        this.medicalForm = medicalForm;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    @Override
    public String toString() {
        return "ChronicDisease{" +
                "id=" + id +
                ", date=" + date +
                ", observations='" + observations + '\'' +
                ", codeCIM='" + codeCIM + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", pacient=" + pacient +
                ", medicalForm=" + medicalForm +
                '}';
    }
}
