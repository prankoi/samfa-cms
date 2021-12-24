package com.samfa.cms.controllers;

import com.samfa.cms.models.*;
import com.samfa.cms.models.reports.*;
import com.samfa.cms.services.*;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {
	
	private ReportService reportService = new ReportService();
	private DriverService driverService = new DriverService();
	private User user = LoginController.user;
	
	@GetMapping("/report")
	public String viewReport(Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				ArrayList<Driver> drivers = new ArrayList<Driver>();
				drivers = driverService.getAllDrivers();
				
				model.addAttribute("drivers", drivers);
				model.addAttribute("report", new Report());
				model.addAttribute("reportDriver", new ReportDriver());
				model.addAttribute("title", "Report");
				model.addAttribute("profileType", user.profileType);
				model.addAttribute("view", "report");
				model.addAttribute("action", "report");
				return user.profileType + "/view";
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@PostMapping("/report/generate")
	public String viewReportType(@ModelAttribute Report report, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				if(report.reportType.equals("totalCash")) {
					Integer totalCashTrip = 0;
					Integer totalCashDelivery = 0;
					Integer totalCash = 0;
					
					totalCashTrip = reportService.totalCashPaymentTrip();
					totalCashDelivery = reportService.totalCashPaymentDelivery();
					totalCash = reportService.totalCashPayment();
					
					model.addAttribute("totalCashTrip", totalCashTrip);
					model.addAttribute("totalCashDelivery", totalCashDelivery);
					model.addAttribute("totalCash", totalCash);
					model.addAttribute("title", "Total Cash Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "total_cash");
					return user.profileType + "/view";
				} else if(report.reportType.equals("totalCard")) {
					Integer totalCardTrip = 0;
					Integer totalCardDelivery = 0;
					Integer totalCard = 0;
					
					totalCardTrip = reportService.totalCardPaymentTrip();
					totalCardDelivery = reportService.totalCardPaymentDelivery();
					totalCard = reportService.totalCardPayment();
					
					model.addAttribute("totalCardTrip", totalCardTrip);
					model.addAttribute("totalCardDelivery", totalCardDelivery);
					model.addAttribute("totalCard", totalCard);
					model.addAttribute("title", "Total Card Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "total_card");
					return user.profileType + "/view";
				} else if(report.reportType.equals("dailyTrip")) {
					ArrayList<Daily> dailies = new ArrayList<Daily>();
					dailies = reportService.getDailyIncomeTrip();
					
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income_trip");
					return user.profileType + "/view";
				} else if(report.reportType.equals("weeklyTrip")) {
					ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
					weeklies = reportService.getWeeklyIncomeTrip();
					
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income_trip");
					return user.profileType + "/view";
				} else if(report.reportType.equals("monthlyTrip")) {
					ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
					monthlies = reportService.getMonthlyIncomeTrip();
					
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income_trip");
					return user.profileType + "/view";
				} else if(report.reportType.equals("dailyDelivery")) {
					ArrayList<Daily> dailies = new ArrayList<Daily>();
					dailies = reportService.getDailyIncomeDelivery();
					
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income_delivery");
					return user.profileType + "/view";
				} else if(report.reportType.equals("weeklyDelivery")) {
					ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
					weeklies = reportService.getWeeklyIncomeDelivery();
					
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income_delivery");
					return user.profileType + "/view";
				} else if(report.reportType.equals("monthlyDelivery")) {
					ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
					monthlies = reportService.getMonthlyIncomeDelivery();
					
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income_delivery");
					return user.profileType + "/view";
				} else if(report.reportType.equals("daily")) {
					ArrayList<Daily> dailies = new ArrayList<Daily>();
					dailies = reportService.getDailyIncome();
					
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income");
					return user.profileType + "/view";
				} else if(report.reportType.equals("weekly")) {
					ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
					weeklies = reportService.getWeeklyIncome();
					
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income");
					return user.profileType + "/view";
				} else if(report.reportType.equals("monthly")) {
					ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
					monthlies = reportService.getMonthlyIncome();
					
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income");
					return user.profileType + "/view";
				} else {
					model.addAttribute("report", new Report());
					model.addAttribute("title", "Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "report");
					return user.profileType + "/view";
				}
			} else {
				model.addAttribute("user", user);
				return "login";
			}
			
		} catch(Exception e) {
			return "error";
		}
	}
	
	@PostMapping("/report/generate/driver")
	public String viewReportType(@ModelAttribute ReportDriver reportDriver, Model model) throws Exception {
		try {
			if(user.isLoggedIn) {
				if(reportDriver.reportType.equals("dailyTrip")) {
					ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					dailies = reportService.getDailyIncomeTripByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income_trip_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("weeklyTrip")) {
					ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					weeklies = reportService.getWeeklyIncomeTripByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income_trip_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("monthlyTrip")) {
					ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					monthlies = reportService.getMonthlyIncomeTripByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Trip Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income_trip_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("dailyDelivery")) {
					ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					dailies = reportService.getDailyIncomeDeliveryByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income_delivery_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("weeklyDelivery")) {
					ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					weeklies = reportService.getWeeklyIncomeDeliveryByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income_delivery_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("monthlyDelivery")) {
					ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					monthlies = reportService.getMonthlyIncomeDeliveryByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Delivery Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income_delivery_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("daily")) {
					ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					dailies = reportService.getDailyIncomeByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("dailies", dailies);
					model.addAttribute("title", "Total Daily Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "daily_income_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("weekly")) {
					ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					weeklies = reportService.getWeeklyIncomeByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("weeklies", weeklies);
					model.addAttribute("title", "Total Weekly Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "weekly_income_driver");
					return user.profileType + "/view";
				} else if(reportDriver.reportType.equals("monthly")) {
					ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
					Driver driver = new Driver();
					
					driver = driverService.getDriverById(reportDriver.driverId);
					monthlies = reportService.getMonthlyIncomeByDriver(reportDriver.driverId);
					
					model.addAttribute("driver", driver);
					model.addAttribute("monthlies", monthlies);
					model.addAttribute("title", "Total Monthly Income Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "monthly_income_driver");
					return user.profileType + "/view";
				} else {
					ArrayList<Driver> drivers = new ArrayList<Driver>();
					drivers = driverService.getAllDrivers();
					
					model.addAttribute("drivers", drivers);
					model.addAttribute("report", new Report());
					model.addAttribute("reportDriver", new ReportDriver());
					model.addAttribute("title", "Report");
					model.addAttribute("profileType", user.profileType);
					model.addAttribute("view", "report");
					model.addAttribute("action", "report");
					return user.profileType + "/view";
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