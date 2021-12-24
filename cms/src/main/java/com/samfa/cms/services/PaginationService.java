package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*The following codes are derived from: https://www.baeldung.com/spring-thymeleaf-pagination*/
@Service
public class PaginationService {
	
	public Page<Customer> findPaginatedCustomerAll(Pageable pageable, ArrayList<Customer> customers) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Customer> list;

		if (customers.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, customers.size());
				list = customers.subList(startItem, toIndex);
		}

		Page<Customer> customerPage = new PageImpl<Customer>(list, PageRequest.of(currentPage, pageSize), customers.size());

		return customerPage;
	}
	
	public Page<Driver> findPaginatedDriverAll(Pageable pageable, ArrayList<Driver> drivers) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Driver> list;

		if (drivers.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, drivers.size());
				list = drivers.subList(startItem, toIndex);
		}

		Page<Driver> driverPage = new PageImpl<Driver>(list, PageRequest.of(currentPage, pageSize), drivers.size());

		return driverPage;
	}
	
	public Page<Profile> findPaginatedProfileAll(Pageable pageable, ArrayList<Profile> profiles) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Profile> list;

		if (profiles.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, profiles.size());
				list = profiles.subList(startItem, toIndex);
		}

		Page<Profile> profilePage = new PageImpl<Profile>(list, PageRequest.of(currentPage, pageSize), profiles.size());

		return profilePage;
	}
	
	public Page<Vehicle> findPaginatedVehicleAll(Pageable pageable, ArrayList<Vehicle> vehicles) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Vehicle> list;

		if (vehicles.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, vehicles.size());
				list = vehicles.subList(startItem, toIndex);
		}

		Page<Vehicle> vehiclePage = new PageImpl<Vehicle>(list, PageRequest.of(currentPage, pageSize), vehicles.size());

		return vehiclePage;
	}
	
	public Page<Promo> findPaginatedPromoAll(Pageable pageable, ArrayList<Promo> promos) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Promo> list;

		if (promos.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, promos.size());
				list = promos.subList(startItem, toIndex);
		}

		Page<Promo> promoPage = new PageImpl<Promo>(list, PageRequest.of(currentPage, pageSize), promos.size());

		return promoPage;
	}
	
	public Page<Trip> findPaginatedTripAll(Pageable pageable, ArrayList<Trip> trips) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Trip> list;

		if (trips.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, trips.size());
				list = trips.subList(startItem, toIndex);
		}

		Page<Trip> tripPage = new PageImpl<Trip>(list, PageRequest.of(currentPage, pageSize), trips.size());

		return tripPage;
	}
	
	public Page<Delivery> findPaginatedDeliveryAll(Pageable pageable, ArrayList<Delivery> deliveries) throws Exception {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Delivery> list;

		if (deliveries.size() < startItem) {
				list = Collections.emptyList();
		} else {
				int toIndex = Math.min(startItem + pageSize, deliveries.size());
				list = deliveries.subList(startItem, toIndex);
		}

		Page<Delivery> deliveryPage = new PageImpl<Delivery>(list, PageRequest.of(currentPage, pageSize), deliveries.size());

		return deliveryPage;
	}
}