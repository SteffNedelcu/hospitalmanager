package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Vaccine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VaccineDao extends CrudRepository<Vaccine, Long> {
    List<Vaccine> findAll();
}
