package com.kapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapi.dao.ClientRepository;
import com.kapi.dao.CommandeRepository;
import com.kapi.model.Client;
import com.kapi.model.Commande;

@Controller
public class CompteController {


	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	// *** Home dashboard page ***
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		
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
		
		return "home";
	}
	
	// *** Mon compte page ***
	@RequestMapping(value="/monCompte", method=RequestMethod.GET)
	public String monCompte(Model model, @RequestParam(name="page", defaultValue="0")int p) {
		
		List<Client> listClients = clientRepository.findAll();
		List<Commande> listCmdeCours = commandeRepository.commandesEnCours();
		List<Commande> listCmdeLivree = commandeRepository.commandesLivree();
		List<Commande> listCmdePretALivree = commandeRepository.commandesPretALivree();
		Page<Commande> listCommandes = commandeRepository.listCommandes(PageRequest.of(p, 8));
		
		int nbClients = listClients.size();
		int nbCmdesECours = listCmdeCours.size();
		int nbCmdesLivree = listCmdeLivree.size();
		int nbCmdesPretALivree = listCmdePretALivree.size();
		
		model.addAttribute("nbClients", nbClients);
		model.addAttribute("nbCmdesECours", nbCmdesECours);
		model.addAttribute("nbCmdesLivree", nbCmdesLivree);
		model.addAttribute("nbCmdesPretALivree", nbCmdesPretALivree);
		model.addAttribute("listCommandes", listCommandes);
		
		return "monCompte";
	}
}
