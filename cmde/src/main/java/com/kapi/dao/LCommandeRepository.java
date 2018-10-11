package com.kapi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kapi.model.Article;
import com.kapi.model.Commande;
import com.kapi.model.LigneCommande;

public interface LCommandeRepository extends JpaRepository<LigneCommande, Long> {
	
	@Query ("select c from LigneCommande c where c.idLigneCmde like :z")
	public LigneCommande findByIdLCmde(@Param("z") Long idLigneCmde); 
	
	@Query ("select c from LigneCommande c where c.commande like :x")
	public Page<LigneCommande> chercherLCommande(@Param("x")Commande id, Pageable pageable);
	
	@Query ("select c from LigneCommande c where c.commande like :y")
	public List<LigneCommande> chercherLCommandes(@Param("y")Commande id);
	
	//@Query (value = "select idArticle from LigneCommande l inner join Article a on (l.idArticle = a.idArticle) where l.commande like :x", nativeQuery = true)
	//@Query ("select l from LigneCommande l inner join fetch l.Article where l.idArticle=:idArticle")
	//@Query(value = "SELECT * FROM USERS u WHERE u.status = 1", nativeQuery = true)
	//public List<Object> chercherLCommand(@Param("x")Commande id);
	
	
	/*
	SELECT * FROM Orders JOIN OrderLines ON OrderLines.OrderID=Orders.ID WHERE Orders.ID = 12345
	*/
	
	/*
		SELECT j.nom nomJeu, p.nom nomProp FROM proprietaires p RIGHT JOIN jeuxVideo j ON j.idProp = p.id
	*/
	
	
	
	
	
}
