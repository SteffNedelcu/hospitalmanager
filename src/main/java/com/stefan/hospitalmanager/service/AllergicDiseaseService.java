package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.AllergicDisease;
import com.stefan.hospitalmanager.entity.ChronicDisease;

import java.util.List;

public interface AllergicDiseaseService {
    AllergicDisease create(AllergicDisease allergicDisease);
    AllergicDisease update(AllergicDisease allergicDisease);
    List<AllergicDisease> findAll();
    AllergicDisease find(Long id);
}
