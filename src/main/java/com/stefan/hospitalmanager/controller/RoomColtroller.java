package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.BedPacient;
import com.stefan.hospitalmanager.entity.MedicalForm;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import com.stefan.hospitalmanager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomColtroller {



    @Autowired
    private RoomService roomService;
    @Autowired
    private BedService bedService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Room> index() {

        List<Room> rooms = roomService.findAll();

        return rooms;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable("id") long id) {

        Room room = roomService.findRoom(id);

        return room;
    }
    @RequestMapping(value = "/{id}/bed-pacients", method = RequestMethod.GET)
    public Map<Long,BedPacient> getBedsPacients(@PathVariable("id") long id) {

        Room room = roomService.findRoom(id);
        Map<Long,BedPacient> map = new HashMap<>();
        for(Bed bed:room.getBedList()){
            List<BedPacient> thisBedPacients = bed.getBedPacients();

            for(BedPacient pacient: thisBedPacients){
                if(pacient.getStatus()==1){
                    map.put(bed.getId(),pacient);
                }
            }
        }

        return map;
    }
    @RequestMapping(value = "/{id}/medical-forms", method = RequestMethod.GET)
    public Map<Long,Long> getMedicalForms(@PathVariable("id") long id) {

        Room room = roomService.findRoom(id);
        Map<Long,Long> map = new HashMap<>();
        for(Bed bed:room.getBedList()){
            List<BedPacient> thisBedPacients = bed.getBedPacients();

            for(BedPacient bedPacient: thisBedPacients){
                if(bedPacient.getStatus()==1){
                    map.put(bed.getId(),bedPacient.getMedicalForm().getId());
                }
            }
        }

        return map;
    }

}
