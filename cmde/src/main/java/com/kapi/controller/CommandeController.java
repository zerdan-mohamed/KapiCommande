package com.kapi.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kapi.dao.ArticleRepository;
import com.kapi.dao.ClientRepository;
import com.kapi.dao.CommandeRepository;
import com.kapi.message.Response;
import com.kapi.model.Article;
import com.kapi.model.Client;
import com.kapi.model.Commande;
import com.kapi.model.LigneCommande;

@Controller
public class CommandeController {
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	// *** New commande page ***
	@RequestMapping(value="/newCommande", method=RequestMethod.GET)
	public String newCommande(Model model) {
		
		List<Client> listClients = clientRepository.findAll();
		
		model.addAttribute("listClients", listClients);
		return "newCommande";
	}

	
	// *** Create new commande ***
	@RequestMapping(value="/createCmde", method=RequestMethod.POST)
	public String createCmde(HttpServletRequest request, Model model,
            @ModelAttribute("idClient") Long idClient,
            @ModelAttribute("statusCmmde") String statusCmmde,
            @RequestParam("dateLiv") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateLiv) {
	    
		Client client = clientRepository.findByIdClient(idClient);
	    
	    List<Commande> listCommandes = commandeRepository.findAll();
	    List<Article> listArticles = articleRepository.findAll();
	    
	    System.out.println(dateLiv);
	    
	    int sizeList = listCommandes.size();
	    int numCmdeAc;
	    long idCmdeAc;
	    
	    if(listCommandes.size()!=0) {
	      numCmdeAc = listCommandes.get(sizeList-1).getNumCmde()+1;
	      idCmdeAc = listCommandes.get(sizeList-1).getIdCmde()+1;
	    } else {
	      numCmdeAc=  201800001;
	      idCmdeAc= 1;
	    }
	    
	    Commande commande = new Commande();
	    commande.setNumCmde(numCmdeAc);
	    commande.setClient(client);
	    commande.setEnregistreDate(new Date());
	    commande.setStatusCmde(statusCmmde);
	    commande.setLivraisonDate(dateLiv);
	    commandeRepository.save(commande);
	    
	    long idCmde = commande.getIdCmde();
	    
	    model.addAttribute("client", client);
	    model.addAttribute("listArticles", listArticles);
	    model.addAttribute("idCmdeAc", idCmdeAc);
	    model.addAttribute("numCmdeAc", numCmdeAc);
	    model.addAttribute("idCmde", idCmde);

	    return "addArticle";
	}	

	// *** list commandes *** 
	@RequestMapping(value="/listeCommande", method=RequestMethod.GET)
	public String listeCommande(Model model,
			@RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="motCle", defaultValue="")String mc) {
		
		//Page<Client> pageClients = clientRepository.chercherClients("%"+mc+"%", PageRequest.of(p, 5));
		Page<Commande> pageCommandes = commandeRepository.chercherCommandes("%"+mc+"%", PageRequest.of(p, 5));
		
		int pagesCount = pageCommandes.getTotalPages();
		int[] pages = new int[pagesCount];
		for(int i=0; i<pagesCount; i++) pages[i] = i;
				
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCommandes", pageCommandes);
				
		return "listeCommande";
	}
	
	
	// *** Delete client ***
	@RequestMapping(value="/delCommande", method=RequestMethod.GET)
	public String delCommande(@RequestParam(name="idCmde")long idCmde) {
		
		commandeRepository.deleteById(idCmde);
		return "redirect:listeCommande";
	}
	
	// *** Find one client
	@RequestMapping(value="/findCommande", method=RequestMethod.GET)
	public String findCommande(Model model, @RequestParam(name="idCmde")Long idCmde) {
		//Client client = clientRepository.findById(idCmde).get();
		Commande commandeView = commandeRepository.findByIdCmde(idCmde);
		
		model.addAttribute("commandeView", commandeView);
		return "viewCommande";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
