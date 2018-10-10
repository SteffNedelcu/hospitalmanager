package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Pacient;

import java.util.List;

public interface PacientDao {
    public List<Pacient> getPacients();
    public void savePacient(Pacient pacient);
    public Pacient getPacient(String cnp);
}
