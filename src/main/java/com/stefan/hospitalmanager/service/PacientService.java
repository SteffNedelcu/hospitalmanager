package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Pacient;

import java.util.List;

public interface PacientService {
    public List<Pacient> getPacients();

    public void savePacient(Pacient thePacient);

    public Pacient getPacient(String cnp);
    boolean checkPacientExists(String cnp);

    boolean checkCNP(String cnp);

}
