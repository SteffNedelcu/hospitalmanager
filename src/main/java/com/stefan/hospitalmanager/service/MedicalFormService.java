package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.MedicalForm;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.entity.User;

import java.util.List;

public interface MedicalFormService {
    MedicalForm createMedicalForm(MedicalForm bed);
    MedicalForm update(MedicalForm bed);
    List<MedicalForm> findAll();
    MedicalForm findMedicalForm(Long id);
    List<MedicalForm> findByStatus(Integer status);
    List<MedicalForm> findByUser(User user);
    List<MedicalForm> findByUserAndStatus(User user,Integer integer);
    List<MedicalForm> findByUserAndStatusIn(User user,List<Integer> status);

}
