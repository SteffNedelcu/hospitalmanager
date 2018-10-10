package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Indexed
@Entity
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Field
    private String cnp;
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @Field
    private String email;
    @Field
    private String phone;
    private String job;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;
    private String sex;
    private Integer statusAsigurat;
    private String bloodType;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChronicDisease> chronicDiseaseList;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AllergicDisease> allergicDiseaseList;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vaccine> vaccineList;


    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MedicalForm> medicalFormList;

    public Pacient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatusAsigurat() {
        return statusAsigurat;
    }

    public void setStatusAsigurat(Integer statusAsigurat) {
        this.statusAsigurat = statusAsigurat;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<ChronicDisease> getChronicDiseaseList() {
        return chronicDiseaseList;
    }

    public void setChronicDiseaseList(List<ChronicDisease> chronicDiseaseList) {
        this.chronicDiseaseList = chronicDiseaseList;
    }

    public List<AllergicDisease> getAllergicDiseaseList() {
        return allergicDiseaseList;
    }

    public void setAllergicDiseaseList(List<AllergicDisease> allergicDiseaseList) {
        this.allergicDiseaseList = allergicDiseaseList;
    }

    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    public List<MedicalForm> getMedicalFormList() {
        return medicalFormList;
    }

    public void setMedicalFormList(List<MedicalForm> medicalFormList) {
        this.medicalFormList = medicalFormList;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", cnp='" + cnp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", job='" + job + '\'' +
                ", birthdate=" + birthdate +
                ", sex='" + sex + '\'' +
                ", statusAsigurat=" + statusAsigurat +
                ", bloodType='" + bloodType + '\'' +
                ", chronicDiseaseList=" + chronicDiseaseList +
                ", allergicDiseaseList=" + allergicDiseaseList +
                ", vaccineList=" + vaccineList +
                ", medicalFormList=" + medicalFormList +
                '}';
    }
}
