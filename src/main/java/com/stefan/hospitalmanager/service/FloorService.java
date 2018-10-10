package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Floor;
import com.stefan.hospitalmanager.entity.Room;

import java.util.List;

public interface FloorService {
    Floor createFloor(Floor floor);
    List<Floor> findAll();
    Floor findFloor(Long id);
    List<Floor> findByStatus(Integer status);

}
