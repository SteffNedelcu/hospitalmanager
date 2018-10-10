package com.stefan.hospitalmanager.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefan.hospitalmanager.config.HospitalConfig;
import com.stefan.hospitalmanager.entity.*;
import com.stefan.hospitalmanager.helper.HashText;
import com.stefan.hospitalmanager.service.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/medical-forms")
public class MedicalFormsController {

    @Autowired
    private MedicalFormService medicalFormService;

    @Autowired
    private BedService bedService;

    @Autowired
    private BedPacientService bedPacientService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiagnosticService diagnosticService;
    @Autowired
    private TreatmentService treatmentService;
    @Autowired
    private VitalSignsService vitalSignsService;
    @Autowired
    private InvestigationService investigationService;
    @Autowired
    private TreatmentAdministrationService treatmentAdministrationService;
    @Autowired
    private ChronicDiseaseService chronicDiseaseService;
    @Autowired
    private AllergicDiseaseService allergicDiseaseService;
    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private HospitalConfig hospitalConfig;

    private final String USER_AGENT = "Mozilla/5.0";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MedicalForm getMedicalForm(@PathVariable("id") long id) {

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);

        return medicalForm;
    }
    @RequestMapping(value = "/{id}/pacient-info", method = RequestMethod.GET)
    public Pacient getMedicalFormPacient(@PathVariable("id") long id) {

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        return medicalForm.getPacient();
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.PUT)
    public ResponseEntity<MedicalForm> editMedicalForm(@PathVariable("id") long id, @RequestBody MedicalForm newMedicalForm, Principal principal) {
        System.out.println("MEDICAL FROM");

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);

        medicalForm.setObservationsCheckUp(newMedicalForm.getObservationsCheckUp());
        medicalForm.setObservationsCheckIn(newMedicalForm.getObservationsCheckIn());
        medicalForm.setObservationsCheckOut(newMedicalForm.getObservationsCheckOut());

        medicalFormService.update(medicalForm);
        return new ResponseEntity<MedicalForm>(medicalForm, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/add-bed", method = RequestMethod.POST)
    public ResponseEntity<BedPacient> addBed(@PathVariable("id") long id, @RequestBody Bed reqBed, Principal principal) {
        System.out.println("MEDICAL FROM add Brd");
        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        Bed bed = bedService.findBed(reqBed.getId());
        BedPacient bedPacient = null;
        if(bed.getStatus().equals(0)){
            bedPacient = new BedPacient();
            bedPacient.setMedicalForm(medicalForm);
            bedPacient.setPacient(medicalForm.getPacient());
            bedPacient.setBed(bed);

            //setting the date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            String dateToString = dateFormat.format(date);

            bedPacient.setDate_ocuppied(date);
            bedPacient.setStatus(1);

            bed.setStatus(1);

            bedService.update(bed);

            Room roomOfBed = bed.getRoom();
            Floor floor = roomOfBed.getFloor();

            bedPacient.setPathToBed(floor.getCode() + "/" + roomOfBed.getCode() + " [" + roomOfBed.getSex() + "]/"+bed.getCode());
            bedPacientService.create(bedPacient);
        }else{

            return new ResponseEntity<BedPacient>(bedPacient, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<BedPacient>(bedPacient, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/change-status", method = RequestMethod.PUT )
    public ResponseEntity<MedicalForm> changeStatus(@PathVariable("id") long id, @RequestBody MedicalForm newMedicalForm, Principal principal) {
        System.out.println("MEDICAL FROM CHANGE STATUS");

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        Integer newStatus = newMedicalForm.getStatus();
        if(newStatus>medicalForm.getStatus()){
            medicalForm.setStatus(newStatus);
            if(newStatus == 2){
                //setting the date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date();
                medicalForm.setDateCheckIn(date);
                medicalForm.setObservationsCheckIn("Observatii diagnostic prezumtiv \n\n"+medicalForm.getObservationsCheckUp());
            }
            if(newStatus == 3) {
                //TODO: status 3 externarea
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date();
                medicalForm.setDateCheckOut(date);

                // add all observations
                String observation = medicalForm.getObservationsCheckIn();

                //add observations from medical investigations
                for(Investigation investigation: medicalForm.getInvestigationList()){
                    observation = observation + " \n\n" +
                            investigation.getName()+ " \n"+
                    investigation.getObservations();
                }
                medicalForm.setObservationsCheckOut(""+observation);

                // 1. mut chronic, alergii si vaccinuri pe pacient
                    this.addNewCoditionsToPacient(medicalForm.getPacient(),medicalForm);

                // 2. trimit fisa catre serverul central
                  this.pushDataToMainframe(medicalForm.getPacient(),medicalForm);

                  // 3. eliberez patul
                this.freeBed(medicalForm);


            }
            medicalFormService.update(medicalForm);
            return new ResponseEntity<MedicalForm>(medicalForm, HttpStatus.OK);
        }
        return new ResponseEntity<MedicalForm>(medicalForm, HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/{id}/diagnostics/add", method = RequestMethod.POST)
    public ResponseEntity<Diagnostic> addDiagnostic(@PathVariable("id") Long id, @RequestBody Diagnostic diagnostic, Principal principal) {

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        User user = userService.findByUsername(principal.getName());


        // VALIDARI TODO daca userull are rolul de doctor, este in lucru
        List<String> errorsList = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);
        String hashString = dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
        HashText hashText = new HashText(hashString);
        String hash = "";
        try {
            hash = hashText.sha1();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
            errorsList.add(ex.toString());
        }

        //daca nu am erori adaug
        if(!errorsList.isEmpty()){
            return new ResponseEntity<Diagnostic>(new Diagnostic(), HttpStatus.BAD_REQUEST);
        }

        diagnostic.setUser(user);
        diagnostic.setStatus(1);
        diagnostic.setMedicalForm(medicalForm);
        diagnostic.setCode(hash);
        diagnostic.setDate(date);
        diagnosticService.create(diagnostic);

        return new ResponseEntity<Diagnostic>(diagnostic, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/treatment/add", method = RequestMethod.POST)
    public ResponseEntity<Treatment> addTreatment(@PathVariable("id") Long id, @RequestBody Treatment treatment, Principal principal) {

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        User user = userService.findByUsername(principal.getName());

        // VALIDARI TODO daca userull are rolul de doctor, este in lucru
        List<String> errorsList = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);
        String hashString = "treatment"+ dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
        HashText hashText = new HashText(hashString);
        String hash = "";
        try {
            hash = hashText.sha1();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
            errorsList.add(ex.toString());
        }

        //daca nu am erori adaug
        if(!errorsList.isEmpty()){
            return new ResponseEntity<Treatment>(new Treatment(), HttpStatus.BAD_REQUEST);
        }

        treatment.setUser(user);
        treatment.setStatus(1);
        treatment.setMedicalForm(medicalForm);
        treatment.setCode(hash);
        treatment.setDate(date);
        treatmentService.create(treatment);
        return new ResponseEntity<Treatment>(treatment, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/investigation/add", method = RequestMethod.POST)
    public ResponseEntity<Investigation> addInvestigation(@PathVariable("id") Long id, @RequestBody Investigation investigation, Principal principal) {
        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        User user = userService.findByUsername(principal.getName());

        // VALIDARI TODO daca userull are rolul de doctor, este in lucru
        List<String> errorsList = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);

        //daca nu am erori adaug
        if(!errorsList.isEmpty()){
            return new ResponseEntity<Investigation>(new Investigation(), HttpStatus.BAD_REQUEST);
        }

        investigation.setUser(user);
        investigation.setStatus(1);
        investigation.setMedicalForm(medicalForm);
        investigation.setDateRequest(date);
        investigationService.create(investigation);

        return new ResponseEntity<Investigation>(investigation, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/vital-signs/add", method = RequestMethod.POST)
    public ResponseEntity<VitalSigns> addVital(@PathVariable("id") Long id, @RequestBody VitalSigns vitalSigns, Principal principal) {
        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        User user = userService.findByUsername(principal.getName());

        // TODO VALIDARI  daca userull are rolul de doctor, este in lucru
        List<String> errorsList = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);
        String hashString = dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
        HashText hashText = new HashText(hashString);
        String hash = "";
        try {
            hash = hashText.sha1();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
            errorsList.add(ex.toString());
        }

        //daca nu am erori adaug
        if(!errorsList.isEmpty()){
            return new ResponseEntity<VitalSigns>(new VitalSigns(), HttpStatus.BAD_REQUEST);
        }

        vitalSigns.setUser(user);
        vitalSigns.setMedicalForm(medicalForm);
        vitalSigns.setDate(date);
        vitalSignsService.create(vitalSigns);

        return new ResponseEntity<VitalSigns>(vitalSigns, HttpStatus.OK);
    }
    @RequestMapping(value = "/bed-pacient/{id}", method = RequestMethod.GET)
    public BedPacient addBedPacient(@PathVariable("id") Long id, Principal principal) {
        // TODO verificari
        BedPacient bedPacient = bedPacientService.find(id);
        return bedPacient;
    }
    @RequestMapping(value = "/{id}/vital-signs", method = RequestMethod.GET)
    public List<VitalSigns> getVitalSigns(@PathVariable("id") long id) {

        MedicalForm medicalForm = medicalFormService.findMedicalForm(id);
        return medicalForm.getVitalSignsList();
    }

    @RequestMapping(value = "/user/{status}", method = RequestMethod.GET)
    public List<MedicalForm> getMedicalFormByUser(@PathVariable("status") Integer status,Principal principal) {
        //TODO sa fac lista pentru findByUserAndStatusIn
        User user = userService.findByUsername(principal.getName());
        List<MedicalForm> medicalForms = new ArrayList<>();
        if(Arrays.asList(1, 2, 3).contains(status) ){
            medicalForms = medicalFormService.findByUserAndStatus(user,status);
        }else if(status == 12){ // fisele active
            //List<Integer> statuses = new ArrayList<Integer>(Arrays.asList(1, 2));
            medicalForms = medicalFormService.findByUserAndStatusIn(user,Arrays.asList(1, 2));
        }else if(status == 123){ // fisele active
            //List<Integer> statuses = new ArrayList<Integer>(Arrays.asList(1, 2));
            medicalForms = medicalFormService.findByUser(user);
        }


        return medicalForms;
    }
    @RequestMapping(value = "/user/{status}/pacients", method = RequestMethod.GET)
    public Map<Long,Pacient> getMedicalFormPacientsByUser(@PathVariable("status") Integer status,Principal principal) {

        User user = userService.findByUsername(principal.getName());
        List<MedicalForm> medicalForms = new ArrayList<>();
        if(Arrays.asList(1, 2, 3).contains(status) ){
            medicalForms = medicalFormService.findByUserAndStatus(user,status);
        }else if(status == 12){ // fisele active
            //List<Integer> statuses = new ArrayList<Integer>(Arrays.asList(1, 2));
            medicalForms = medicalFormService.findByUserAndStatusIn(user,Arrays.asList(1, 2));
        }else if(status == 123){ // toate active
            //List<Integer> statuses = new ArrayList<Integer>(Arrays.asList(1, 2));
            medicalForms = medicalFormService.findByUser(user);
        }
        Map<Long,Pacient> pacientsMap = new HashMap<>();

        for(MedicalForm medicalForm : medicalForms){
            pacientsMap.put(medicalForm.getId(),medicalForm.getPacient());
        }

        return pacientsMap;
    }
    @PreAuthorize("hasRole('ROLE_MEDIC')")
    @RequestMapping(value = "/delete/diagnostic/{id}", method = RequestMethod.DELETE)
    public Boolean deleteDiagnostic(@PathVariable("id") Long id,Principal principal){

        Diagnostic diagnostic = diagnosticService.find(id);
        try{
            System.out.println("deleteDiagnostic");
            diagnosticService.delete(diagnostic);
        }catch (RuntimeException e){
            return false;
        }
        return true;
    }
    @PreAuthorize("hasRole('ROLE_MEDIC')")
    @RequestMapping(value = "/delete/treatment/{id}", method = RequestMethod.DELETE)
    public Boolean deleteTreatment(@PathVariable("id") Long id,Principal principal){

        Treatment treatment = treatmentService.find(id);
        if(treatment.getTreatmentAdministrationList().size() == 0 ){
            try{
                System.out.println("deleteTreatment");
                treatmentService.delete(treatment);
            }catch (RuntimeException e){
                return false;
            }
        }else{
            return false;
        }

        return true;
    }
    @PreAuthorize("hasRole('ROLE_MEDIC')")
    @RequestMapping(value = "/delete/investigation/{id}", method = RequestMethod.DELETE)
    public Boolean deleteInvestigation(@PathVariable("id") Long id,Principal principal){

        Investigation investigation = investigationService.find(id);
        if(investigation.getStatus() == 1){ //doar daca nu a apucat sa fie facuta/recoltata
            try{
                System.out.println("Deleted Investigation");
                investigationService.delete(investigation);
            }catch (RuntimeException e){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/{id}/treatment-administration/add", method = RequestMethod.POST)
    public ResponseEntity<TreatmentAdministration> addVital(@PathVariable("id") Long id, @RequestBody Treatment newTreatment, Principal principal) {
        System.out.println(newTreatment);
        User user = userService.findByUsername(principal.getName());
        Treatment treatment = treatmentService.find(newTreatment.getId());
        TreatmentAdministration treatmentAdministration = new TreatmentAdministration();
        treatmentAdministration.setUser(user);
        treatmentAdministration.setTreatment(treatment);
        Date date = new Date();
        treatmentAdministration.setDate(date);
        treatmentAdministration= treatmentAdministrationService.create(treatmentAdministration);
        return new ResponseEntity<TreatmentAdministration>(treatmentAdministration, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MEDIC')")
    @RequestMapping(value = "{id}/change/treatment", method = RequestMethod.PUT)
    public ResponseEntity<Treatment> changeTreatment(@PathVariable("id") Long id,@RequestBody Treatment treatment,Principal principal){

        Treatment dbTreatment = treatmentService.find(treatment.getId());
        if(treatment.getStatus() != dbTreatment.getStatus()){
            dbTreatment.setStatus(treatment.getStatus());
            treatmentService.update(dbTreatment);
        }
        return new ResponseEntity<Treatment>(dbTreatment, HttpStatus.OK);
    }

    private void addNewCoditionsToPacient(Pacient pacient,MedicalForm medicalForm){

        //parcurgem diagnosticele si verificam daca este chronic sau alergie
        User user = medicalForm.getUser();
        for(Diagnostic diagnostic: medicalForm.getDiagnosticList()){
            if(diagnostic.getCronical()==1){
                List<String> errorsList = new ArrayList<String>();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = new Date();
                String dateToString = dateFormat.format(date);
                String hashString = "chronicD" + dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
                HashText hashText = new HashText(hashString);
                String hash = "";
                try {
                    hash = hashText.sha1();
                }catch (NoSuchAlgorithmException ex){
                    System.out.println(ex);
                    errorsList.add(ex.toString());
                }

                ChronicDisease chronicDisease = new ChronicDisease();
                chronicDisease.setDate(date);
                chronicDisease.setCodeCIM(diagnostic.getCodeCIM());
                chronicDisease.setCode(hash);
                chronicDisease.setName(diagnostic.getName());
                chronicDisease.setStatus(1);
                chronicDisease.setPacient(medicalForm.getPacient());
                chronicDisease.setMedicalForm(medicalForm);
                if(errorsList.isEmpty()){
                    chronicDiseaseService.create(chronicDisease);
                }

            }else if(diagnostic.getAllergy()==1){
                List<String> errorsList = new ArrayList<String>();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = new Date();
                String dateToString = dateFormat.format(date);
                String hashString = "allergicD" + dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
                HashText hashText = new HashText(hashString);
                String hash = "";
                try {
                    hash = hashText.sha1();
                }catch (NoSuchAlgorithmException ex){
                    System.out.println(ex);
                    errorsList.add(ex.toString());
                }

                AllergicDisease allergicDisease = new AllergicDisease();
                allergicDisease.setDate(date);
                allergicDisease.setCodeCIM(diagnostic.getCodeCIM());
                allergicDisease.setCode(hash);
                allergicDisease.setName(diagnostic.getName());
                allergicDisease.setStatus(1);
                allergicDisease.setPacient(medicalForm.getPacient());
                allergicDisease.setMedicalForm(medicalForm);
                if(errorsList.isEmpty()){
                    allergicDiseaseService.create(allergicDisease);
                }
            }
        }
        //parcurgem tratamentele si vedem daca este vaccin
        for(Treatment treatment: medicalForm.getTreatmentList()){
            if(treatment.getVaccine()==1){
                List<String> errorsList = new ArrayList<String>();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = new Date();
                String dateToString = dateFormat.format(date);
                String hashString = "chronicD" + dateToString + medicalForm.getCode() + user.getId() + hospitalConfig.getHospitalName();
                HashText hashText = new HashText(hashString);
                String hash = "";
                try {
                    hash = hashText.sha1();
                }catch (NoSuchAlgorithmException ex){
                    System.out.println(ex);
                    errorsList.add(ex.toString());
                }

                Vaccine vaccine = new Vaccine();
                vaccine.setDate(date);
                vaccine.setCodeCIM(treatment.getCodeCIM());
                vaccine.setCode(hash);
                vaccine.setName(treatment.getName());
                vaccine.setStatus(1);
                vaccine.setPacient(medicalForm.getPacient());
                vaccine.setMedicalForm(medicalForm);
                if(errorsList.isEmpty()){
                    vaccineService.create(vaccine);
                }

            }
        }

    }
    private void pushDataToMainframe(Pacient pacient,MedicalForm medicalForm){
        String url = "http://localhost:3000/api/pacients/"+pacient.getCnp();
        HttpsURLConnection con = null;
        try{
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("User-Agent", USER_AGENT);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            ObjectMapper mapper = new ObjectMapper();


//Object to JSON in String
            String jsonInString = mapper.writeValueAsString(pacient);
            urlParameters.add(new BasicNameValuePair("pacient", jsonInString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void freeBed(MedicalForm medicalForm){
        List<BedPacient> bedPacientList = medicalForm.getBedPacientList();

        for(BedPacient bedPacient: bedPacientList){
            if(bedPacient.getStatus()==1){

                Date date = new Date();
                bedPacient.setStatus(0);
                bedPacient.setDate_leave(date);
                bedPacientService.update(bedPacient);
                Bed bed = bedPacient.getBed();
                bed.setStatus(0); // eliberam si patul
                bedService.update(bed);
            }
        }
    }
}
