package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Room;

import java.util.List;

public interface BedService {
    Bed createBed(Bed bed);
    Bed update(Bed bed);
    List<Bed> findAll();
    Bed findBed(Long id);
    List<Bed> findByRoom(Room room);
    List<Bed> findByStatus(Integer status);

}
