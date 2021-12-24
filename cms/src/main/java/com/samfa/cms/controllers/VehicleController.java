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
public class VehicleController {
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private PaginationService paginationService;
	private VehicleService vehicleService = new VehicleService();
	private User user = LoginController.user;
	
	@GetMapping("/vehicle/add")
  public String addCustomerForm(Model model) {
		try {
			if(user.isLoggedIn) {
				model.addAttribute("vehicle", new Vehicle());
				model.addAttribute("title", "Add Vehicle");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "vehicle");
				model.addAttribute("action", "vehicle_add");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/vehicle/add")
	public String addVehicleResult(@ModelAttribute Vehicle vehicle, Model model) throws Exception {	
		if(vehicleService.checkPlateNumber(vehicle.plateNumber)) {
			model.addAttribute("error", "Plate number already exists.");
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("title", "Add Vehicle");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "vehicle");
			model.addAttribute("action", "vehicle_add");
			return user.profileType + "/view";
		} else {
			vehicle.setPlateNumber(vehicle.plateNumber);
			vehicle.setBrand(vehicle.brand);
			vehicle.setModel(vehicle.model);
			vehicle.setPassengerCapacity(vehicle.passengerCapacity);
			vehicle.setLoadCapacity(vehicle.loadCapacity);
			vehicle.setStatus(vehicle.status);
			vehicle.setAssignability("Assignable");
			
			vehicleRepository.save(vehicle);
			
			return "redirect:/vehicle";
		}
	}
	
	@GetMapping(path="/vehicle/delete/{id}")
	public String deleteVehicle(@PathVariable Integer id, Model model) throws Exception {		
		try {
			if(user.isLoggedIn) {
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicleId(id);
				vehicleRepository.delete(vehicle);
				
				return "redirect:/vehicle";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/vehicle/edit/{id}")
  public String editVehicleForm(@PathVariable Integer id, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				Vehicle vehicle = vehicleRepository.findById(id).get();

				model.addAttribute("vehicle", vehicle);
				model.addAttribute("title", "Edit Vehicle");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "vehicle");
				model.addAttribute("action", "vehicle_edit");
				
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
  }
	
	@PostMapping(path="/vehicle/edit/{id}")
	public String editVehicle(@PathVariable Integer id, @ModelAttribute Vehicle newVehicle, Model model) throws Exception{
		Vehicle vehicle = vehicleRepository.findById(id).get();
		if(vehicleService.checkOtherPlateNumber(newVehicle.plateNumber, id)) {
			model.addAttribute("error", "Plate number already exists.");
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("title", "Edit Vehicle");
			model.addAttribute("profileType", user.profileType);
			model.addAttribute("view", "vehicle");
			model.addAttribute("action", "vehicle_edit");
			
			return user.profileType + "/view";
		} else {
			vehicle.setPlateNumber(newVehicle.plateNumber);
			vehicle.setBrand(newVehicle.brand);
			vehicle.setModel(newVehicle.model);
			vehicle.setPassengerCapacity(newVehicle.passengerCapacity);
			vehicle.setLoadCapacity(newVehicle.loadCapacity);
			vehicle.setStatus(newVehicle.status);
			vehicle.setAssignability(newVehicle.assignability);
			vehicleRepository.save(vehicle);
			
			return "redirect:/vehicle";
		}
	}
	
	@GetMapping("/vehicle")
  public String viewVehicle(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
		Optional<Integer> size) throws Exception {
		try {
			if(user.isLoggedIn) {
				int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        
				ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
				vehicles = vehicleService.getAllVehicles();
				Page<Vehicle> vehiclePage = paginationService.findPaginatedVehicleAll(PageRequest.of(currentPage - 1, pageSize), vehicles);
				
				int totalPages = vehiclePage.getTotalPages();
				
        if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
        }
				
				model.addAttribute("vehiclePage", vehiclePage);
				model.addAttribute("title", "Vehicle");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "vehicle");
				model.addAttribute("action", "vehicle");
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