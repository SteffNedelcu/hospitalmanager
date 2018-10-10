package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Diagnostic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosticDao extends CrudRepository<Diagnostic, Long> {
    List<Diagnostic> findAll();
}
