package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.ChronicDisease;

import java.util.List;

public interface ChronicDiseaseService {
    ChronicDisease create(ChronicDisease chronicDisease);
    ChronicDisease update(ChronicDisease chronicDisease);

    List<ChronicDisease> findAll();

    ChronicDisease find(Long id);
}
