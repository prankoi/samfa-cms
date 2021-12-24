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
public class PromoController {
	@Autowired
	private PromoRepository promoRepository;
	@Autowired
	private PaginationService paginationService;
	private PromoService promoService = new PromoService();
	private User user = LoginController.user;

	@GetMapping("/promo/add")
  public String addCustomerForm(Model model) {
		try {
			if(user.isLoggedIn) {
				model.addAttribute("promo", new Promo());
				model.addAttribute("title", "Add Promo");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "promo");
				model.addAttribute("action", "promo_add");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }

	@PostMapping(path="/promo/add")
	public String addPromoResult(@ModelAttribute Promo promo, Model model) throws Exception {		
		promo.setPromoCode(promo.promoCode);
		promo.setPromoType(promo.promoType);
		promo.setDiscount(promo.discount);
		promo.setStartDate(promo.startDate);
		promo.setEndDate(promo.endDate);
		promoRepository.save(promo);

		return "redirect:/promo";
	}
	
	@GetMapping(path="/promo/delete/{id}")
	public String deletePromo(@PathVariable Integer id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				Promo promo = new Promo();
				promo.setPromoId(id);
				promoRepository.delete(promo);
				
				return "redirect:/promo";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/promo/edit/{id}")
  public String editPromoForm(@PathVariable Integer id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Promo promo = promoRepository.findById(id).get();

				model.addAttribute("promo", promo);
				model.addAttribute("title", "Edit Promo");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "promo");
				model.addAttribute("action", "promo_edit");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/promo/edit/{id}")
	public String editPromo(@PathVariable Integer id, @ModelAttribute Promo newPromo, Model model) throws Exception{
		Promo promo = promoRepository.findById(id).get();
		promo.setPromoCode(newPromo.promoCode);
		promo.setPromoType(newPromo.promoType);
		promo.setDiscount(newPromo.discount);
		promo.setStartDate(newPromo.startDate);
		promo.setEndDate(newPromo.endDate);
		promoRepository.save(promo);
		
		return "redirect:/promo";
	}

	@GetMapping("/promo")
  public String viewPromo(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Promo> promos = new ArrayList<Promo>();
				promos = promoService.getAllPromos();
				Page<Promo> promoPage = paginationService.findPaginatedPromoAll(PageRequest.of(currentPage - 1, pageSize), promos);
				
				int totalPages = promoPage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("promoPage", promoPage);
				model.addAttribute("title", "Promo");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "promo");
				model.addAttribute("action", "promo");
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