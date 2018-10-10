package com.stefan.hospitalmanager.controller;


import com.stefan.hospitalmanager.config.HospitalConfig;
import com.stefan.hospitalmanager.dao.RoleDao;
import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.entity.security.UserRole;
import com.stefan.hospitalmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private HospitalConfig hospitalConfig;

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        System.out.println("REQUEST INDEX");

        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_MEDIC")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

    @RequestMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        System.out.println("REQUEST");
        User user = userService.findByUsername(principal.getName());
        System.out.println(hospitalConfig.getHospitalCity());
        return "dashboard";
    }


}
