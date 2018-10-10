package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.BedDao;
import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService{

    @Autowired
    private BedDao bedDao;

    public Bed createBed(Bed bed) {
        return bedDao.save(bed);
    }

    public Bed update(Bed bed) {
        return bedDao.save(bed);
    }

    public List<Bed> findAll() {
        return bedDao.findAll();
    }

    public Bed findBed(Long id) {
        return bedDao.findOne(id);
    }

    @Override
    public List<Bed> findByRoom(Room room) {
        return bedDao.findByRoom(room);
    }

    @Override
    public List<Bed> findByStatus(Integer status) {
        return bedDao.findByStatus(status);
    }
}
