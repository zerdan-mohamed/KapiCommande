package com.kapi.controller;

import java.util.ArrayList;
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

	/*
	@GetMapping(value = "/all")
	public Response getResource() {
		Response response = new Response("Article", articles);
		return response;
	}
	*/
	
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
		//model.addAttribute("libelle", libelle);
		
		Response response = new Response("Article", articleActuel);
		return response;
	}
	
	
	// ** Save ligne commande **
	@RequestMapping(value="/saveLCmde", method=RequestMethod.POST)
	@ResponseBody
	public Response saveLigneCommande(@RequestBody Map<String, String> json) {
		
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
		
		Response response = new Response("Done", lCmde);
		return response;
	}
	
	
}

