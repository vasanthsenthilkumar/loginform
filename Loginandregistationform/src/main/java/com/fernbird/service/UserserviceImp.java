
package com.fernbird.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fernbird.model.Role;
import com.fernbird.model.User;
import com.fernbird.repo.Rolerepository;
import com.fernbird.repo.Userrepository;

@Service("userservice")
public class UserserviceImp implements Userservice {

	@Autowired
	private Userrepository userrepo;

	@Autowired
	private Rolerepository rolerepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userrepo.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);

		Role userrole = rolerepo.findByRole("ADMIN");

		user.setRoles(new HashSet<Role>(Arrays.asList(userrole)));
		userrepo.save(user);
	}

}
