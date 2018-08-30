package com.kapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LigneCommande implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long idLigneCmde;
	
	@NotNull
	public Long quantite;
	@NotNull
	public Double remise;
	
	@ManyToOne
	@JoinColumn(name="idArticle")
	private Article article;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name="idCmde")
	private Commande commande;
	
	public LigneCommande() {
		
	}
	
	public LigneCommande(@NotNull Long quantite, @NotNull Double remise, Article article, Commande commande) {
		super();
		this.quantite = quantite;
		this.remise = remise;
		this.article = article;
		this.commande = commande;
	}

	public Long getIdLigneCmde() {
		return idLigneCmde;
	}
	public void setIdLigneCmde(Long idLigneCmde) {
		this.idLigneCmde = idLigneCmde;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
		
}
