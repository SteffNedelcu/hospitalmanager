package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.VitalSigns;

import java.util.List;

public interface VitalSignsService {
    VitalSigns create(VitalSigns vitalSigns);

    List<VitalSigns> findAll();

    VitalSigns find(Long id);
}
