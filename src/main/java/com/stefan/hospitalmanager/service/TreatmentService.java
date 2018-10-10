package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Diagnostic;
import com.stefan.hospitalmanager.entity.Treatment;

import java.util.List;

public interface TreatmentService {
    Treatment create(Treatment treatment);
    Treatment update(Treatment treatment);
    List<Treatment> findAll();
    Treatment find(Long id);
    void delete(Treatment treatment);
}
