package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.MedicalFormDao;
import com.stefan.hospitalmanager.dao.MedicalFormDao;
import com.stefan.hospitalmanager.entity.MedicalForm;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.service.MedicalFormService;
import com.stefan.hospitalmanager.service.MedicalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalFormServiceImpl implements MedicalFormService{

    @Autowired
    private MedicalFormDao medicalFormDao;

    public MedicalForm createMedicalForm(MedicalForm medicalForm) {
        return medicalFormDao.save(medicalForm);
    }

    public MedicalForm update(MedicalForm medicalForm) {
        return medicalFormDao.save(medicalForm);
    }

    public List<MedicalForm> findAll() {
        return medicalFormDao.findAll();
    }

    public MedicalForm findMedicalForm(Long id) {
        return medicalFormDao.findOne(id);
    }


    @Override
    public List<MedicalForm> findByStatus(Integer status) {
        return medicalFormDao.findByStatus(status);
    }

    @Override
    public List<MedicalForm> findByUser(User user) {
        return medicalFormDao.findByUser(user);
    }

    @Override
    public List<MedicalForm> findByUserAndStatus(User user, Integer integer) {
        return medicalFormDao.findByUserAndStatus(user,integer);
    }
    @Override
    public List<MedicalForm> findByUserAndStatusIn(User user, List<Integer> status) {
        return medicalFormDao.findByUserAndStatusIn(user,status);
    }
}
