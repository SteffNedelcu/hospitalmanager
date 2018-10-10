package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.MedicalForm;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicalFormDao extends CrudRepository<MedicalForm, Long> {
    List<MedicalForm> findAll();
    List<MedicalForm> findByStatus(Integer status);
    List<MedicalForm> findByUser(User user);
    List<MedicalForm> findByUserAndStatus(User user,Integer status);
    List<MedicalForm> findByUserAndStatusIn(User user,List<Integer> status);
}
