package com.kapi.dao;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kapi.model.Client;
import com.kapi.model.Commande;

public interface ClientRepository extends JpaRepository<Client, Long> {
	public List<Client> findByNomClient(String n);
	public Page<Client> findByNomClient(String n, Pageable pageable);
	public List<Client> findAll();
	
	
	public Page<Client> findAll(Pageable pageable);
	
	public void deleteByIdClient(Long idClient);
	
	@Query ("select c from Client c where c.idClient like :x")
	public Client findByIdClient(@Param("x") Long idClient);
	
	@Query ("select c from Client c where c.idClient like :x")
	public Client searchByIdClient(@Param("x") Long idClient);
	
	@Query ("select c from Client c where c.nomClient like :x")
	public Page<Client> chercherClients(@Param("x")String mc, Pageable pageable);
	
	
	@Modifying
	@Query("update Client c set c.nomClient = :a, c.prenomClient = :b, c.villeClient = :c,"
			+ "c.telClient = :d, c.mailClient = :e where c.idClient = :f")
	public Client updateClient(@Param("a") String nomClient, @Param("b") String prenomClient, @Param("c") String villeClient,
			@Param("d") String telClient, @Param("e") String mailClient, @Param("f") Long idClient);
	
	//@Modifying
	@Query("update Client c set c.nomClient = ?1, c.prenomClient = ?2, c.villeClient = ?3,"
			+ "c.telClient = ?4, c.mailClient = ?5 where c.idClient = ?6")
	public void updateCl(String nomClient, String prenomClient, String villeClient,
			String telClient, String mailClient, Long idClient);
	
	
	//("update product set name = ?, price = ?, quantity = ?, description = ?, photo = ?, featured = ? where id = ?", product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getPhoto(), product.isFeatured(), product.getId()) > 0;
	
}










