package com.stefan.hospitalmanager.service.ServiceImpl;

import com.stefan.hospitalmanager.dao.PacientDao;
import com.stefan.hospitalmanager.entity.Pacient;
import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacientServiceImpl implements PacientService {
    @Autowired
    private PacientDao pacientDAO;

    @Override
    public List<Pacient> getPacients() {
        return pacientDAO.getPacients();
    }

    @Override
    public void savePacient(Pacient thePacient) {
        pacientDAO.savePacient(thePacient);
    }

    @Override
    public Pacient getPacient(String cnp) {
        return pacientDAO.getPacient(cnp);
    }
    public boolean checkPacientExists(String Cnp){
        if (checkCNP(Cnp)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCNP(String cnp) {
        if (null != findByCnp(cnp)) {
            return true;
        }

        return false;
    }


    public Pacient findByCnp(String cnp) {
        return pacientDAO.getPacient(cnp);
    }

}
