package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AllergicDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String observations;
    private String code;
    private String codeCIM;
    private Integer status;
    private String name;


    @ManyToOne
    @JoinColumn(name = "pacient_id")
    @JsonIgnore
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public AllergicDisease() {}

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

    public MedicalForm getMedicalForm() {
        return medicalForm;
    }

    public void setMedicalForm(MedicalForm medicalForm) {
        this.medicalForm = medicalForm;
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
        return "AllergicDisease{" +
                "id=" + id +
                ", date=" + date +
                ", observations='" + observations + '\'' +
                ", code='" + code + '\'' +
                ", codeCIM='" + codeCIM + '\'' +
                ", status=" + status +
                ", pacient=" + pacient +
                ", medicalForm=" + medicalForm +
                '}';
    }
}
