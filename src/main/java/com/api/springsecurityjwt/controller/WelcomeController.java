package com.api.springsecurityjwt.controller;

import com.api.springsecurityjwt.entity.UserRequest;
import com.api.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/")
    public String welcome(){
        return "welcome to my spring security project";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserRequest userRequest) throws Exception {
        try {
            System.out.println(userRequest.getUsername()+" "+userRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(userRequest.getUsername());
    }
}
