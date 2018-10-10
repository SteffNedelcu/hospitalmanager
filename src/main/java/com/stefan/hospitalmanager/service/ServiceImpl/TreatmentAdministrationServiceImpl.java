package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.TreatmentAdministrationDao;
import com.stefan.hospitalmanager.dao.TreatmentDao;
import com.stefan.hospitalmanager.entity.Treatment;
import com.stefan.hospitalmanager.entity.TreatmentAdministration;
import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.service.TreatmentAdministrationService;
import com.stefan.hospitalmanager.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentAdministrationServiceImpl implements TreatmentAdministrationService{

    @Autowired
    private TreatmentAdministrationDao treatmentAdministrationDao;

     public List<TreatmentAdministration> findAll() {
        return treatmentAdministrationDao.findAll();
    }

    @Override
    public TreatmentAdministration create(TreatmentAdministration treatment) {
        return treatmentAdministrationDao.save(treatment);
    }

    @Override
    public List<TreatmentAdministration> findByUser(User user) {
        return treatmentAdministrationDao.findByUser(user);
    }
}
