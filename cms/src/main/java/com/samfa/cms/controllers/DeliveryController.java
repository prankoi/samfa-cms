package com.samfa.cms.controllers;

import com.samfa.cms.models.*;
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
public class DeliveryController {
	@Autowired
	private PaginationService paginationService;
	private CustomerService customerService = new CustomerService();
	private DriverService driverService = new DriverService();
	private DeliveryService deliveryService = new DeliveryService();
	private MiscService miscService = new MiscService();
	private User user = LoginController.user;
	
	@GetMapping(path="/delivery/delete/{id}")
	public String deleteDelivery(@PathVariable String id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				deliveryService.deleteDeliveryById(id);
				
				return "redirect:/delivery";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/delivery")
  public String viewDelivery(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
				deliveries = deliveryService.getAllDeliveries();
				Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
				
				int totalPages = deliveryPage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				
				model.addAttribute("sortAndFilter", new SortAndFilter());
				model.addAttribute("deliveryPage", deliveryPage);
				model.addAttribute("title", "Delivery");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "delivery");
				model.addAttribute("action", "delivery");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@GetMapping("/delivery/{id}")
  public String viewDeliveryInfo(@PathVariable String id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Delivery delivery = deliveryService.getDeliveryById(id);
				Customer customer = customerService.getCustomerById(delivery.customerId);
				Driver driver = driverService.getDriverById(delivery.driverId);
				Location pickup = miscService.getLocation(delivery.pickupLocation);
				Location dropoff = miscService.getLocation(delivery.dropoffLocation);
				Item item = miscService.getItem(delivery.itemId);
				
				model.addAttribute("delivery", delivery);
				model.addAttribute("customer", customer);
				model.addAttribute("driver", driver);
				model.addAttribute("pickup", pickup);
				model.addAttribute("dropoff", dropoff);
				model.addAttribute("item", item);
				model.addAttribute("title", "Delivery Info");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "delivery");
				model.addAttribute("action", "delivery_info");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping("/delivery/generate")
	public String viewReportType(@ModelAttribute SortAndFilter sortAndFilter, Model model,
		@RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				if(sortAndFilter.sorting.equals("newest")) {
					if(sortAndFilter.filter.equals("cash")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndCash();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";	
					} else if(sortAndFilter.filter.equals("card")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndCard();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("confirmed")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndConfirmed();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
								
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("cancelled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndCancelled();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("fulfilled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndFulfilled();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getNewestAndAll();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					}
				} else {
					if(sortAndFilter.filter.equals("cash")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndCash();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("card")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndCard();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("confirmed")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndConfirmed();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("cancelled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndCancelled();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("fulfilled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndFulfilled();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					} else {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
						deliveries = deliveryService.getOldestAndAll();
						Page<Delivery> deliveryPage = paginationService.findPaginatedDeliveryAll(PageRequest.of(currentPage - 1, pageSize), deliveries);
						
						int totalPages = deliveryPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("deliveryPage", deliveryPage);
						model.addAttribute("title", "Delivery");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "delivery");
						model.addAttribute("action", "delivery");
						return user.profileType + "/view";
					}
				}
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
}