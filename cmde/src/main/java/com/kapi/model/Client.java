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
import javax.validation.constraints.Size;

import javax.validation.constraints.Email;

//import org.hibernate.validator.constraints.Email;

@Entity
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idClient;
	
	@Size(min=3, max=30)
	private String nomClient;
	
	@NotNull
	private String prenomClient;
	private String villeClient;
	private String telClient;
	
	@Email
	private String mailClient;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Commande> commandes;

	public Client() {
		super();
	}

	public Client(@Size(min = 3, max = 30) String nomClient, @NotNull String prenomClient,
			String villeClient, String telClient, @Email String mailClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.villeClient = villeClient;
		this.telClient = telClient;
		this.mailClient = mailClient;
	}
	
	public Collection<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getVilleClient() {
		return villeClient;
	}

	public void setVilleClient(String villeClient) {
		this.villeClient = villeClient;
	}

	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}
		
}
