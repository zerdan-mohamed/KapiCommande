package com.kapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kapi.model.User;

@Controller
public class LoginController {
	//to get login form page
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin() {
		//return html page name
		return "index";
	}
	
	//checking for login credentials
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(name="loginForm") User user, Model model) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		if("admin".equals(username) && "admin".equals(password)) {
			//if username and password is correct, we are returning home page
			model.addAttribute("code", 21);
			return "home";
		}
		
		//if username and password is wrong
		model.addAttribute("invalidLogin", true);
		//returning again login page 
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
