package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.ChronicDiseaseDao;
import com.stefan.hospitalmanager.entity.ChronicDisease;
import com.stefan.hospitalmanager.service.ChronicDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChronicDiseasesServiceImpl implements ChronicDiseaseService {

    @Autowired
    private ChronicDiseaseDao chronicDiseaseDao;

    public ChronicDisease create(ChronicDisease chronicDisease) {
        return chronicDiseaseDao.save(chronicDisease);
    }
    public ChronicDisease update(ChronicDisease chronicDisease) {
        return chronicDiseaseDao.save(chronicDisease);
    }
    public List<ChronicDisease> findAll() {
        return chronicDiseaseDao.findAll();
    }

    public ChronicDisease find(Long id) {
        return chronicDiseaseDao.findOne(id);
    }

}
