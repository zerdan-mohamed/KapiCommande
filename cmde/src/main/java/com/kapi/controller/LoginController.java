package com.kapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kapi.dao.ClientRepository;
import com.kapi.dao.CommandeRepository;
import com.kapi.dao.UserRepository;
import com.kapi.model.Client;
import com.kapi.model.Commande;
import com.kapi.model.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	// to get login form page
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin() {
		//return html page name
		return "index";
	}
	
	//checking for login credentials
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(name="loginForm") User user, Model model, HttpSession session) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		User userCompte = userRepository.findByUsernameAndPassword(username, password);
		
		if(userCompte != null) {
			//if username and password is correct, we are returning home page
			
			List<Client> listClients = clientRepository.findAll();
			List<Commande> listCmdeCours = commandeRepository.commandesEnCours();
			List<Commande> listCmdeLivree = commandeRepository.commandesLivree();
			List<Commande> listCmdePretALivree = commandeRepository.commandesPretALivree();
			
			int nbClients = listClients.size();
			int nbCmdesECours = listCmdeCours.size();
			int nbCmdesLivree = listCmdeLivree.size();
			int nbCmdesPretALivree = listCmdePretALivree.size();
			
			model.addAttribute("nbClients", nbClients);
			model.addAttribute("nbCmdesECours", nbCmdesECours);
			model.addAttribute("nbCmdesLivree", nbCmdesLivree);
			model.addAttribute("nbCmdesPretALivree", nbCmdesPretALivree);
			
			session.setAttribute("userCompte", userCompte);
			
			return "home";
		} else {
			//returning again login page
			model.addAttribute("invalidLogin", true);
			return "index";
		}	
	}
	
	
	// *** se deconnecter ***
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
