package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.ChronicDisease;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChronicDiseaseDao extends CrudRepository<ChronicDisease, Long> {
    List<ChronicDisease> findAll();
}
