package com.kapi.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Article implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idArticle;
	
	@NotNull
	private String libelleArticle;
	@NotNull
	private String refArticle;
	@NotNull
	private Double prixHT;
	@NotNull
	private Double tva;
	
	@OneToMany(mappedBy="article", fetch=FetchType.LAZY)
	private Collection<LigneCommande> ligneCmde;
	
		
	public Article() {
		super();
	}
	
	public Article(@NotNull String libelleArticle, @NotNull String refArticle, @NotNull Double prixHT, @NotNull Double tva) {
		super();
		this.libelleArticle = libelleArticle;
		this.refArticle = refArticle;
		this.prixHT = prixHT;
		this.tva = tva;
	}

	public Long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	public String getLibelleArticle() {
		return libelleArticle;
	}
	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}
	public String getRefArticle() {
		return refArticle;
	}
	public void setRefArticle(String refArticle) {
		this.refArticle = refArticle;
	}
	public Double getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(Double prixHT) {
		this.prixHT = prixHT;
	}
	public Double getTva() {
		return tva;
	}
	public void setTva(Double tva) {
		this.tva = tva;
	}

	
}
