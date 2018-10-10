package com.stefan.hospitalmanager.controller;

import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.service.MedicalFormService;
import com.stefan.hospitalmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MedicalFormService medicalFormService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public User profile(Principal principal, Model model) {
        System.out.println("REQUEST CREDENTIALS");
        User user = userService.findByUsername(principal.getName());


        return user;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user", user);

        userService.saveUser(user);

        return "profile";
    }
}
