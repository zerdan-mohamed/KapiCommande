package com.kapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapi.dao.ClientRepository;
import com.kapi.model.Client;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@RequestMapping(value="/listeClient", method=RequestMethod.GET)
	public String listeClient(Model model,
			@RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="motCle", defaultValue="")String mc) {
		
		Page<Client> pageClients = clientRepository.chercherClients("%"+mc+"%", PageRequest.of(p, 5));
		
		int pagesCount = pageClients.getTotalPages();
		int[] pages = new int[pagesCount];
		for(int i=0; i<pagesCount; i++) pages[i] = i;
				
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageClients", pageClients);
		
		return "listeClient";
	}
	
	
	// *** Add client ***
	@RequestMapping(value="/addClient", method=RequestMethod.GET)
	public String addClient(Model model) {
		model.addAttribute("client", new Client());
		return "addClient";
	}
	
	// *** Save client ***
	@RequestMapping(value="/saveClient", method=RequestMethod.POST)
	public String saveClient(@Valid Client client, BindingResult bindResult) {
		clientRepository.save(client);
		if(bindResult.hasErrors()) {
			return "addClient";
		}
		return "redirect:listeClient";
	}
	
	
	// *** Delete client ***
	@RequestMapping(value="/delClient", method=RequestMethod.GET)
	public String delClient(@RequestParam(name="idClient")Long idClient) {
		clientRepository.deleteById(idClient);
		return "redirect:listeClient";
	}
	
	
	// *** Find one client information ***
	@RequestMapping(value="/findClient", method=RequestMethod.GET)
	public String findClient(Model model, @RequestParam(name="idClient")Long idClient) {
		Client client = clientRepository.findById(idClient).get();
		model.addAttribute("eClient", client);
		return "editClient";
	}

	
	// *** Update client information ***
	@RequestMapping(value="/editClient", method=RequestMethod.POST)
	public String editClient(@RequestParam(name="idClient")Long idClient,
			@RequestParam("nom")String nom,
			@RequestParam(name="prenom")String prenom,
			@RequestParam(name="ville")String ville,
			@RequestParam(name="tel")String tel,
			@RequestParam(name="mail")String mail) {
		
		Client c = clientRepository.findById(idClient).get(); 	
		c.setNomClient(nom);
		c.setPrenomClient(prenom);
		c.setVilleClient(ville);
		c.setTelClient(tel);
		c.setMailClient(mail);
		clientRepository.save(c);
					
		return "redirect:listeClient";
	}
	
	
	
}
