package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Treatment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TreatmentDao extends CrudRepository<Treatment, Long> {
    List<Treatment> findAll();

}
