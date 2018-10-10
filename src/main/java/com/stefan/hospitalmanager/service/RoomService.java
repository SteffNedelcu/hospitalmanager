package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    List<Room> findAll();
    Room findRoom(Long id);
    List<Room> findByCode(String code);

}
