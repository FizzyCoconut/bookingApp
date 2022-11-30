package com.example.bookingApp.controller;

import com.example.bookingApp.DTO.UserLoginDTO;
import com.example.bookingApp.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private DefaultUserService userService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String loginUser(@ModelAttribute("user")
                            UserLoginDTO userLoginDTO) {
        System.out.println("UserDTO"+userLoginDTO);
        UserDetails user = userService.loadUserByUsername(userLoginDTO.getUsername());
        if(user.getPassword().equals(passwordEncoder.encode(userLoginDTO.getPassword()))) {

            return "redirect:/dashboard";
        }
        else
            return "redirect:/login?error";


    }
}
