package com.example.bookingApp.controller;


import com.example.bookingApp.beans.BusData;
import com.example.bookingApp.beans.User;
import com.example.bookingApp.repository.BusDataRepository;
import com.example.bookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminScreen")
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BusDataRepository busDataRepository;


    @ModelAttribute("busDetails")
    public BusData busData() {
        return new BusData();
    }

    @GetMapping
    public String displayDashboard(Model model){
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "adminScreen";
    }

    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername());
        return users.getName();
    }

    @PostMapping
    public String saveBusData(@ModelAttribute("busDetails") BusData busData, Model model){
        String user= returnUsername();
        model.addAttribute("userDetails", user);
        busDataRepository.save(busData);
        model.addAttribute("busDetails", new BusData());
        return "redirect:/adminScreen?success";
    }
}
