package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.Pacient;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("pacientDao")
public class PacientDaoImpl implements PacientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Pacient> getPacients() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //create query

        Query theQuery = currentSession.createQuery("from Pacient order by lastName");
        /// execute query and get result list
        List<Pacient> pacients = theQuery.list();
        // return the results
        return pacients;

    }

    @Override
    public void savePacient(Pacient pacient) {
         //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //save the customer
        currentSession.saveOrUpdate(pacient);
    }

    @Override
    public Pacient getPacient(String cnp) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = currentSession.createQuery("from Pacient where cnp =:cnp ")
                .setParameter("cnp", cnp);
        // now retrieve/read from database using the primary key
        Pacient thePacient = (Pacient) theQuery.uniqueResult();
        return thePacient;
    }
}
