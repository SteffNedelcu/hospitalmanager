package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Investigation;
import com.stefan.hospitalmanager.entity.Treatment;

import java.util.List;

public interface InvestigationService {
    Investigation create(Investigation investigation);
    Investigation update(Investigation investigation);
    List<Investigation> findAll();
    Investigation find(Long id);
    List<Investigation> findByStatus(Integer status);
    List<Investigation> findByStatusIn(List<Integer> status);
    void delete(Investigation investigation);
}
