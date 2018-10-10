package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.Diagnostic;

import java.util.List;

public interface DiagnosticService {
    Diagnostic create(Diagnostic diagnostic);
    List<Diagnostic> findAll();
    Diagnostic find(Long id);
    Diagnostic update(Diagnostic diagnostic);
    void delete(Diagnostic diagnostic);
}
