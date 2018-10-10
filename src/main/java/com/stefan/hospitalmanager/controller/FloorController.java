package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.Bed;
import com.stefan.hospitalmanager.entity.Floor;
import com.stefan.hospitalmanager.entity.Room;
import com.stefan.hospitalmanager.service.BedService;
import com.stefan.hospitalmanager.service.FloorService;
import com.stefan.hospitalmanager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Floor> index() {

        List<Floor> floors = floorService.findAll();

        return floors;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Floor getBed(@PathVariable("id") long id) {

        Floor floor = floorService.findFloor(id);

        return floor;
    }

}
