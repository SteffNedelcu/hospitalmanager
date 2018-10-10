package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Investigation;
import com.stefan.hospitalmanager.entity.Treatment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestigationDao extends CrudRepository<Investigation, Long> {
    List<Investigation> findAll();
    List<Investigation> findByStatus(Integer status);
    List<Investigation> findByStatusIn(List<Integer> status);

}
