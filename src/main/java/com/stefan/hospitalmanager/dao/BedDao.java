package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Diagnostic;
import com.stefan.hospitalmanager.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BedDao extends CrudRepository<Bed, Long> {
    List<Bed> findAll();
    List<Bed> findByStatus(Integer status);
    List<Bed> findByRoom(Room room);
}
