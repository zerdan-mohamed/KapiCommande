package com.kapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kapi.dao.ArticleRepository;
import com.kapi.dao.CommandeRepository;
import com.kapi.dao.LCommandeRepository;
import com.kapi.message.Response;
import com.kapi.model.Article;
import com.kapi.model.Commande;
import com.kapi.model.LigneCommande;

@RestController
@RequestMapping("/api/article")
public class LCommandeController {

	List<Article> articles = new ArrayList<Article>();
	
	@Autowired
	private LCommandeRepository lCommandeRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	List<LigneCommande> lCommandes = new ArrayList<LigneCommande>();

	
	// ** Select article information **
	@RequestMapping(value="/selectArticle", method=RequestMethod.POST)
	@ResponseBody
	public Response selectArticle(@RequestBody Map<String, String> json, Model model) {
		
		long idArticle = Long.parseLong(json.get("idArticle"));
		Article articleActuel = articleRepository.findByIdArticle(idArticle);
		
		articles.add(articleActuel);
		
		//String libelle = articleActuel.getLibelleArticle();
		
		model.addAttribute("articleSuccess", "true");
		model.addAttribute("articleActuel", articleActuel);
		
		Response response = new Response("Article", articleActuel);
		return response;
	}
	
	
	// ** Select ligne command information **
	@RequestMapping(value="/selectLCommand", method=RequestMethod.POST)
	@ResponseBody
	public Response selectLcommandU(@RequestBody Map<String, String> json, Model model) {
		long idLcommand =  Long.parseLong(json.get("idLC"));
		
		LigneCommande ligneCmdeS = lCommandeRepository.findByIdLCmde(idLcommand);
		
		//articles.add(articleActuel);
			
		model.addAttribute("ligneCmdeS", ligneCmdeS);
			
		Response response = new Response("LCommandS", ligneCmdeS);
		return response;
	}
	
	
	// ** Update date livraison **
	@RequestMapping(value="/updateDL", method=RequestMethod.POST)
	@ResponseBody
	public Response updateDL(@RequestBody Map<String, String> json, Model model) throws ParseException {
		
		// long quantite = Long.parseLong(json.get("quantite"));
		// int numCmde = Integer.parseInt(json.get("numCmde"));
		
		long idCmde = Long.parseLong(json.get("idCmde"));
		
		/*
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(json.get("dateL"));
		Date   dateL       = format.parse(dateString); */
		
		
		long b = 396;
//		Commande commandeView = commandeRepository.findByIdCmde(idCmde);
		Commande commandeView = commandeRepository.findBynumCmde(201800010);
		commandeView.setLivraisonDate(new Date());
		
		long a = 1;
		Article art = articleRepository.findByIdArticle(a);
		
		
		//cmde.setLivraisonDate(dateL);
		
		Response response = new Response("Date", art);
		return response;
	}
	
	
	// ** Save ligne commande **
	@RequestMapping(value="/saveLCmde", method=RequestMethod.POST)
	@ResponseBody
	public Response saveLigneCommande(@RequestBody Map<String, String> json, Model model) {
		
		long quantite = Long.parseLong(json.get("quantite"));
		long idA = Long.parseLong(json.get("idArticle"));
		long idC = Long.parseLong(json.get("idCmde"));
		Double remise = Double.parseDouble(json.get("remise"));
		
		Article article = articleRepository.findByIdArticle(idA);
		Commande commande = commandeRepository.findByIdCmde(idC);
		
		LigneCommande lCmde = new LigneCommande();
		
		lCmde.setArticle(article);
		lCmde.setQuantite(quantite);
		lCmde.setRemise(remise);
		lCmde.setCommande(commande);
		lCommandeRepository.save(lCmde);
		
		lCommandes.add(lCmde);
		
		long nombre = lCmde.getQuantite();
		model.addAttribute("nombre", nombre);
		
		Response response = new Response("Commande", lCmde);
		return response;
	}
	
	
	// ** Save ligne commande **
	@RequestMapping(value="/updateLCmdeV", method=RequestMethod.POST)
	@ResponseBody
	public Response updateLigneCommande(@RequestBody Map<String, String> json, Model model) {	

		long editLC = Long.parseLong(json.get("editLC"));
		long editQtte = Long.parseLong(json.get("editQtte"));
		Double editRemise = Double.parseDouble(json.get("editRemise"));
		
		LigneCommande lCmdeUpdate = lCommandeRepository.findByIdLCmde(editLC);
		
		lCmdeUpdate.setQuantite(editQtte);
		lCmdeUpdate.setRemise(editRemise);
		
		lCommandeRepository.save(lCmdeUpdate);
	
		
		Response response = new Response("CommandeU", lCmdeUpdate);
		return response;
	}
	
	
	// ** Save ligne commande in view command **
	@RequestMapping(value="/saveLCmdeV", method=RequestMethod.POST)
	@ResponseBody
	public Response saveLigneCommandeV(@RequestBody Map<String, String> json, Model model) {
		
		long quantite = Long.parseLong(json.get("quantite"));
		long idA = Long.parseLong(json.get("idArticle"));
		long idC = Long.parseLong(json.get("idCmde"));
		Double remise = Double.parseDouble(json.get("remise"));
		
		Article article = articleRepository.findByIdArticle(idA);
		Commande commande = commandeRepository.findByIdCmde(idC);
		
		LigneCommande lCmde = new LigneCommande();
		
		lCmde.setArticle(article);
		lCmde.setQuantite(quantite);
		lCmde.setRemise(remise);
		lCmde.setCommande(commande);
		lCommandeRepository.save(lCmde);
		
		lCommandes.add(lCmde);
		
		long nombre = lCmde.getQuantite();
		model.addAttribute("nombre", nombre);
		
		Response response = new Response("CommandeV", lCmde);
		return response;
	}
	
	
}

