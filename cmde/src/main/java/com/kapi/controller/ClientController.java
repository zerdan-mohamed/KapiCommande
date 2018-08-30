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
	
	
	// *** Find one client
	@RequestMapping(value="/findClient", method=RequestMethod.GET)
	public String findClient(Model model, @RequestParam(name="idClient")Long idClient) {
		Client client = clientRepository.findById(idClient).get();
		model.addAttribute("eClient", client);
		return "editClient";
	}
		

	
	// *** essai update client 1 ***
	@RequestMapping(value="/editClient", method=RequestMethod.POST)
	public String editClient(@RequestParam(name="idClient")Long idClient,
			@RequestParam(name="nomClient")String nomClient, @RequestParam(name="prenomClient")String prenomClient,
			@RequestParam(name="villeClient")String villeClient, @RequestParam(name="telClient")String telClient,
			@RequestParam(name="mailClient")String mailClient) {
		

		//Client c = new Client();
		Client c = clientRepository.findById(idClient).get(); 
				
		String tel = c.getTelClient();
		c.setNomClient(nomClient);
		c.setPrenomClient("Siham");
		c.setVilleClient("Rabat");
		c.setTelClient(tel);
		c.setMailClient("falah@gmail.com");
		
		clientRepository.save(c);
					
		return "redirect:listeClient";
	}
	
	
	@RequestMapping(value="/editClient2", method=RequestMethod.POST)
	public String editClient2(@Valid Client client, BindingResult bindResult, @RequestParam(name="idClient")Long idClient,
			@RequestParam(name="nomClient")String nomClient, @RequestParam(name="prenomClient")String prenomClient,
			@RequestParam(name="villeClient")String villeClient, @RequestParam(name="telClient")String telClient,
			@RequestParam(name="mailClient")String mailClient) {
		clientRepository.updateCl(nomClient, prenomClient, villeClient, telClient, mailClient, idClient);
		if(bindResult.hasErrors()) {
			return "editClient";
		}
		return "redirect:listeClient";
	}
	
	
	
	
	
	// *** essai update client 2 ***
	/*
	@RequestMapping(value="/editClient", method=RequestMethod.POST)
	public String editClient(@Valid Client eclient, BindingResult bindResult) {	
		
		clientRepository.save(eclient);
		
		if(bindResult.hasErrors()) {
			return "editClient";
		}
		return "redirect:listeClient";
	}	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
}
