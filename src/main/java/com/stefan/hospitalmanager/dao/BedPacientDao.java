package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.BedPacient;
import com.stefan.hospitalmanager.entity.Pacient;
import com.stefan.hospitalmanager.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BedPacientDao extends CrudRepository<BedPacient, Long> {
    List<BedPacient> findAll();
    List<BedPacient> findByStatus(Integer status);
    List<BedPacient> findByPacient(Pacient pacient);
    List<BedPacient> findByBed(Bed bed);
}
