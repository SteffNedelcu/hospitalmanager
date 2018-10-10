package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.BedDao;
import com.stefan.hospitalmanager.dao.FloorDao;
import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Floor;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import com.stefan.hospitalmanager.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService{

    @Autowired
    private FloorDao floorDao;

    public Floor createFloor(Floor floor) {
        return floorDao.save(floor);
    }

    public List<Floor> findAll() {
        return floorDao.findAll();
    }

    public Floor findFloor(Long id) {
        return floorDao.findOne(id);
    }

    @Override
    public List<Floor> findByStatus(Integer status) {
        return floorDao.findByStatus(status);
    }
}
