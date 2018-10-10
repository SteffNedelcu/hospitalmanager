package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.BedPacientDao;
import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.BedPacient;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedPacientService;
import com.stefan.hospitalmanager.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedPacientServiceImpl implements BedPacientService {

    @Autowired
    private BedPacientDao bedPacientDao;

    public BedPacient create(BedPacient bedPacient) {
        return bedPacientDao.save(bedPacient);
    }

    public List<BedPacient> findAll() {
        return bedPacientDao.findAll();
    }

    public BedPacient find(Long id) {
        return bedPacientDao.findOne(id);
    }

    @Override
    public List<BedPacient> findByStatus(Integer status) {
        return bedPacientDao.findByStatus(status);
    }
    @Override
    public BedPacient update(BedPacient bedPacient) {
        return bedPacientDao.save(bedPacient);
    }

}
