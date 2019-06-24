package com.fernbird.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernbird.model.User;
@Repository("userrepository")
public interface Userrepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	
}
