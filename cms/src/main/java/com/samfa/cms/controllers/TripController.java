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
public class TripController {
	@Autowired
	private PaginationService paginationService;
	private CustomerService customerService = new CustomerService();
	private DriverService driverService = new DriverService();
	private TripService tripService = new TripService();
	private MiscService miscService = new MiscService();
	private User user = LoginController.user;
	
	@GetMapping(path="/trip/delete/{id}")
	public String deleteTrip(@PathVariable String id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				tripService.deleteTripById(id);
				
				return "redirect:/trip";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/trip")
  public String viewTrip(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Trip> trips = new ArrayList<Trip>();
				trips = tripService.getAllTrips();
				Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
				
				int totalPages = tripPage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("sortAndFilter", new SortAndFilter());
				model.addAttribute("tripPage", tripPage);
				model.addAttribute("title", "Trip");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "trip");
				model.addAttribute("action", "trip");
				return user.profileType + "/view";
		
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@GetMapping("/trip/{id}")
  public String viewTripInfo(@PathVariable String id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Trip trip = tripService.getTripById(id);
				Customer customer = customerService.getCustomerById(trip.customerId);
				Driver driver = driverService.getDriverById(trip.driverId);
				Location pickup = miscService.getLocation(trip.pickupLocation);
				Location dropoff = miscService.getLocation(trip.dropoffLocation);
				
				model.addAttribute("trip", trip);
				model.addAttribute("customer", customer);
				model.addAttribute("driver", driver);
				model.addAttribute("pickup", pickup);
				model.addAttribute("dropoff", dropoff);
				model.addAttribute("title", "Trip Info");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "trip");
				model.addAttribute("action", "trip_info");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping("/trip/generate")
	public String viewReportType(@ModelAttribute SortAndFilter sortAndFilter, Model model,
		@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				if(sortAndFilter.sorting.equals("newest")) {
					if(sortAndFilter.filter.equals("cash")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndCash();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";	
					} else if(sortAndFilter.filter.equals("card")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndCard();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("confirmed")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndConfirmed();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("cancelled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndCancelled();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("fulfilled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndFulfilled();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getNewestAndAll();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					}
				} else {
					if(sortAndFilter.filter.equals("cash")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndCash();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("card")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndCard();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("confirmed")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndConfirmed();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("cancelled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndCancelled();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else if(sortAndFilter.filter.equals("fulfilled")) {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndFulfilled();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
						return user.profileType + "/view";
					} else {
						int currentPage = page.orElse(1);
						int pageSize = size.orElse(5);
						
						ArrayList<Trip> trips = new ArrayList<Trip>();
						trips = tripService.getOldestAndAll();
						Page<Trip> tripPage = paginationService.findPaginatedTripAll(PageRequest.of(currentPage - 1, pageSize), trips);
						
						int totalPages = tripPage.getTotalPages();
						
						if (totalPages > 0) {
							List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
							model.addAttribute("pageNumbers", pageNumbers);
						}
						
						model.addAttribute("sortAndFilter", new SortAndFilter());
						model.addAttribute("tripPage", tripPage);
						model.addAttribute("title", "Trip");
						model.addAttribute("profileType", user.profileType);
						model.addAttribute("view", "trip");
						model.addAttribute("action", "trip");
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