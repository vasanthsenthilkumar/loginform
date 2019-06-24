

  package com.fernbird.service;
  
  import com.fernbird.model.User;
  
  public interface Userservice {
  
  public User findUserByEmail(String email);
  
  
  public void saveUser(User user);
  
  
  }
 
 