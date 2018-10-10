package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.BedDao;
import com.stefan.hospitalmanager.dao.RoomDao;
import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import com.stefan.hospitalmanager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;
    @Override
    public Room createRoom(Room room) {
        return roomDao.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomDao.findAll();
    }

    @Override
    public Room findRoom(Long id) {
        return roomDao.findOne(id);
    }

    @Override
    public List<Room> findByCode(String code) {
        return  roomDao.findByCode(code);
    }
}
