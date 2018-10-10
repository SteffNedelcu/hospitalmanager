package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BedPacient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date_ocuppied;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date_leave;
    private Integer status;

    // Floor/Room/Bed - easy to read in frontend minimal request
    private String pathToBed;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    @JsonIgnore
    private Bed bed;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;

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

    public Date getDate_ocuppied() {
        return date_ocuppied;
    }

    public void setDate_ocuppied(Date date_ocuppied) {
        this.date_ocuppied = date_ocuppied;
    }

    public Date getDate_leave() {
        return date_leave;
    }

    public void setDate_leave(Date date_leave) {
        this.date_leave = date_leave;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPathToBed() {
        return pathToBed;
    }

    public void setPathToBed(String pathToBed) {
        this.pathToBed = pathToBed;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
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
        return "BedPacient{" +
                "id=" + id +
                ", date_ocuppied=" + date_ocuppied +
                ", date_leave=" + date_leave +
                ", status=" + status +
                ", bed=" + bed +
                ", pacient=" + pacient +
                '}';
    }
}
