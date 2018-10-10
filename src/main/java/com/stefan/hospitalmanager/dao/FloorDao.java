package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Floor;
import com.stefan.hospitalmanager.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FloorDao extends CrudRepository<Floor, Long> {
    List<Floor> findAll();
    List<Floor> findByStatus(Integer status);
}
