package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Vaccine;

import java.util.List;

public interface VaccineService {
    Vaccine create(Vaccine vaccine);
    Vaccine update(Vaccine vaccine);
    List<Vaccine> findAll();
    Vaccine find(Long id);
}
