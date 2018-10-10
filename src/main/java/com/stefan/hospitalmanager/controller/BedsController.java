package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.BedPacient;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import com.stefan.hospitalmanager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bed")
public class BedsController {

    @Autowired
    private BedService bedService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Bed> index() {

        List<Bed> beds = bedService.findAll();

        return beds;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Bed getBed(@PathVariable("id") long id) {

        Bed bed = bedService.findBed(id);

        return bed;
    }
    @RequestMapping(value = "/{id}/pacients", method = RequestMethod.GET)
    public List<BedPacient> getBedPacients(@PathVariable("id") long id) {

        Bed bed = bedService.findBed(id);

        return bed.getBedPacients();
    }
    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public List<Bed> getBedsByRoom(@PathVariable("id") long id) {

        Room room = roomService.findRoom(id);
        List<Bed> beds = bedService.findByRoom(room);

        return beds;
    }
}
