package com.kapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kapi.model.Article;
import com.kapi.model.Client;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	public List<Article> findAll(); // ORDER BY libelleArticle
	
	@Query ("select c from Article c where c.idArticle like :x")
	public Article findByIdArticle(@Param("x") Long idArticle);  

}
