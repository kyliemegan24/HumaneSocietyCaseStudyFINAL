package com.humane.society.contollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.humane.society.entity.Cat;
import com.humane.society.entity.Dog;
import com.humane.society.entity.Employee;
import com.humane.society.entity.Location;
import com.humane.society.service.CatService;
import com.humane.society.service.DogService;
import com.humane.society.service.EmployeeService;
import com.humane.society.service.LocationService;

@Controller
public class HomeController {
	
	private EmployeeService empService;
	private CatService catService;
	private DogService dogService;
	private LocationService locService;
	
	@Autowired
	public HomeController(EmployeeService empService, CatService catService, DogService dogService, LocationService locService) {
		this.empService = empService;
		this.catService = catService;
		this.dogService = dogService;
		this.locService = locService;
	}
	
	@GetMapping("/")
	public String showIndexPage() {
		
		return "index";
	}
	
	@GetMapping("/index")
	public String showHomePage() {
		
		return "index";
	}
	
	@GetMapping("/Cats")
	public String showCatsPage(Model model) {
		model.addAttribute("cat", new Cat());
		return "Cats";
	}
	
	@GetMapping("/Dogs")
	public String showDogsPage(Model model) {
		model.addAttribute("dog", new Dog());
		return "Dogs";
	}
	
