package com.kapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapi.dao.ClientRepository;
import com.kapi.dao.CommandeRepository;
import com.kapi.dao.LCommandeRepository;
import com.kapi.model.Client;
import com.kapi.model.Commande;
import com.kapi.model.LigneCommande;

@Controller
public class CompteController {


	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private LCommandeRepository ligneCommandeRepository;
	
	// *** Home dashboard page ***
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		
		List<Client> listClients = clientRepository.findAll();
		List<Commande> listCmdeCours = commandeRepository.commandesEnAttente();
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
		List<Commande> listCmdeCours = commandeRepository.commandesEnAttente();
		List<Commande> listCmdeLivree = commandeRepository.commandesLivree();
		List<Commande> listCmdePretALivree = commandeRepository.commandesPretALivree();
		Page<Commande> listCommandes = commandeRepository.listCommandes(PageRequest.of(p, 8));
		
		List<Commande> listesCommande = commandeRepository.findAll();
		
		List<Double> listTTC = new ArrayList<>();
		
		// * iterate listCommandes is prefer than listesCommande *
		for(int i=listesCommande.size()-8; i<listesCommande.size(); i++) {
			List<LigneCommande> listLCommandes = ligneCommandeRepository.findAll();
			
			Double total = (double) 0, totalRm = (double) 0, totalHT = (double) 0, totalTVA = (double) 0, totalTTC = (double) 0;
			for(int j=0; j<listLCommandes.size() ; j++ ) {
				total = listLCommandes.get(j).getArticle().getPrixHT() * listLCommandes.get(j).getQuantite();
				totalRm = (total * listLCommandes.get(j).getRemise())/100;
				totalHT = total - totalRm;
				totalTVA = (totalHT * listLCommandes.get(j).getArticle().getTva())/100;
				
				totalTTC += totalHT + totalTVA;
			}
			
			listTTC.add(totalTTC);
		}
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		
		
		
		List<Object> globList = new ArrayList<Object>();
		
		globList.add(Arrays.asList(listesCommande.get(1).getNumCmde(), listesCommande.get(1).getStatusCmde(), listTTC.get(1).doubleValue()));
		
		globList.get(0);
		
		//fusion.add(Arrays.asList(al.get(i).getNom(),  al.get(i).getPrenom(),   al.get(i).getAge(),   solde.get(i)) );
		
		
		
		
		
		
		/*
			Map<List<Commande>, List<Double>> mapCmde = new HashMap<>();
		    mapCmde.put(listesCommande, listTTC);
		    model.addAttribute("mapCmde", mapCmde);
	    */
		
		int nbClients = listClients.size();
		int nbCmdesECours = listCmdeCours.size();
		int nbCmdesLivree = listCmdeLivree.size();
		int nbCmdesPretALivree = listCmdePretALivree.size();
		
		model.addAttribute("nbClients", nbClients);
		model.addAttribute("nbCmdesECours", nbCmdesECours);
		model.addAttribute("nbCmdesLivree", nbCmdesLivree);
		model.addAttribute("nbCmdesPretALivree", nbCmdesPretALivree);
		model.addAttribute("listCommandes", listCommandes);
		model.addAttribute("listTTC", listTTC);
		
		return "monCompte";
	}
}
