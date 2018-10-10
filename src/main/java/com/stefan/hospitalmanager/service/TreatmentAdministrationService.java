package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Treatment;
import com.stefan.hospitalmanager.entity.TreatmentAdministration;
import com.stefan.hospitalmanager.entity.User;

import java.util.List;

public interface TreatmentAdministrationService {
    TreatmentAdministration create(TreatmentAdministration treatment);
    List<TreatmentAdministration> findAll();
    List<TreatmentAdministration> findByUser(User user);
}
