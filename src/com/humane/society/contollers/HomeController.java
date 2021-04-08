package com.humane.society.contollers;

import java.util.ArrayList;
import java.util.List;

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
import com.humane.society.exceptions.NoDuplicateException;
import com.humane.society.exceptions.NoZeroException;
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
		System.out.println("hello hello");
		return "index";
	}
	
	@GetMapping("/index")
	public String showHomePage() {
		System.out.println("hello hi");
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
			if (emp!=null && password.equals(emp.getPassword())) {
				session.setAttribute("currentUser", emp);
				model.addAttribute("loginSuccessMessage", "Welcome");
				return "LogIn";
			}
			model.addAttribute("loginFailedMessage", "Invalid Credentials");
			return "LogIn";
		}
	
	
		@PostMapping("/logout")
		public String processLogoutRequest(Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn!=null) {
				session.removeAttribute("currentUser");
			} else {
				model.addAttribute("logoutError", "Nobody is logged in!");
				return "LogIn";
			}
			return "LogIn";
		}
	
		
		
		
		//Employee CRUD Methods
		
		@PostMapping("/addEmp")
		public String addNewEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int eId = emp.getEId();
			List<Employee> empList = empService.getAllEmpService();
			List<Integer> empIds = new ArrayList<Integer>();
			for (Employee e : empList) {
				empIds.add(e.getEId());
			}
			
			
			if (result.hasErrors()) {
				model.addAttribute("errorMessage", "Error, please try again");
				return "Employees";
			} else if (loggedIn==null) {
				model.addAttribute("addEmpSessionError", "Please log in to complete this action");
				return "Employees";
			} else if (eId==0) {
				model.addAttribute("addEmpNoZero", "0 is not a valid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return "Employees"; 
				
				} else if (empIds.contains(eId)) {
					model.addAttribute("addEmpNoDuplicate", "This Id already belongs to another user");
					try {
						throw new NoDuplicateException("This Id already belongs to another user");
					} catch (NoDuplicateException e) {
						e.printStackTrace();
					}
					return "Employees"; 
					
					} else {
				int locId = emp.getLocationId();
				Location location = locService.getLocService(locId);
				if (location==null) {
					model.addAttribute("addEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return "Employees";
				} else {
					
					empService.addEmpService(emp);
					locService.addEmpToLocService(eId, locId);
					model.addAttribute("successMessage", "Employee added to the database successfully!");
					System.out.println("added to db successfully");
				}
			}
			return "Employees";
		}
		
		@PostMapping("/getEmp")
		public String getEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			emp = empService.getEmpService(eId);
			if (emp==null) {
				model.addAttribute("getEmpError", "Please enter a valid employee id");
				return "Employees";
			} else if (loggedIn==null) {
				model.addAttribute("getEmpSessionError", "You must be logged in to view an employee in the database");
				return "Employees";
			} else {
				System.out.println(emp.getFirstName());
				model.addAttribute("eId", "Employee Id: " +emp.getEId() + ", ");
				model.addAttribute("firstName", "First Name: " + emp.getFirstName() + ", ");
				model.addAttribute("lastName", "Last Name: " + emp.getLastName() + ", ");
				model.addAttribute("salary", "Salary: " + emp.getSalary() + ", ");
				model.addAttribute("position", "Position: " + emp.getPosition() + ", ");
				model.addAttribute("locationId","Location Id: " + emp.getLocationId() + ", ");
				model.addAttribute("password", emp.getPassword() + " ");
			}
			return "Employees";
		}
		
		@PostMapping("/updateEmp")
		public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int eId = emp.getEId();
			List<Employee> empList = empService.getAllEmpService();
			List<Integer> empIds = new ArrayList<Integer>();
			for (Employee e : empList) {
				empIds.add(e.getEId());
			}
			if (result.hasErrors()) {
				model.addAttribute("updateEmpError", "There was an error with an input field, please try again");
				return "Employees";
			} else if (loggedIn==null) {
				model.addAttribute("updateEmpSessionError", "You must be logged in to update an employee in the database");
				return "Employees";
			}  else if (empIds.contains(eId)) {
				model.addAttribute("addEmpNoDuplicate", "This Id already belongs to another user");
				try {
					throw new NoDuplicateException("This Id already belongs to another user");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return "Employees"; 
				
				} else {
				empService.updateEmpService(emp);
				model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
				System.out.println("updated successfully");
			}
			return "Employees";
		}
		
		@PostMapping("/removeEmp")
		public String removeEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			emp = empService.getEmpService(eId);
			if (empService.getEmpService(eId)==null) {
				model.addAttribute("removeEmpError", "Please enter the ID of an existing employee");
			} else if (loggedIn==null) {
				model.addAttribute("removeEmpSessionError", "You must be logged in to remove an employee from the database");
				return "Employees";
			} else {
				int locId = emp.getLocationId();
				locService.removeEmpFromLocService(eId, locId);
				empService.removeEmpService(eId);
				model.addAttribute("removeEmpSuccess", "Employee removed from database sucessfully!");
			}
			return "Employees";
		}
		
		

		
		//Location CRUD methods
		
		
				@PostMapping("/addLocation")
				public String addNewLocation(@ModelAttribute("location") Location loc, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("addLocationError", "Please enter valid input");
						return "Locations";
					} else if (loggedIn==null) {
						model.addAttribute("addLocationSessionError", "You must be logged in to add a store to the database");
						return "Locations";
					} else {
						locService.addLocService(loc);
						model.addAttribute("addLocationSuccess", "Store added to the database successfully!");
						System.out.println("added to db successfully");
					}
					return "Locations";
				}
				
				@PostMapping("/getLocation")
				public String getLocation(@ModelAttribute("location") Location loc, @RequestParam("locId") int locId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					loc = locService.getLocService(locId);
					if (loc==null) {
						model.addAttribute("getLocError", "Please enter the ID of an existing location");
					} else if (loggedIn==null) {
						model.addAttribute("getLocSessionError", "To view a location, please log in");
						return "Locations";
					} else {
						model.addAttribute("locId", "Location Id: " +  loc.getLocId() + ", ");
						model.addAttribute("name", "Name: " + loc.getName() + ", ");
						model.addAttribute("address", "Address: " +loc.getAddress() + " ");
						
					}
					return "Locations";
				}
				
				@PostMapping("/updateLocation")
				public String updateStore(@ModelAttribute("location") Location loc, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateLocError", "Please try again, there was an error with your input fields");
						return "Locations";
					} else if (loggedIn==null) {
						model.addAttribute("updateLocSessionError", "Please log in to update");
						return "Locations";
					} else {
						locService.updateLocService(loc);
						model.addAttribute("updateLocSuccess", "Location updated successfully!");
						System.out.println("updated successfully");
					}
					return "Locations";
				}
				
				@PostMapping("/removeLocation")
				public String removeStore(@ModelAttribute("location") Location loc, @RequestParam("locId") int locId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (locService.getLocService(locId)==null) {
						model.addAttribute("removeLocationError", "Please enter the ID of an existing location");
					} else if (loggedIn==null) {
						model.addAttribute("removeLocSessionError", "To remove a location from the database, please log in");
						return "Locations";
					} else {
						locService.removeLocService(locId);
						model.addAttribute("removeLocSuccess", "Location removed from database sucessfully!");
					}
					return "Locations";
				}
				
				
				@PostMapping("/getEmpList")
				public String getEmpList(@ModelAttribute("location") Location loc, @RequestParam("locId") int locId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (locService.getLocService(locId)==null) {
						model.addAttribute("empListLocError", "Please enter a valid location id");
					} else if (loggedIn==null) {
						model.addAttribute("empListSessionError", "You must be logged in to view");
						return "Locations";
					} else {
						model.addAttribute("empList", locService.viewAllEmpService(locId));
					}
					
					return "Locations";
				}
				
				
				//Cat CRUD methods 
				
				@PostMapping("/addCat")
				public String addNewCat(@ModelAttribute("cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("errorMessage", "Error, please try again");
						return "Cats";
					} else if (loggedIn==null) {
						model.addAttribute("addEmpSessionError", "Please log in to complete this action");
						return "Cats";
					} else {
						int locId = cat.getLocationId();
						Location location = locService.getLocService(locId);
						if (location==null) {
							model.addAttribute("Loc", "Please ensure that the location ID matches the ID of an existing store");
							return "Cats";
						} else {
							int cId = cat.getCId();
							catService.addCatService(cat);
							// locService.addCatToLocService(cId, locId);
							model.addAttribute("successMessage", "Cat added to the database successfully!");
							System.out.println("Cat added to database successfully");
						}
					}
					return "Cats";
				}
				
				@PostMapping("/getCat")
				public String getCat(@ModelAttribute("cat") Cat cat, @RequestParam("cId") int cId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					cat = catService.getCatService(cId);
					if (cat==null) {
						model.addAttribute("getCatError", "Please enter a valid cat id");
					} else if (loggedIn==null) {
						model.addAttribute("getCatSessionError", "You must be logged in to view a cat in the database");
						return "Cats";
					} else {
						model.addAttribute("cId", "Cat Id: " +cat.getCId() + ", ");
						model.addAttribute("name", "Name: " + cat.getName() + ", ");
						model.addAttribute("age", "Age: " + cat.getAge() + ", ");
						model.addAttribute("breed", "Breed: " + cat.getBreed() + ", ");
						model.addAttribute("upToDateShots", "Up to date shots: " + cat.isUpToDateShots() + ", ");
						model.addAttribute("gender", "Gender: " + cat.getGender() + ", ");
						model.addAttribute("locationId","Location Id: " + cat.getLocationId() + " ");
					}
					return "Cats";
				}
				
				@PostMapping("/updateCat")
				public String updateCat(@ModelAttribute("cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateCatError", "Please try again, input field error");
						return "Cats";
					} else if (loggedIn==null) {
						model.addAttribute("updateCatSessionError", "You must be logged in to update a cat in the database");
						return "Cats";
					} else {
						catService.updateCatService(cat);
						model.addAttribute("updateCatSuccess", "Cat updated successfully!");
						System.out.println("updated successfully");
					}
					
					return "Cats";
				}
				
				@PostMapping("/removeCat")
				public String removeCat(@ModelAttribute("cat") Cat cat, @RequestParam("cId") int cId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (catService.getCatService(cId)==null) {
						model.addAttribute("RemoveCatError", "Please enter ID of an existing cat");
					} else if (loggedIn==null) {
						model.addAttribute("removeCatSessionError", "You must be logged in to remove a cat in the database");
						return "Cats";
					} else {
						catService.removeCatService(cId);
						model.addAttribute("removeCatSuccess", "Cat removed from database");
					}
					return "Cats";
				}
	
				
				
				
