package com.kapi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kapi.model.Client;
import com.kapi.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	public List<Commande> findAll();
	
	@Query ("select c from Commande c where c.statusCmde like :x")
	public Page<Commande> chercherCommandes(@Param("x")String mc, Pageable pageable);
	
	@Query ("select c from Commande c where c.idCmde like :y")
	public Commande findByIdCmde(@Param("y") long idCmde);
}
