package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.TreatmentDao;
import com.stefan.hospitalmanager.dao.VitalSignsDao;
import com.stefan.hospitalmanager.entity.Treatment;
import com.stefan.hospitalmanager.entity.VitalSigns;
import com.stefan.hospitalmanager.service.TreatmentService;
import com.stefan.hospitalmanager.service.VitalSignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitalSignsServiceImpl implements VitalSignsService{

    @Autowired
    private VitalSignsDao vitalSignsDao;

    public VitalSigns create(VitalSigns vitalSigns) {
        return vitalSignsDao.save(vitalSigns);
    }
    public VitalSigns update(VitalSigns vitalSigns) {
        return vitalSignsDao.save(vitalSigns);
    }

    public List<VitalSigns> findAll() {
        return vitalSignsDao.findAll();
    }

    public VitalSigns find(Long id) {
        return vitalSignsDao.findOne(id);
    }

}
