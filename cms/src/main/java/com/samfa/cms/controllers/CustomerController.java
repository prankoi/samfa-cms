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
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PaginationService paginationService;
	private LoginService loginService = new LoginService();
	private CustomerService customerService = new CustomerService();
	private User user = LoginController.user;
	
	@GetMapping("/customer/add")
  public String addCustomerForm(Model model) {
		try {
			if(user.isLoggedIn) {
				model.addAttribute("customer", new Customer());
				model.addAttribute("title", "Add Customer");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "customer");
				model.addAttribute("action", "customer_add");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/customer/add")
	public String addCustomerResult(@ModelAttribute Customer customer, Model model) throws Exception {		
		if(customerService.checkEmailAddress(customer.emailAddress)) {
			model.addAttribute("error", "Email address already exists.");
			model.addAttribute("customer", customer);
			model.addAttribute("title", "Add Customer");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "customer");
			model.addAttribute("action", "customer_add");
			return user.profileType + "/view";

		} else {
			customer.setFirstName(customer.firstName);
			customer.setLastName(customer.lastName);
			customer.setEmailAddress(customer.emailAddress);
			customer.setCellphoneNumber(customer.cellphoneNumber);
			customer.setPassword(loginService.hash(customer.password));
			customerRepository.save(customer);
			
			return "redirect:/";
		}
	}
	
	@GetMapping(path="/customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				Customer customer = new Customer();
				customer.setCustomerId(id);
				customerRepository.delete(customer);
				
				return "redirect:/";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/customer/edit/{id}")
  public String editCustomerForm(@PathVariable Integer id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Customer customer = customerService.getCustomerById(id);

				model.addAttribute("customer", customer);
				model.addAttribute("title", "Edit Customer");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "customer");
				model.addAttribute("action", "customer_edit");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/customer/edit/{id}")
	public String editCustomer(@PathVariable Integer id, @ModelAttribute Customer newCustomer, Model model) throws Exception {
		Customer customer = customerRepository.findById(id).get();
		if(customerService.checkOtherEmailAddress(newCustomer.emailAddress, id)) {
			model.addAttribute("error", "Email address already exists.");
			model.addAttribute("customer", customer);
			model.addAttribute("title", "Edit Customer");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "customer");
			model.addAttribute("action", "customer_edit");
			
			return user.profileType + "/view";
		} else {
			customer.setFirstName(newCustomer.firstName);
			customer.setLastName(newCustomer.lastName);
			customer.setEmailAddress(newCustomer.emailAddress);
			customer.setCellphoneNumber(newCustomer.cellphoneNumber);
			if(!newCustomer.password.isEmpty()) {
				customer.setPassword(loginService.hash(newCustomer.password));
			}
			
			customerRepository.save(customer);
			
			return "redirect:/";
		}
	}
	
	@GetMapping("/customer")
  public String viewCustomer(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Customer> customers = new ArrayList<Customer>();
				customers = customerService.getAllCustomers();
				Page<Customer> customerPage = paginationService.findPaginatedCustomerAll(PageRequest.of(currentPage - 1, pageSize), customers);
				
				int totalPages = customerPage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("customerPage", customerPage);
				model.addAttribute("title", "Customer");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "customer");
				model.addAttribute("action", "customer");
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