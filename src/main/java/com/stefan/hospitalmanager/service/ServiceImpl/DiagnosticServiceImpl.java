package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.DiagnosticDao;
import com.stefan.hospitalmanager.entity.Diagnostic;
import com.stefan.hospitalmanager.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticServiceImpl implements DiagnosticService{

    @Autowired
    private DiagnosticDao diagnosticDao;

    public Diagnostic create(Diagnostic diagnostic) {
        return diagnosticDao.save(diagnostic);
    }
    public Diagnostic update(Diagnostic diagnostic) {
        return diagnosticDao.save(diagnostic);
    }

    public List<Diagnostic> findAll() {
        return diagnosticDao.findAll();
    }

    public Diagnostic find(Long id) {
        return diagnosticDao.findOne(id);
    }
    public void delete(Diagnostic diagnostic){
        diagnosticDao.delete(diagnostic);
    }

}
