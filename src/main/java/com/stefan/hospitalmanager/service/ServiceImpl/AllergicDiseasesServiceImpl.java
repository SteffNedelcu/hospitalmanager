package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.AllergicDiseaseDao;
import com.stefan.hospitalmanager.dao.ChronicDiseaseDao;
import com.stefan.hospitalmanager.entity.AllergicDisease;
import com.stefan.hospitalmanager.entity.ChronicDisease;
import com.stefan.hospitalmanager.service.AllergicDiseaseService;
import com.stefan.hospitalmanager.service.ChronicDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergicDiseasesServiceImpl implements AllergicDiseaseService {

    @Autowired
    private AllergicDiseaseDao allergicDiseaseDao;

    public AllergicDisease create(AllergicDisease allergicDisease) {
        return allergicDiseaseDao.save(allergicDisease);
    }
    public AllergicDisease update(AllergicDisease allergicDisease) {
        return allergicDiseaseDao.save(allergicDisease);
    }
    public List<AllergicDisease> findAll() {
        return allergicDiseaseDao.findAll();
    }

    public AllergicDisease find(Long id) {
        return allergicDiseaseDao.findOne(id);
    }

}
