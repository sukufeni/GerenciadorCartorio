package com.passGenerator.PassGenarator.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v2")
public class LoginController {
    @GetMapping("/login")
	public String login(){
		return "authenticated successfully" ;
	}
}