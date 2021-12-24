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
public class DriverController {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private PaginationService paginationService;
	private LoginService loginService = new LoginService();
	private DriverService driverService = new DriverService();
	private VehicleService vehicleService = new VehicleService();
	private User user = LoginController.user;
	
	@GetMapping("/driver/add")
  public String addDriverForm(Model model) throws Exception{
		try {
			if(user.isLoggedIn) {
				ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
				vehicles = vehicleService.getAssignableVehicles();
				
				model.addAttribute("driver", new Driver());
				model.addAttribute("vehicles", vehicles);
				model.addAttribute("title", "Add Driver");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "driver");
				model.addAttribute("action", "driver_add");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/driver/add")
	public String addDriverResult(@ModelAttribute Driver driver, Model model) throws Exception {		
		if(driverService.checkEmailAddress(driver.emailAddress)) {
			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles = vehicleService.getAssignableVehicles();
				
			model.addAttribute("error", "Email address already exists.");
			model.addAttribute("driver", driver);
			model.addAttribute("vehicles", vehicles);
			model.addAttribute("title", "Add Driver");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "driver");
			model.addAttribute("action", "driver_add");
			
			return user.profileType + "/view";
		} else {
			driver.setVehicleId(driver.vehicleId);
			driver.setFirstName(driver.firstName);
			driver.setLastName(driver.lastName);
			driver.setEmailAddress(driver.emailAddress);
			driver.setCellphoneNumber(driver.cellphoneNumber);
			driver.setPassword(loginService.hash(driver.password));
			vehicleService.updateAssignableStatus(driver.vehicleId, "Not Assignable");
			
			driverRepository.save(driver);
			
			return "redirect:/driver";
		}
	}
	
	@GetMapping(path="/driver/delete/{id}")
	public String deleteDriver(@PathVariable Integer id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				Driver driver = new Driver();
				driver = driverService.getDriverById(id);
				vehicleService.updateAssignableStatus(driver.vehicleId, "Assignable");
				
				driverRepository.delete(driver);
				return "redirect:/driver";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/driver/edit/{id}")
  public String editDriverForm(@PathVariable Integer id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
				Driver driver = driverService.getDriverById(id);
				vehicles = vehicleService.getAssignableVehicles();

				model.addAttribute("driver", driver);
				model.addAttribute("vehicles", vehicles);
				model.addAttribute("title", "Edit Driver");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "driver");
				model.addAttribute("action", "driver_edit");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/driver/edit/{id}")
	public String editDriver(@PathVariable Integer id, @ModelAttribute Driver newDriver, Model model) throws Exception{
		Driver driver = driverService.getDriverById(id);
		if(driverService.checkOtherEmailAddress(newDriver.emailAddress, id)) {
			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles = vehicleService.getAssignableVehicles();
			
			model.addAttribute("error", "Email address already exists.");
			model.addAttribute("driver", driver);
			model.addAttribute("vehicles", vehicles);
			model.addAttribute("title", "Edit Driver");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "driver");
			model.addAttribute("action", "driver_edit");
			
			return user.profileType + "/view";
		} else {
			driver.setFirstName(newDriver.firstName);
			driver.setLastName(newDriver.lastName);
			driver.setEmailAddress(newDriver.emailAddress);
			driver.setCellphoneNumber(newDriver.cellphoneNumber);
			if(!newDriver.password.isEmpty()) {
				driver.setPassword(loginService.hash(newDriver.password));
			}
			
			driverRepository.save(driver);
			
			return "redirect:/driver";
		}
	}
	
	@PostMapping(path="/driver/assign/{id}")
	public String editDriverVehicle(@PathVariable Integer id, @ModelAttribute Driver newDriver, Model model) throws Exception{
		Driver driver = driverRepository.findById(id).get();
		vehicleService.updateAssignableStatus(driver.vehicleId, "Assignable");
		driver.setVehicleId(newDriver.vehicleId);
		vehicleService.updateAssignableStatus(newDriver.vehicleId, "Not Assignable");

		driverRepository.save(driver);
		
		return "redirect:/driver";
	}
	
	@GetMapping("/driver")
  public String viewDriver(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {	
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Driver> drivers = new ArrayList<Driver>();
				drivers = driverService.getAllDrivers();
				Page<Driver> driverPage = paginationService.findPaginatedDriverAll(PageRequest.of(currentPage - 1, pageSize), drivers);
				
				
				int totalPages = driverPage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("driverPage", driverPage);
				model.addAttribute("title", "Driver");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "driver");
				model.addAttribute("action", "driver");
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