//			// Dog CRUD methods
				
				@PostMapping("/addDog")
				public String addNewDog(@ModelAttribute("dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("errorMessage", "Error, please try again");
						return "Dogs";
					} else if (loggedIn==null) {
						model.addAttribute("addDogSessionError", "Please log in to complete this action");
						return "Dogs";
					} else {
						int locId = dog.getLocationId();
						Location location = locService.getLocService(locId);
						if (location==null) {
							model.addAttribute("Loc", "Please ensure that the location ID matches the ID of an existing location");
							return "Dogs";
						} else {
							int dId = dog.getDId();
							dogService.addDogService(dog);
							// locService.addCatToLocService(cId, locId);
							model.addAttribute("addDogSuccess", "Dog added to the database successfully!");
							System.out.println("Dog added to database successfully");
						}
					}
					return "Dogs";
				}
				
				@PostMapping("/getDog")
				public String getDog(@ModelAttribute("dog") Dog dog, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					dog = dogService.getDogService(dId);
					if (dog==null) {
						model.addAttribute("getCatError", "Please enter a valid dog id");
					} else if (loggedIn==null) {
						model.addAttribute("getDogSessionError", "You must be logged in to view a dog in the database");
						return "Dogs";
					} else {
						model.addAttribute("dId","Dog Id: " + dog.getDId() + ", ");
						model.addAttribute("name", "Name: " + dog.getName() + ", ");
						model.addAttribute("age", "Age: " + dog.getAge() + ", ");
						model.addAttribute("breed","Breed: " + dog.getBreed() + ", ");
						model.addAttribute("upToDateShots", "Up to date shots: " + dog.isUpToDateShots() + ", ");
						model.addAttribute("gender", "Gender: " + dog.getGender() + ", ");
						model.addAttribute("locationId", "Location Id: " + dog.getLocationId() + " ");
					}
					return "Dogs";
				}
				
				@PostMapping("/updateDog")
				public String updateDog(@ModelAttribute("dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateDogError", "Please try again, input field error");
						return "Dogs";
					} else if (loggedIn==null) {
						model.addAttribute("updateDogSessionError", "You must be logged in to update a dog in the database");
						return "Dogs";
					} else {
						dogService.updateDogService(dog);
						model.addAttribute("updateDogSuccess", "Dog updated successfully!");
						System.out.println("updated successfully");
					}
					
					return "Dogs";
				}
				
				@PostMapping("/removeDog")
				public String removeDog(@ModelAttribute("dog") Dog dog, @RequestParam("dId") int dId, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (dogService.getDogService(dId)==null) {
						model.addAttribute("RemoveDogError", "Please enter ID of an existing dog");
					} else if (loggedIn==null) {
						model.addAttribute("removeDogSessionError", "You must be logged in to remove a dog in the database");
						return "Dogs";
					} else {
						dogService.removeDogService(dId);
						model.addAttribute("removeDogSuccess", "Dog removed from database");
					}
					return "Dogs";
				}

	}
