package at.fh.swenga.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.account.service.SecurityService;
import at.fh.swenga.account.service.SecurityServiceImpl;
import at.fh.swenga.account.service.UserService;
import at.fh.swenga.account.service.UserServiceImpl;
import at.fh.swenga.account.validator.UserValidator;
import at.fh.swenga.dao.IngredientRepository;
import at.fh.swenga.dao.RecipeRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserRoleModel;

@Controller
public class LoginController {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private UserService userService;
	
	


	@Autowired
	private SecurityService securityService;
	

	@Autowired
	private UserValidator userValidator;

	

	/*
	 * @RequestMapping("/fillRecipeList")
	 * 
	 * @Transactional public String fillData(Model model) {
	 * 
	 * UserModel user1 = new UserModel(1,"kevin","stessel","kiv1995","cobra11");
	 * 
	 * RecipeModel p1 = new RecipeModel(1,"Cordon Bleu", "Gefülltes Schnitzel",
	 * "hahahahah"); p1.setUsermodel(user1); recipeRepository.persist(p1);
	 * 
	 * /*RecipeModel p2 = new RecipeModel(2,"Schnitzel", "Schnitzel",
	 * "fritieren"); //p2.setUsermodel(user2); recipeRepository.persist(p2);
	 * 
	 * RecipeModel p3 = new RecipeModel(3,"Erdberren", "Doe", "now");
	 * //p3.setUsermodel(user3); recipeRepository.persist(p3);
	 * 
	 * return "forward:list"; }
	 */


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserModel());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute UserModel user, BindingResult bindingResult, Model model) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is used<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "registration";
		}

		userService.save(user);

		//securityService.autologin(user.getUsername(), user.getPasswordConfirm());

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin(Model model, String error, String logout, Principal principal) {
		

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

		return "login";
	}

	
}