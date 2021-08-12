package com.passGenerator.PassGenarator.Login;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @GetMapping("/basicauth")
    public AuthenticationBean helloWorldBean() {
        return new AuthenticationBean("You are authenticated");
    }
}