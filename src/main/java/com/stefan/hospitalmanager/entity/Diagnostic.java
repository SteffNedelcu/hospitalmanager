package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Diagnostic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String codeCIM;
    //cod intern al sistemului Hash unic
    private String code;
    private String name;
    private String observations;
    private Integer status;
    private Integer cronical;
    private Integer allergy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public Diagnostic() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCronical() {
        return cronical;
    }

    public void setCronical(Integer cronical) {
        this.cronical = cronical;
    }

    public Integer getAllergy() {
        return allergy;
    }

    public void setAllergy(Integer allergy) {
        this.allergy = allergy;
    }

    public MedicalForm getMedicalForm() {
        return medicalForm;
    }

    public void setMedicalForm(MedicalForm medicalForm) {
        this.medicalForm = medicalForm;
    }

    public String getCodeCIM() {
        return codeCIM;
    }

    public void setCodeCIM(String codeCIM) {
        this.codeCIM = codeCIM;
    }

    @Override
    public String toString() {
        return "Diagnostic{" +
                "id=" + id +
                ", date=" + date +
                ", observations='" + observations + '\'' +
                ", code='" + code + '\'' +
                ", user=" + user +
                '}';
    }
}
