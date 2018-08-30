package com.kapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompteController {

	
	// *** Home dashboard page ***
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("code", 21);
		return "home";
	}
	
	// *** Mon compte page ***
	@RequestMapping(value="/monCompte", method=RequestMethod.GET)
	public String monCompte() {
		return "monCompte";
	}
}
