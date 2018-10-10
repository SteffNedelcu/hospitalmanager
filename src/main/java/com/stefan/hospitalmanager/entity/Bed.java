package com.stefan.hospitalmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @OneToMany(mappedBy = "bed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BedPacient> bedPacients;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<BedPacient> getBedPacients() {
        return bedPacients;
    }

    public void setBedPacients(List<BedPacient> bedPacients) {
        this.bedPacients = bedPacients;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", room=" + room +
                ", bedPacients=" + bedPacients +
                '}';
    }
}
