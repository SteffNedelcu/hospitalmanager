package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.ChronicDisease;
import com.stefan.hospitalmanager.entity.Pacient;
import com.stefan.hospitalmanager.service.ChronicDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
public class DiganosticsController {

    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ChronicDisease getDiagnostic(@PathVariable("id") long id) {

        ChronicDisease diagnostic = chronicDiseaseService.find(id);

        return diagnostic;
    }
    @RequestMapping(value = "/{id}/pacient", method = RequestMethod.GET)
    public Pacient getPacient(@PathVariable("id") long id) {

        ChronicDisease chronicDisease = chronicDiseaseService.find(id);

        return chronicDisease.getPacient();
    }
}
