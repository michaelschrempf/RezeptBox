package at.fh.swenga.controller;

import java.security.Principal;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import at.fh.swenga.account.service.SecurityService;
import at.fh.swenga.account.service.SecurityServiceImpl;
import at.fh.swenga.account.service.UserService;
import at.fh.swenga.account.service.UserServiceImpl;
import at.fh.swenga.account.validator.UserValidator;
import at.fh.swenga.dao.IngredientRepository;
import at.fh.swenga.dao.RecipeCategoryRepository;
import at.fh.swenga.dao.RecipeRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.manager.UserManager;
import at.fh.swenga.model.RecipeCategoryModel;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserRoleModel;

@Controller
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private RecipeCategoryRepository recipeCategoryRepository;

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



	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		
		
		
		List<RecipeModel> recipes = recipeRepository.findAll();

		model.addAttribute("recipes", recipes);
		
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "index";
	}

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

	@RequestMapping("/deleteRecipe")
	public String deleteRecipe(Model model, @RequestParam int id, Principal principal) {
		recipeRepository.delete(id);

		return "forward:list";
	}
	
	@RequestMapping("/showRecipe")
	public String showRecipe(Model model, @RequestParam int id) {
		model.addAttribute("recipes", recipeRepository.findByIdRecipe(id));
		return "showRecipe";
	}

	@RequestMapping("/searchRecipes")
	public String searchRecipes(Model model, @RequestParam String searchString) {
		model.addAttribute("recipes", recipeRepository.doANameSearchWithLike("%"+searchString+"%"));
		return "index";
	}
	
	@RequestMapping("/find")
	public String findRecipes(Model model, @RequestParam String searchString, @RequestParam String searchType) {
		List<RecipeModel> recipes = null;
		int count=0;

		switch (searchType) {
		case "query1":
			recipes = recipeRepository.findAll();
			break;
		case "query2":
			recipes = recipeRepository.findByRecipeCategoryModelName(searchString);
			break;
		case "query3":
			recipes = recipeRepository.findByUserModelUsername(searchString);
			break;
		
		

		default:
			recipes = recipeRepository.findAll();
		}
		
		model.addAttribute("recipes", recipes);

		
		return "index";
	}
	
	
	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String showAddRecipeForm(Model model,@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel) {
		List<RecipeCategoryModel> recipeCategoryModels = recipeCategoryRepository.findAll();
		model.addAttribute("recipeCategoryModels",recipeCategoryModels);
		return "editRecipe";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String addRecipe(@Valid @ModelAttribute RecipeModel newRecipeModel ,@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel, BindingResult bindingResult,
			Model model, Principal principal) {
		newRecipeModel.setRecipeCategoryModel(recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));
		newRecipeModel.setUserModel(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		System.out.println(newRecipeModel.getIdRecipe());

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}
		if(newRecipeModel.getUserModel() == null)
		{
			String errorMessage = "";
			errorMessage += "Username is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);
			
			return "forward:/login";
		}
		if (recipeCategoryModel.getIdCategory() == 0)
		{
			String errorMessage = "";
			errorMessage += "Recipe Category is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}
		
		List<RecipeModel> recipe3 = recipeRepository.findByName(newRecipeModel.getName());

		if (recipe3.size() > 0) {
			model.addAttribute("errorMessage", "Recipe already exists!<br>");
		} else {
			recipeRepository.save(newRecipeModel);
			RecipeModel recipe2 = newRecipeModel;
			model.addAttribute("message", "New recipe " + recipe2.getIdRecipe() + " added.");
		}

		return "forward:/list";
	}

	
	@RequestMapping(value = "/editRecipe", method = RequestMethod.GET)
	public String showChangeRecipeForm(Model model, @RequestParam int id, Principal principal,@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel) {
		

		
		List<RecipeCategoryModel> recipeCategoryModels = recipeCategoryRepository.findAll();
		model.addAttribute("recipeCategoryModels",recipeCategoryModels);
		RecipeModel recipe = recipeRepository.findByIdRecipe(id);
		if (recipe != null) {
			recipeCategoryModel = recipe.getRecipeCategoryModel();
			model.addAttribute("recipeCategoryModel", recipeCategoryModel);
			model.addAttribute("recipe", recipe);
			return "editRecipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/list";
		}
	}

	@RequestMapping(value = "/editRecipe", method = RequestMethod.POST)
	public String changeRecipe(@Valid @ModelAttribute RecipeModel changedRecipeModel,@RequestParam int id, BindingResult bindingResult,
			Model model, Principal principal,@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}
		if (recipeCategoryModel.getIdCategory() == 0)
		{
			String errorMessage = "";
			errorMessage += "Recipe Category is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		RecipeModel recipe = recipeRepository.findByIdRecipe(id);
		System.out.println(changedRecipeModel.getIdRecipe());

		if (recipe == null) {
			model.addAttribute("errorMessage", "Recipe does not exist!<br>");
		} else {
			recipe.setName(changedRecipeModel.getName());
			recipe.setDescription(changedRecipeModel.getDescription());
			recipe.setPreparation(changedRecipeModel.getPreparation());
			recipe.setRecipeCategoryModel(recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));
			RecipeModel recipe2 = recipeRepository.save(recipe);
			model.addAttribute("message", "Changed recipe " + recipe2.getIdRecipe());
		}

		return "forward:/list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "index";

	}

	

	
}