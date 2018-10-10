package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.InvestigationDao;
import com.stefan.hospitalmanager.entity.Investigation;
import com.stefan.hospitalmanager.service.InvestigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestigationServiceImpl implements InvestigationService {

    @Autowired
    private InvestigationDao investigationDao;

    public Investigation create(Investigation investigation) {
        return investigationDao.save(investigation);
    }
    public Investigation update(Investigation investigation) {
        return investigationDao.save(investigation);
    }

    public List<Investigation> findAll() {
        return investigationDao.findAll();
    }

    public Investigation find(Long id) {
        return investigationDao.findOne(id);
    }
    public List<Investigation> findByStatus(Integer status) {
        return investigationDao.findByStatus(status);
    }
    public List<Investigation> findByStatusIn(List<Integer> status) {
        return investigationDao.findByStatusIn(status);
    }
    public void delete(Investigation investigation){
        investigationDao.delete(investigation);
    }


}
