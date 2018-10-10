package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.VaccineDao;
import com.stefan.hospitalmanager.dao.VitalSignsDao;
import com.stefan.hospitalmanager.entity.Vaccine;
import com.stefan.hospitalmanager.entity.VitalSigns;
import com.stefan.hospitalmanager.service.VaccineService;
import com.stefan.hospitalmanager.service.VitalSignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService{

    @Autowired
    private VaccineDao vaccineDao;

    public Vaccine create(Vaccine vaccine) {
        return vaccineDao.save(vaccine);
    }
    public Vaccine update(Vaccine vaccine) {
        return vaccineDao.save(vaccine);
    }

    public List<Vaccine> findAll() {
        return vaccineDao.findAll();
    }

    public Vaccine find(Long id) {
        return vaccineDao.findOne(id);
    }

}
