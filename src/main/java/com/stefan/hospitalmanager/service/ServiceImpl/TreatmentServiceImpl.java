package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.DiagnosticDao;
import com.stefan.hospitalmanager.dao.TreatmentDao;
import com.stefan.hospitalmanager.entity.Diagnostic;
import com.stefan.hospitalmanager.entity.Treatment;
import com.stefan.hospitalmanager.service.DiagnosticService;
import com.stefan.hospitalmanager.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Autowired
    private TreatmentDao treatmentDao;

    public Treatment create(Treatment treatment) {
        return treatmentDao.save(treatment);
    }
    public Treatment update(Treatment treatment) {
        return treatmentDao.save(treatment);
    }

    public List<Treatment> findAll() {
        return treatmentDao.findAll();
    }

    public Treatment find(Long id) {
        return treatmentDao.findOne(id);
    }
    public void delete(Treatment treatment){
        treatmentDao.delete(treatment);
    }

}
