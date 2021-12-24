package com.samfa.cms.controllers;

import com.samfa.cms.models.*;
import com.samfa.cms.services.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	private LoginService loginService = new LoginService();
	public static User user = new User();
	
	@GetMapping(path="/")
  public String checkLogin(Model model) throws Exception {
    if(!user.isLoggedIn) {
			
			model.addAttribute("user", user);

			return "login";
		} else {	
			return "redirect:customer";
		}
  }
	
	@PostMapping(path="/")
	public String login(@ModelAttribute User newUser, Model model) throws Exception {		
		if(loginService.checkCredentials(newUser.username, newUser.password, newUser.profileType)) {
			user.setProfileType(newUser.profileType);
			user.setLoginStatus(true);
			return "redirect:customer";
		} else {
			model.addAttribute("invalidCredentials", "Invalid credentials. Please try again.");
			return "login";
		}
	}
	
	@GetMapping(path="/logout")
	public String logout(Model model) throws Exception {		
		User newUser = new User();
		user.setLoginStatus(false);
		model.addAttribute("user", newUser);
		return "login";
	}
	
	@GetMapping(path="/error")
  public String error() throws Exception {
		return "error";
  }
}