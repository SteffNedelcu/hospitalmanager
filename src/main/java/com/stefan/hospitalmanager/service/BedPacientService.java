package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.BedPacient;
import com.stefan.hospitalmanager.entity.Room;

import java.util.List;

public interface BedPacientService {
    BedPacient create(BedPacient bed);
    BedPacient update(BedPacient bed);
    List<BedPacient> findAll();
    List<BedPacient> findByStatus(Integer status);
    BedPacient find(Long id);

}
