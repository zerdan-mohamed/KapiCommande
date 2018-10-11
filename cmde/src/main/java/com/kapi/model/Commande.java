package com.kapi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Commande implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long idCmde;
	
	@NotNull
	//@Column(name="num_cmde", columnDefinition="int default '100'")
	public int numCmde;
	
	public String statusCmde;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date enregistreDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date livraisonDate;
	
//	EAGER 
	@OneToMany(mappedBy="commande", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<LigneCommande> ligneCmde;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	public Commande() {
		super();
	}
	
	
	
	public Commande(long idCmde, @NotNull int numCmde, String statusCmde, Date enregistreDate, Date livraisonDate,
			Client client) {
		super();
		this.idCmde = idCmde;
		this.numCmde = numCmde;
		this.statusCmde = statusCmde;
		this.enregistreDate = enregistreDate;
		this.livraisonDate = livraisonDate;
		this.client = client;
	}


/*
	public Commande(@NotNull Long numCmde,String statusCmde, Date enregistreDate, Date livraisonDate,
			Collection<LigneCommande> ligneCmde, Client client) {
		super();
		this.numCmde = numCmde;
		this.statusCmde = statusCmde;
		this.enregistreDate = enregistreDate;
		this.livraisonDate = livraisonDate;
		this.ligneCmde = ligneCmde;
		this.client = client;
	}
 */	

	public Client getClient() {
		return client;
	}
	
	public Collection<LigneCommande> getLigneCmde() {
		return ligneCmde;
	}

	public void setLigneCmde(Collection<LigneCommande> ligneCmde) {
		this.ligneCmde = ligneCmde;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	public long getIdCmde() {
		return idCmde;
	}

	public void setIdCmde(long idCmde) {
		this.idCmde = idCmde;
	}

	public int getNumCmde() {
		return numCmde;
	}

	public void setNumCmde(int numCmde) {
		this.numCmde = numCmde;
	}

	public String getStatusCmde() {
		return statusCmde;
	}

	public void setStatusCmde(String statusCmde) {
		this.statusCmde = statusCmde;
	}

	public Date getEnregistreDate() {
		return enregistreDate;
	}

	public void setEnregistreDate(Date enregistreDate) {
		this.enregistreDate = enregistreDate;
	}

	public Date getLivraisonDate() {
		return livraisonDate;
	}

	public void setLivraisonDate(Date livraisonDate) {
		this.livraisonDate = livraisonDate;
	}
	
}
