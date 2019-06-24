package com.fernbird.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernbird.model.Role;
@Repository("rolerepository")
public interface Rolerepository extends JpaRepository<Role, Integer>{
   Role findByRole(String role);
   
}
