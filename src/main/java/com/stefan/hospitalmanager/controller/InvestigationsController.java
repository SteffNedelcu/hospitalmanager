package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.Floor;
import com.stefan.hospitalmanager.entity.Investigation;
import com.stefan.hospitalmanager.entity.Treatment;
import com.stefan.hospitalmanager.service.InvestigationService;
import com.stefan.hospitalmanager.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.constraints.EAN.Type.EAN13;

@RestController
@RequestMapping("/investigations")
public class InvestigationsController {

    @Autowired
    InvestigationService investigationService;
    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Investigation> index() {

        List<Investigation> investigationList = investigationService.findAll();

        return investigationList;
    }
    @PreAuthorize("hasRole('ROLE_NURSE') or hasRole('ROLE_LAB') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Investigation getInvestigation(@PathVariable("id") Long id, Principal principal,HttpServletRequest request) {

        Investigation investigation = investigationService.find(id);
        if(investigation.getStatus() == 2){
            if(request.isUserInRole("ROLE_LAB")){
                investigation.setStatus(3);
                investigationService.update(investigation);
            }

        }
        return investigation;
    }
    @PreAuthorize("hasRole('ROLE_NURSE') or hasRole('ROLE_LAB') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/by-status/{status}", method = RequestMethod.GET)
    public List<Investigation> getInvestigationsByStatus(@PathVariable("status") Integer id) {

        List<Investigation> investigationList = investigationService.findByStatus(id);

        return investigationList;
    }

    @PreAuthorize("hasRole('ROLE_NURSE') or hasRole('ROLE_LAB') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/change-status", method = RequestMethod.PUT)
    public ResponseEntity<Investigation> changeInvestigation(@PathVariable("id") Long id, @RequestBody Investigation investigation, Principal principal){

        Investigation dbInvestigation = investigationService.find(investigation.getId());
        Integer status = investigation.getStatus();
        if(status != dbInvestigation.getStatus() && status>dbInvestigation.getStatus() ){
            Date date = new Date();
            if(status==2){
                dbInvestigation.setDateSampling(date);
            }else if(status==3){
                dbInvestigation.setDateCheckin(date);
            }else if(status==4){
                dbInvestigation.setDateCheckout(date);
            }
            dbInvestigation.setStatus(status);
            investigationService.update(dbInvestigation);
        }
        return new ResponseEntity<Investigation>(dbInvestigation, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_NURSE') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-code", method = RequestMethod.PUT)
    public ResponseEntity<Investigation> editCode(@RequestBody Investigation investigation, Principal principal){
        System.out.println("editCode");
        Investigation dbInvestigation = investigationService.find(investigation.getId());
        if(!investigation.getCode().isEmpty()){
            dbInvestigation.setCode(investigation.getCode());
            dbInvestigation.setStatus(2);
            investigationService.update(dbInvestigation);
        }
        return new ResponseEntity<Investigation>(dbInvestigation, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_NURSE') or hasRole('ROLE_LAB')  or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<Investigation> edit(@RequestBody Investigation investigation, Principal principal){

        Investigation dbInvestigation = investigationService.find(investigation.getId());
        if(!investigation.getObservations().isEmpty()){
            dbInvestigation.setObservations(investigation.getObservations());
            investigationService.update(dbInvestigation);
        }
        return new ResponseEntity<Investigation>(dbInvestigation, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_LAB') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public ResponseEntity<Investigation> uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Principal principal){

        Investigation dbInvestigation = investigationService.find(id);
       // TODO
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            dbInvestigation.setFileName(file.getOriginalFilename());
            dbInvestigation.setStatus(4);
            investigationService.update(dbInvestigation);
            return new ResponseEntity<Investigation>(dbInvestigation, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<Investigation>(dbInvestigation, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @RequestMapping(value = "/{id}/post", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@PathVariable("id") Long id,@RequestParam("file") MultipartFile file) {
        String message = "";
        Investigation dbInvestigation = investigationService.find(id);
        System.out.println("Aici");
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            dbInvestigation.setFileName(file.getOriginalFilename());
            investigationService.update(dbInvestigation);
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(InvestigationsController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
