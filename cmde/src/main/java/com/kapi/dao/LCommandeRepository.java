package com.kapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapi.model.LigneCommande;

public interface LCommandeRepository extends JpaRepository<LigneCommande, Long> {
	
}
