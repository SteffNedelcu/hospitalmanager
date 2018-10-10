package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.TreatmentAdministration;
import com.stefan.hospitalmanager.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TreatmentAdministrationDao extends CrudRepository<TreatmentAdministration, Long> {
    List<TreatmentAdministration> findAll();
    List<TreatmentAdministration> findByUser(User user);
}
