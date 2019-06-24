package com.fernbird.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fernbird.model.User;
import com.fernbird.service.Userservice;

@Controller
public class Usercontroller {
	@Autowired
	private Userservice userservice;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		model.setViewName("user/login");
		return model;

	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/signup");

		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView CreateUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userservice.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email is already exits");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		
		} else {
			userservice.saveUser(user);
			model.addObject("msg", "User has registered successfully!");
			model.addObject("user", new User());
			model.setViewName("user/signup");
		}
		return model;
	}

	@RequestMapping(value = { "/admin/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findUserByEmail(auth.getName());
		model.addObject("username", user.getFirstname() + " " + user.getLastname());
		model.setViewName("/admin/home");
		return model;
	}

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)

	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}

}
