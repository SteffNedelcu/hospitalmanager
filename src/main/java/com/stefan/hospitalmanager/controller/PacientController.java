package com.stefan.hospitalmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefan.hospitalmanager.config.HospitalConfig;
import com.stefan.hospitalmanager.entity.*;
import com.stefan.hospitalmanager.helper.HashText;
import com.stefan.hospitalmanager.service.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;



@RestController
//@Controller
@RequestMapping("/pacient")
public class PacientController {

    @Autowired
    private PacientService pacientService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @Autowired
    private MedicalFormService medicalFormService;

    @Autowired
    private HospitalConfig hospitalConfig;

    @Autowired
    private EntityManager bentityManager;

    private final String USER_AGENT = "Mozilla/5.0";


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pacient> index(Model model) {

        List<Pacient> pacientList = pacientService.getPacients();

        return pacientList;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        System.out.println("GET");
        Pacient pacient = new Pacient();

        model.addAttribute("pacient", pacient);
        return "pacient_add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("pacient") Pacient pacient, Model model) {
        System.out.println(pacient);
        System.out.println("POST");
        if(pacientService.checkPacientExists(pacient.getCnp()))  {

            if (pacientService.checkCNP(pacient.getCnp())) {
                model.addAttribute("cnp exists", true);
            }
            return "pacient_add";
        } else {

            pacientService.savePacient(pacient);

            return "redirect:/dashboard";
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pacient getPacient(@PathVariable("id") String id) {

        return  pacientService.getPacient(id);

    }
    @RequestMapping(value = "/{id}/medical-forms", method = RequestMethod.GET)
    public List<MedicalForm> getPacientMedicalForm(@PathVariable("id") String id) {

        Pacient pacient = pacientService.getPacient(id);

        return pacient.getMedicalFormList();
    }
    @RequestMapping(value = "/{id}/active-form", method = RequestMethod.GET)
    public Long getPacientMedicalFormActive(@PathVariable("id") String id) {

        Long idMedicalForm = new Long(0);
        Pacient pacient = pacientService.getPacient(id);
        List<MedicalForm> medicalFormsList= pacient.getMedicalFormList();

        for(MedicalForm form : medicalFormsList ){

            if(form.getStatus() != (Integer)3){
                idMedicalForm =  form.getId();
            }
        }

        return idMedicalForm;
    }
    @RequestMapping(value = "/{id}/diagnostics", method = RequestMethod.GET)
    public List<ChronicDisease> getPacientDiagnostics(@PathVariable("id") String id) {

        Pacient pacient = pacientService.getPacient(id);

        return pacient.getChronicDiseaseList();
    }
    @RequestMapping(value = "/{id}/diagnostics/add", method = RequestMethod.POST)
    public ResponseEntity<ChronicDisease> addDiagnostic(@PathVariable("id") String id, @RequestBody ChronicDisease chronicDisease, Principal principal) {

        Pacient pacient = pacientService.getPacient(id);
        User user = userService.findByUsername(principal.getName());
        List<ChronicDisease>  listDiagnostics =  pacient.getChronicDiseaseList();
        chronicDisease.setPacient(pacient);
        chronicDisease.setStatus(1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);
        chronicDisease.setDate(date);


        listDiagnostics.add(chronicDisease);
        chronicDiseaseService.create(chronicDisease);
        System.out.println(listDiagnostics);
        return new ResponseEntity<ChronicDisease>(chronicDisease, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/add-medical-form", method = RequestMethod.POST)
    public ResponseEntity<MedicalForm> addMedicalForm(@PathVariable("id") String id, Principal principal) {

        System.out.println("Addding medical FORM .................");

        //creating MedicalForm
        MedicalForm medicalForm = new MedicalForm();

        //get padcient info
        Pacient pacient = pacientService.getPacient(id);

        //get medic info
        User user = userService.findByUsername(principal.getName());

        //setting the date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String dateToString = dateFormat.format(date);

        String hashString = dateToString + pacient.getCnp() + user.getId() + hospitalConfig.getHospitalName();
        HashText hashText = new HashText(hashString);
        String hash = "";

        try {
            hash = hashText.sha1();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
        }
        if(!hash.equals("")){
            medicalForm.setCode(hash);
            medicalForm.setStatus(1);
            medicalForm.setDateCheckUp(date);
            medicalForm.setPacient(pacient);
            medicalForm.setUser(user);
            String hospitalInfo = hospitalConfig.getHospitalName() + " " + hospitalConfig.getHospitalCity();
            medicalForm.setHospitalInfo(hospitalInfo);
            medicalFormService.createMedicalForm(medicalForm);


        }
        return new ResponseEntity<MedicalForm>(medicalForm, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/test-hash", method = RequestMethod.GET)
    public String testHAsh(@PathVariable("id") String id,Principal principal) {

        Pacient pacient = pacientService.getPacient(id);
        User user = userService.findByUsername(principal.getName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        String dateToString = dateFormat.format(date);
        String hashString = dateToString + pacient.getCnp() + user.getId() + hospitalConfig.getHospitalName();
        HashText hashText = new HashText(hashString);
        String hash = "";
        try {
            hash = hashText.sha1();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
        }
        return hash;
    }
    @RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
    public List<Pacient> searchPacient(@PathVariable("term") String term,Principal principal){
        PacientSearchService pacientSearchService = new PacientSearchService(bentityManager);
        return pacientSearchService.fuzzySearch(term);
    }
    @RequestMapping(value = "/{id}/send-mainframe", method = RequestMethod.GET)
    public Pacient sentMain(@PathVariable("id") String id) {


        Pacient pacient = pacientService.getPacient(id);

        String url = "http://localhost:3000/api/pacients/"+id;
        HttpsURLConnection  con = null;
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


        return pacient;
    }


}
