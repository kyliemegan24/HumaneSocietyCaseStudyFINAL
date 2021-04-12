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
		
		// shows all employees in the database
		@PostMapping("viewEmps")
		public String viewEmpsByID(@ModelAttribute("employee") Employee emp, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn==null) {
				model.addAttribute("viewEmpSessionError", "You must be logged in to view all employees in the database");
				return "Employees";
			} else {
				model.addAttribute("empList", empService.getAllEmpService());
			}
			return "Employees";
		}
		
		// adds a new employee to the database
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
					model.addAttribute("addEmpLocError", "Please ensure that the Location ID matches the ID of an existing location");
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
		
		// gets employee by id 
		
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
		
		//updates existing employee in the database
		
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
			} else {
				Employee emp1 = empService.getEmpService(eId);
				int locId1 = emp1.getLocationId();
				int locId = emp.getLocationId();

				locService.removeEmpFromLocService(eId, locId1);
				locService.addEmpToLocService(eId, locId);
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
		
		// shows all locations in the database
				@PostMapping("/viewLocs")
				public String viewLocsByID(@ModelAttribute("location") Location loc, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (loggedIn==null) {
						model.addAttribute("viewLocSessionError", "You must be logged in to view all locations in the database");
						return "Locations";
					} else {
						model.addAttribute("locList", locService.getAllLocsService());
					}
					return "Locations";
				}
		
		
				@PostMapping("/addLocation")
				public String addNewLocation(@ModelAttribute("location") Location loc, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int locId = loc.getLocId();
					List<Location> locList = locService.getAllLocsService();
					List<Integer> locIds = new ArrayList<Integer>();
					for (Location e : locList) {
						locIds.add(e.getLocId());
					}
					if (result.hasErrors()) {
						model.addAttribute("addLocationError", "Please enter valid input");
						return "Locations";
					} else if (loggedIn==null) {
						model.addAttribute("addLocationSessionError", "You must be logged in to add a location to the database");
						return "Locations";
					} else if (locIds.contains(locId)) {
						model.addAttribute("addLocNoDuplicate", "This Id already belongs to another location");
						try {
							throw new NoDuplicateException("This Id already belongs to another location");
						} catch (NoDuplicateException e) {
							e.printStackTrace();
						}
						return "Locations"; 
					} else {
						locService.addLocService(loc);
						model.addAttribute("addLocationSuccess", "Location added to the database successfully!");
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
					int locId = loc.getLocId();
					List<Location> locList = locService.getAllLocsService();
					List<Integer> locIds = new ArrayList<Integer>();
					for (Location e : locList) {
						locIds.add(e.getLocId());
					}
					if (result.hasErrors()) {
						model.addAttribute("updateLocError", "Please try again, there was an error with your input fields");
						return "Locations";
					} else if (loggedIn==null) {
						model.addAttribute("updateLocSessionError", "Please log in to update");
						return "Locations";
					}  else {
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
				
				// shows all locations in the database
				
				@PostMapping("/viewCats")
				public String viewCatsByID(@ModelAttribute("cat") Cat cat, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (loggedIn==null) {
						model.addAttribute("viewCatSessionError", "You must be logged in to view all cats in the database");
						return "Cats";
					} else {
						model.addAttribute("catList", catService.getAllCatsService());
					}
					return "Cats";
				}
				
				// adds cat to database
				
				@PostMapping("/addCat")
				public String addNewCat(@ModelAttribute("cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int cId = cat.getCId();
					List<Cat> catList = catService.getAllCatsService();
					List<Integer> catIds = new ArrayList<Integer>();
					for (Cat e : catList) {
						catIds.add(e.getCId());
					}
					if (result.hasErrors()) {
						model.addAttribute("errorMessage", "Error, please try again");
						return "Cats";
					} else if (loggedIn==null) {
						model.addAttribute("addCatSessionError", "Please log in to complete this action");
						return "Cats";
					} else if (cat.getGender() <1 || cat.getGender() > 2) {
							model.addAttribute("addCatGenderError", "Please select a Gender value of 1 or 2");
							return "Cats";
						}
					 else if (catIds.contains(cId)) {
						model.addAttribute("addCatNoDuplicate", "This Id already belongs to another cat");
						try {
							throw new NoDuplicateException("This Id already belongs to another cat");
						} catch (NoDuplicateException e) {
							e.printStackTrace();
						}
						return "Cats";  
					} else {
						int locId = cat.getLocationId();
						Location location = locService.getLocService(locId);
						if (location==null) {
							model.addAttribute("addCatLocError", "Please ensure that the location ID matches the ID of an existing store");
							return "Cats";
						} else {
							
							catService.addCatService(cat);
							// locService.addCatToLocService(cId, locId);
							model.addAttribute("successMessage", "Cat added to the database successfully!");
							System.out.println("Cat added to database successfully");
						}
					}
					return "Cats";
				}
				
				//looks up cat by id
				
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
				
				// updates cat information in the database
				
				@PostMapping("/updateCat")
				public String updateCat(@ModelAttribute("cat") Cat cat, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int cId = cat.getCId();
					List<Cat> catList = catService.getAllCatsService();
					List<Integer> catIds = new ArrayList<Integer>();
					for (Cat e : catList) {
						catIds.add(e.getCId());
					}
					if (result.hasErrors()) {
						model.addAttribute("updateCatError", "Please try again, input field error");
						return "Cats";
					} else if (loggedIn==null) {
						model.addAttribute("updateCatSessionError", "You must be logged in to update a cat in the database");
						return "Cats";
					
					} else if (cat.getGender() <1 || cat.getGender() > 2) {
							model.addAttribute("updateCatGenderError", "Please select a Gender value of 1 or 2");
							return "Cats";
					} else {
						int locId = cat.getLocationId();
						Location location = locService.getLocService(locId);
						if (location==null) {
							model.addAttribute("updateCatLocError", "Please ensure that the location ID matches the ID of an existing store");
							return "Cats"; 
						} else {
						catService.updateCatService(cat);
						model.addAttribute("updateCatSuccess", "Cat updated successfully!");
						System.out.println("updated successfully");
					}
					
					return "Cats";
					}
				}
				
				//removes a cat from the database by id
				
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
	
				
				
				
			// Dog CRUD methods
				
				// shows all locations in the database
				@PostMapping("/viewDogs")
				public String viewDogsByID(@ModelAttribute("dog") Dog dog, Model model, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (loggedIn==null) {
						model.addAttribute("viewDogSessionError", "You must be logged in to view all dogs in the database");
						return "Dogs";
					} else {
						model.addAttribute("dogList", dogService.getAllDogsService());
					}
					return "Dogs";
				}
				
				// adds a dog to the database
				
				@PostMapping("/addDog")
				public String addNewDog(@ModelAttribute("dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					int dId = dog.getDId();
					List<Dog> dogList = dogService.getAllDogsService();
					List<Integer> dogIds = new ArrayList<Integer>();
					for (Dog e : dogList) {
						dogIds.add(e.getDId());
					}
					if (result.hasErrors()) {
						model.addAttribute("errorMessage", "Error, please try again");
						return "Dogs";
					} else if (loggedIn==null) {
						model.addAttribute("addDogSessionError", "Please log in to complete this action");
						return "Dogs";
					} else if (dogIds.contains(dId)) {
						model.addAttribute("addDogNoDuplicate", "This Id already belongs to another dog");
						try {
							throw new NoDuplicateException("This Id already belongs to another dog");
						} catch (NoDuplicateException e) {
							e.printStackTrace();
						}
						return "Dogs";  
					} else if (dog.getGender() <1 || dog.getGender() > 2) {
						model.addAttribute("addDogGenderError", "Please select a Gender value of 1 or 2");
						return "Dogs";
					} else {
						int locId = dog.getLocationId();
						Location location = locService.getLocService(locId);
						if (location==null) {
							model.addAttribute("Loc", "Please ensure that the location ID matches the ID of an existing location");
							return "Dogs";
						} else {
							
							dogService.addDogService(dog);
							
							model.addAttribute("addDogSuccess", "Dog added to the database successfully!");
							System.out.println("Dog added to database successfully");
						}
					}
					return "Dogs";
				}
				
				// gets dog from database by its id 
				
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
				
				//updates an existing dogs information in the database
				
				@PostMapping("/updateDog")
				public String updateDog(@ModelAttribute("dog") Dog dog, Model model, BindingResult result, HttpSession session) {
					Object loggedIn = session.getAttribute("currentUser");
					if (result.hasErrors()) {
						model.addAttribute("updateDogError", "Please try again, input field error");
						return "Dogs";
					} else if (loggedIn==null) {
						model.addAttribute("updateDogSessionError", "You must be logged in to update a dog in the database");
						return "Dogs";
					} else if (dog.getGender() <1 || dog.getGender() > 2) {
						model.addAttribute("updateDogGenderError", "Please select a Gender value of 1 or 2");
						return "Dogs";
					}else {
						dogService.updateDogService(dog);
						model.addAttribute("updateDogSuccess", "Dog updated successfully!");
					
					}
					
					return "Dogs";
				}
				
				// removes a dog from the database by id
				
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
