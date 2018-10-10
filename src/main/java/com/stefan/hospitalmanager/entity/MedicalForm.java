package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefan.hospitalmanager.config.HospitalConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class MedicalForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCheckUp;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCheckIn;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCheckOut;
    private String observationsCheckUp;
    private String observationsCheckIn;
    private String observationsCheckOut;
    private Integer status;
    private String hospitalInfo;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    @JsonIgnore
    private Pacient pacient;

    @OneToMany(mappedBy = "medicalForm")
    private List<BedPacient> bedPacientList;

    @OneToMany(mappedBy = "medicalForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Treatment> treatmentList;

    @OneToMany(mappedBy = "medicalForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnostic> diagnosticList;

    @OneToMany(mappedBy = "medicalForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VitalSigns> vitalSignsList;

    @OneToMany(mappedBy = "medicalForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Investigation> investigationList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateCheckUp() {
        return dateCheckUp;
    }

    public void setDateCheckUp(Date dateCheckUp) {
        this.dateCheckUp = dateCheckUp;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public String getObservationsCheckUp() {
        return observationsCheckUp;
    }

    public void setObservationsCheckUp(String observationsCheckUp) {
        this.observationsCheckUp = observationsCheckUp;
    }

    public String getObservationsCheckIn() {
        return observationsCheckIn;
    }

    public void setObservationsCheckIn(String observationsCheckIn) {
        this.observationsCheckIn = observationsCheckIn;
    }

    public String getObservationsCheckOut() {
        return observationsCheckOut;
    }

    public void setObservationsCheckOut(String observationsCheckOut) {
        this.observationsCheckOut = observationsCheckOut;
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

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public List<BedPacient> getBedPacientList() {
        return bedPacientList;
    }

    public void setBedPacientList(List<BedPacient> bedPacientList) {
        this.bedPacientList = bedPacientList;
    }

    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public List<Diagnostic> getDiagnosticList() {
        return diagnosticList;
    }

    public void setDiagnosticList(List<Diagnostic> diagnosticList) {
        this.diagnosticList = diagnosticList;
    }

    public List<VitalSigns> getVitalSignsList() {
        return vitalSignsList;
    }

    public void setVitalSignsList(List<VitalSigns> vitalSignsList) {
        this.vitalSignsList = vitalSignsList;
    }

    public List<Investigation> getInvestigationList() {
        return investigationList;
    }

    public void setInvestigationList(List<Investigation> investigationList) {
        this.investigationList = investigationList;
    }

    public String getHospitalInfo() {
        return hospitalInfo;
    }

    public void setHospitalInfo(String hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    @Override
    public String toString() {
        return "MedicalForm{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", dateCheckUp=" + dateCheckUp +
                ", dateCheckIn=" + dateCheckIn +
                ", date_check_out=" + dateCheckOut +
                ", observationsCheckUp='" + observationsCheckUp + '\'' +
                ", observationsCheckIn='" + observationsCheckIn + '\'' +
                ", observationsCheckOut='" + observationsCheckOut + '\'' +
                ", status=" + status +
                ", hospitalInfo='" + hospitalInfo + '\'' +
                ", user=" + user +
                ", pacient=" + pacient +
                ", treatmentList=" + treatmentList +
                ", diagnosticList=" + diagnosticList +
                ", vitalSignsList=" + vitalSignsList +
                ", investigationList=" + investigationList +
                '}';
    }
}
