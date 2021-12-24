package com.samfa.cms.controllers;

import com.samfa.cms.models.*;
import com.samfa.cms.repositories.*;
import com.samfa.cms.services.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Controller
public class ProfileController {
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private PaginationService paginationService;
	private LoginService loginService = new LoginService();
	private ProfileService profileService = new ProfileService();
	private User user = LoginController.user;
	
	@GetMapping("/profile/add")
  public String addProfileForm(Model model) {	
		try {
			if(user.isLoggedIn) {
				model.addAttribute("profile", new Profile());
				model.addAttribute("title", "Add Profile");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "profile");
				model.addAttribute("action", "profile_add");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/profile/add")
	public String addProfileResult(@ModelAttribute Profile profile, Model model) throws Exception {		
		if(profileService.checkUsername(profile.username)) {
			model.addAttribute("error", "Username already exists.");
			model.addAttribute("profile", profile);
			model.addAttribute("title", "Add Profile");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "profile");
			model.addAttribute("action", "profile_add");
			return user.profileType + "/view";
		} else {
			profile.setUsername(profile.username);
			profile.setPassword(loginService.hash(profile.password));
			profile.setProfileType(profile.profileType);
			profileRepository.save(profile);
			
			return "redirect:/profile";
		}
	}
	
	@GetMapping(path="/profile/delete/{id}")
	public String deleteProfile(@PathVariable Integer id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				Profile profile = new Profile();
				profile.setProfileId(id);
				profileRepository.delete(profile);
				
				return "redirect:/profile";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/profile/edit/{id}")
  public String editProfileForm(@PathVariable Integer id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Profile profile = profileService.getProfileById(id);

				model.addAttribute("profile", profile);
				model.addAttribute("title", "Edit Profile");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "profile");
				model.addAttribute("action", "profile_edit");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/profile/edit/{id}")
	public String editProfile(@PathVariable Integer id, @ModelAttribute Profile newProfile, Model model) throws Exception{
		Profile profile = profileRepository.findById(id).get();
		if(profileService.checkOtherUsername(newProfile.username, id)) {
			model.addAttribute("error", "Username already exists");
			model.addAttribute("profile", profile);
			model.addAttribute("title", "Edit Profile");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "profile");
			model.addAttribute("action", "profile_edit");
			
			return user.profileType + "/view";		
		} else {
			profile.setUsername(newProfile.username);
			profile.setProfileType(newProfile.profileType);
			if(!newProfile.password.isEmpty()) {
				profile.setPassword(loginService.hash(newProfile.password));
			}
			
			profileRepository.save(profile);
			
			return "redirect:/profile";
		}
	}
	
	@GetMapping("/profile")
  public String viewProfile(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Profile> profiles = new ArrayList<Profile>();
				profiles = profileService.getAllProfiles();
				Page<Profile> profilePage = paginationService.findPaginatedProfileAll(PageRequest.of(currentPage - 1, pageSize), profiles);
				
				int totalPages = profilePage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("profilePage", profilePage);
				model.addAttribute("title", "Profile");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "profile");
				model.addAttribute("action", "profile");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
}