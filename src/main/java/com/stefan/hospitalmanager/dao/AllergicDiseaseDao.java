package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.AllergicDisease;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AllergicDiseaseDao extends CrudRepository<AllergicDisease, Long> {
    List<AllergicDisease> findAll();
}
