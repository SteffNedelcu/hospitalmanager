package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomDao extends CrudRepository<Room, Long> {
    List<Room> findAll();
    List<Room> findById(Long id);
    List<Room> findByCode(String code);
}
