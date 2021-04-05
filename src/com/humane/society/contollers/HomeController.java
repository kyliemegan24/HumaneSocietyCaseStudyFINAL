package com.humane.society.contollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		public String processLoginRequest(@RequestParam("eId") int eId, @RequestParam("email") String email, Model model, HttpSession session) {
			Employee emp = empService.getEmpService(eId);
			if (emp!=null && emp.getPosition().equals("General Manager") && email.equals(emp.getEId())) {
				session.setAttribute("currentUser", emp);
				model.addAttribute("loginSuccessMessage", "Welcome,");
				return "login";
			}
			model.addAttribute("loginFailedMessage", "Invalid Credentials");
			return "login";
		}
	
	
	
	
	
	

}
