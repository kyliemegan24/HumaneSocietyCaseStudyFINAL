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
	
		
		
		
		//Employee CRUD Methods
		
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
		
		

		
		//Location CRUD methods
		
		
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
					int cId = cat.getCId();
					Cat loc = catService.getCatService(cId);
					if (result.hasErrors()) {
						model.addAttribute("addCatError", "There was an error with an input field, please try again");
						return "cats";
					} else if (loggedIn==null) {
						model.addAttribute("addCatSessionError", "You must be logged in to add a cat to the database");
						return "cats";
					} else if (loc==null) {
						model.addAttribute("addCatLocError", "The location ID must correspond with an existing location");
						return "cats";
					}
					catService.addCatService(cat);
					model.addAttribute("addCatSuccess", "cat added to the database successfully!");
					System.out.println("added to db successfully");
					return "cats";
				}
				
				@PostMapping("/getCat")
				public String getCat(@ModelAttribute("cat") Cat cat, @RequestParam("cId") int cId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					cat = catService.getCatService(cId);
					if (cat==null) {
						model.addAttribute("getCatError", "Please enter a valid cat id");
					} else if (loggedIn==null) {
						model.addAttribute("getCatSessionError", "You must be logged in to view a cat in the database");
						return "cats";
					} else {
						model.addAttribute("cId", cat.getCId() + ", ");
						model.addAttribute("model", cat.getName() + ", ");
						model.addAttribute("age", cat.getAge() + ", ");
						model.addAttribute("breed", cat.getBreed() + ", ");
						model.addAttribute("upToDateShots", cat.isUpToDateShots() + ", ");
						model.addAttribute("gender", cat.getGender() + ", ");
						model.addAttribute("locationId", cat.getLocationId() + ", ");
					}
					return "vehicles";
				}
				
				@PostMapping("/updateCat")
				public String updateCat(@ModelAttribute("cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateCatError", "Please try again, input field error");
						return "cats";
					} else if (loggedIn==null) {
						model.addAttribute("updateCatSessionError", "You must be logged in to update a cat in the database");
						return "cats";
					} else {
						catService.updateCatService(cat);
						model.addAttribute("updateCatSuccess", "Vehicle updated successfully!");
						System.out.println("updated successfully");
					}
					
					return "cats";
				}
				
				@PostMapping("/removeCat")
				public String removeCat(@ModelAttribute("cat") Cat cat, @RequestParam("cId") int cId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (catService.getCatService(cId)==null) {
						model.addAttribute("RemoveCatError", "Please enter ID of an existing cat");
					} else if (loggedIn==null) {
						model.addAttribute("removeCatSessionError", "You must be logged in to remove a cat in the database");
						return "cats";
					} else {
						catService.removeCatService(cId);
						model.addAttribute("removeCatSuccess", "cat removed from database");
					}
					return "cats";
				}
	
				
				
				
//			// Dog CRUD methods
				
				@PostMapping("/addDog")
				public String addNewDog(@ModelAttribute("Dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int dId = dog.getDId();
					Dog loc = dogService.getDogService(dId);
					if (result.hasErrors()) {
						model.addAttribute("addDogError", "There was an error with an input field, please try again");
						return "dogs";
					} else if (loggedIn==null) {
						model.addAttribute("addDogSessionError", "You must be logged in to add a dog to the database");
						return "dogs";
					} else if (loc==null) {
						model.addAttribute("addDogLocError", "The location ID must correspond with an existing location");
						return "dogs";
					}
					dogService.addDogService(dog);
					model.addAttribute("addDogSuccess", "dog added to the database successfully!");
					System.out.println("added to db successfully");
					return "dogs";
				}
				
				@PostMapping("/getDog")
				public String getDog(@ModelAttribute("Dog") Dog Dog, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					Dog = dogService.getDogService(dId);
					if (Dog==null) {
						model.addAttribute("getCatError", "Please enter a valid Dog id");
					} else if (loggedIn==null) {
						model.addAttribute("getCatSessionError", "You must be logged in to view a Dog in the database");
						return "dogs";
					} else {
						model.addAttribute("cId", Dog.getDId() + ", ");
						model.addAttribute("model", Dog.getName() + ", ");
						model.addAttribute("age", Dog.getAge() + ", ");
						model.addAttribute("breed", Dog.getBreed() + ", ");
						model.addAttribute("upToDateShots", Dog.isUpToDateShots() + ", ");
						model.addAttribute("gender", Dog.getGender() + ", ");
						model.addAttribute("locationId", Dog.getLocationId() + ", ");
					}
					return "dogs";
				}
				
				@PostMapping("/updateDog")
				public String updateDog(@ModelAttribute("dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateDogError", "Please try again, input field error");
						return "dogs";
					} else if (loggedIn==null) {
						model.addAttribute("updateCatSessionError", "You must be logged in to update a dog in the database");
						return "dogs";
					} else {
						dogService.updateDogService(dog);
						model.addAttribute("updateDogSuccess", "Dog updated successfully!");
						System.out.println("updated successfully");
					}
					
					return "dogs";
				}
				
				@PostMapping("/removeDog")
				public String removeDog(@ModelAttribute("dog") Dog dog, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (dogService.getDogService(dId)==null) {
						model.addAttribute("RemoveDogError", "Please enter ID of an existing dog");
					} else if (loggedIn==null) {
						model.addAttribute("removeDogSessionError", "You must be logged in to remove a dog in the database");
						return "dogs";
					} else {
						dogService.removeDogService(dId);
						model.addAttribute("removeDogSuccess", "Dog removed from database");
					}
					return "dogs";
				}

	}