	@GetMapping("/Employees")
	public String showEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "Employees";
	}
	
	@GetMapping("/Locations")
	public String showLocationsPage(Model model) {
		model.addAttribute("location", new Location());
		return "Locations";
	}
	
	@GetMapping("/LogIn")
	public String showLogInPage() {
		return "LogIn";
	}
	
	
	//login functionality
		@PostMapping("/LogIn")
		public String processLoginRequest(@RequestParam("eId") int eId, @RequestParam("password") String password, Model model, HttpSession session) {
			Employee emp = empService.getEmpService(eId);
			if (emp!=null && password.equals(emp.getEId())) {
				session.setAttribute("currentUser", emp);
				model.addAttribute("loginSuccessMessage", "Welcome,");
				return "login";
			}
			model.addAttribute("loginFailedMessage", "Invalid Credentials");
			return "login";
		}
	
	
		@PostMapping("/logout")
		public String processLogoutRequest(Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn!=null) {
				session.removeAttribute("currentUser");
			} else {
				model.addAttribute("logoutError", "Nobody is logged in!");
				return "logout";
			}
			return "login";
		}
	
		
		
		
		//Employee Methods
		
		@PostMapping("/addEmp")
		public String addNewEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("errorMessage", "Error, please try again");
				return "employees";
			} else if (loggedIn==null) {
				model.addAttribute("addEmpSessionError", "Please log in to complete this action");
				return "employees";
			} else {
				int locId = emp.getLocationId();
				Location location = locService.getLocService(locId);
				if (location==null) {
					model.addAttribute("addEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return "employees";
				} else {
					int eId = emp.getEId();
					empService.addEmpService(emp);
					locService.addEmpToLocService(eId, locId);
					model.addAttribute("successMessage", "Employee added to the database successfully!");
					System.out.println("added to db successfully");
				}
			}
			return "employees";
		}
		
		@PostMapping("/getEmp")
		public String getEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			emp = empService.getEmpService(eId);
			if (emp==null) {
				model.addAttribute("getEmpError", "Please enter a valid employee id");
				return "employees";
			} else if (loggedIn==null) {
				model.addAttribute("getEmpSessionError", "You must be logged in to view an employee in the database");
				return "employees";
			} else {
				System.out.println(emp.getFirstName());
				model.addAttribute("eId", emp.getEId() + ", ");
				model.addAttribute("firstName", emp.getFirstName() + ", ");
				model.addAttribute("lastName", emp.getLastName() + ", ");
				model.addAttribute("salary", emp.getSalary() + ", ");
				model.addAttribute("position", emp.getPosition() + ", ");
				model.addAttribute("locationId", emp.getLocationId() + ", ");
				model.addAttribute("password", emp.getPassword() + ", ");
			}
			return "employees";
		}
		
		@PostMapping("/updateEmp")
		public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("updateEmpError", "There was an error with an input field, please try again");
				return "employees";
			} else if (loggedIn==null) {
				model.addAttribute("updateEmpSessionError", "You must be logged in to update an employee in the database");
				return "employees";
			} else {
				empService.updateEmpService(emp);
				model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
				System.out.println("updated successfully");
			}
			return "employees";
		}
		
		@PostMapping("/removeEmp")
		public String removeEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			emp = empService.getEmpService(eId);
			if (empService.getEmpService(eId)==null) {
				model.addAttribute("removeEmpError", "Please enter the ID of an existing employee");
			} else if (loggedIn==null) {
				model.addAttribute("removeEmpSessionError", "You must be logged in to remove an employee from the database");
				return "employees";
			} else {
				int locId = emp.getLocationId();
				locService.removeEmpFromLocService(eId, locId);
				empService.removeEmpService(eId);
				model.addAttribute("removeEmpSuccess", "Employee removed from database sucessfully!");
			}
			return "employees";
		}
		
		

		
		//STORE METHODS
		
		
				@PostMapping("/addLocation")
				public String addNewLocation(@ModelAttribute("location") Location loc, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("addLocationError", "Please enter valid input");
						return "locations";
					} else if (loggedIn==null) {
						model.addAttribute("addLocationSessionError", "You must be logged in to add a store to the database");
						return "locations";
					} else {
						locService.addLocService(loc);
						model.addAttribute("addStoreSuccess", "Store added to the database successfully!");
						System.out.println("added to db successfully");
					}
					return "stores";
				}
				
				@PostMapping("/getLocation")
				public String getLocation(@ModelAttribute("location") Location loc, @RequestParam("locId") int locId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					loc = locService.getLocService(locId);
					if (loc==null) {
						model.addAttribute("getLocError", "Please enter the ID of an existing location");
					} else if (loggedIn==null) {
						model.addAttribute("getLocSessionError", "To view a location, please log in");
						return "locations";
					} else {
						model.addAttribute("locId", loc.getLocId() + ", ");
						model.addAttribute("name", loc.getName() + ", ");
						model.addAttribute("address", loc.getAddress() + ", ");
						
					}
					return "stores";
				}
				
				@PostMapping("/updateLocation")
				public String updateStore(@ModelAttribute("location") Location loc, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateLocError", "Please try again, there was an error with your input fields");
						return "locations";
					} else if (loggedIn==null) {
						model.addAttribute("updateLocSessionError", "Please log in to update");
						return "locations";
					} else {
						locService.updateLocService(loc);
						model.addAttribute("updateLocSuccess", "Location updated successfully!");
						System.out.println("updated successfully");
					}
					return "locations";
				}
				
				@PostMapping("/removeLocation")
				public String removeStore(@ModelAttribute("store") Location loc, @RequestParam("locId") int locId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (locService.getLocService(locId)==null) {
						model.addAttribute("removeLocationError", "Please enter the ID of an existing location");
					} else if (loggedIn==null) {
						model.addAttribute("removeLocSessionError", "To remove a location from the database, please log in");
						return "locations";
					} else {
						locService.removeLocService(locId);
						model.addAttribute("removeLocSuccess", "Location removed from database sucessfully!");
					}
					return "locations";
				}
				
				
				
				//Cat CRUD methods
				
				@PostMapping("/addCat")
				public String addNewCat(@ModelAttribute("Cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int eId = cat.getCId();
					Employee driver = empService.getEmpService(eId);
					if (result.hasErrors()) {
						model.addAttribute("addCarError", "There was an error with an input field, please try again");
						return "vehicles";
					} else if (loggedIn==null) {
						model.addAttribute("addCarSessionError", "You must be logged in to add a vehicle to the database");
						return "vehicles";
					} else if (driver==null) {
						model.addAttribute("addCarEmpError", "The Driver ID must correspond with an existing employee");
						return "vehicles";
					}
					carService.addCarService(car);
					model.addAttribute("addCarSuccess", "Vehicle added to the database successfully!");
					System.out.println("added to db successfully");
					return "vehicles";
				}
				
				@PostMapping("/getCar")
				public String getCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					car = carService.getCarService(dId);
					if (car==null) {
						model.addAttribute("getCarError", "Please enter the ID of an existing vehicle");
					} else if (loggedIn==null) {
						model.addAttribute("getCarSessionError", "You must be logged in to view a vehicle in the database");
						return "vehicles";
					} else {
						model.addAttribute("dId", car.getdId() + ", ");
						model.addAttribute("model", car.getModel() + ", ");
						model.addAttribute("year", car.getYear() + ", ");
						model.addAttribute("color", car.getColor() + ", ");
						model.addAttribute("insuranceProvider", car.getInsuranceProvider() + ", ");
						model.addAttribute("driverId", car.getDriverId() + ", ");
					}
					return "vehicles";
				}
				
				@PostMapping("/updateCar")
				public String updateCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateCarError", "There was an error with an input field, please try again");
						return "vehicles";
					} else if (loggedIn==null) {
						model.addAttribute("updateCarSessionError", "You must be logged in to update a vehicle in the database");
						return "vehicles";
					} else {
						carService.updateCarService(car);
						model.addAttribute("updateCarSuccess", "Vehicle updated successfully!");
						System.out.println("updated successfully");
					}
					return "vehicles";
				}
				
				@PostMapping("/removeCar")
				public String removeCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (carService.getCarService(dId)==null) {
						model.addAttribute("RemoveCarError", "Please enter ID of an existing vehicle");
					} else if (loggedIn==null) {
						model.addAttribute("removeCarSessionError", "You must be logged in to remove a vehicle in the database");
						return "vehicles";
					} else {
						carService.removeCarService(dId);
						model.addAttribute("removeCarSuccess", "Vehicle removed from database sucessfully!");
					}
					return "vehicles";
				}
	

}
