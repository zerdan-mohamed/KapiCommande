package com.kapi.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//public List<User> findAll();
	
	@Query ("select u from User u where u.username like :x and u.password like :y")
	public User findByUsernameAndPassword(@Param("x") String username, @Param("y") String password);
}
