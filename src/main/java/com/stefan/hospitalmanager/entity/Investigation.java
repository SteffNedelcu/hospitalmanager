package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Investigation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String codeCIM;
    //cod intern al sistemului Hash unic
    private String code;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateRequest;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateSampling;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCheckin;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCheckout;
    private String observations;
    private String fileName;

    //1-request;2-recorltata;3- ajuns la laborator; 4 - gata
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medical_form_id")
    @JsonIgnore
    private MedicalForm medicalForm;

    public Investigation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateSampling() {
        return dateSampling;
    }

    public void setDateSampling(Date dateSampling) {
        this.dateSampling = dateSampling;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Date getDateCheckin() {
        return dateCheckin;
    }

    public void setDateCheckin(Date dateCheckin) {
        this.dateCheckin = dateCheckin;
    }

    public Date getDateCheckout() {
        return dateCheckout;
    }

    public void setDateCheckout(Date dateCheckout) {
        this.dateCheckout = dateCheckout;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Investigation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeCIM='" + codeCIM + '\'' +
                ", code='" + code + '\'' +
                ", dateRequest=" + dateRequest +
                ", dateCheckin=" + dateCheckin +
                ", dateCheckout=" + dateCheckout +
                ", observations='" + observations + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", medicalForm=" + medicalForm +
                '}';
    }
}
