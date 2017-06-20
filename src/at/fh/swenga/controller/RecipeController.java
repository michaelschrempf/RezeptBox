package at.fh.swenga.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.account.service.IngredientService;
import at.fh.swenga.account.service.SecurityService;
import at.fh.swenga.account.service.UserService;
import at.fh.swenga.account.validator.UserValidator;
import at.fh.swenga.dao.IngredientRepository;
import at.fh.swenga.dao.IngredientDataRepository;
import at.fh.swenga.dao.RecipeCategoryRepository;
import at.fh.swenga.dao.RecipeRepository;
import at.fh.swenga.dao.UnitDataRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.model.IngredientModel;
import at.fh.swenga.model.IngredientDataModel;
import at.fh.swenga.model.RecipeCategoryModel;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UnitDataModel;

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
	private UnitDataRepository unitDataRepository;
	@Autowired
	private IngredientDataRepository ingredientDataRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<RecipeModel> recipeModels = recipeRepository.findAll();

		model.addAttribute("recipeModels", recipeModels);

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

		return "forward:/list";
	}

	@RequestMapping("/showRecipe")
	public String showRecipe(Model model, @RequestParam int id) {

		RecipeModel recipeModel = recipeRepository.findByIdRecipe(id);
		if (recipeModel != null) {
			model.addAttribute("recipeModel", recipeRepository.findByIdRecipe(id));

			model.addAttribute("ingredientModels", recipeModel.getIngredientModels());
			return "showRecipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/list";
		}
	}

	@RequestMapping("/searchRecipes")
	public String searchRecipes(Model model, @RequestParam String searchString) {
		model.addAttribute("recipes", recipeRepository.doANameSearchWithLike("%" + searchString + "%"));
		return "index";
	}

	@RequestMapping("/find")
	public String findRecipes(Model model, @RequestParam String searchString, @RequestParam String searchType) {
		List<RecipeModel> recipeModels = null;
		int count = 0;

		switch (searchType) {
		case "query1":
			recipeModels = recipeRepository.findAll();
			break;
		case "query2":
			recipeModels = recipeRepository.findByRecipeCategoryModelName(searchString);
			break;
		case "query3":
			recipeModels = recipeRepository.findByUserModelUsername(searchString);
			break;

		default:
			recipeModels = recipeRepository.findAll();
		}

		model.addAttribute("recipeModels", recipeModels);

		return "index";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String showAddRecipeForm(Model model,
			@Valid @ModelAttribute("ingredientDataModel") IngredientDataModel ingredientDataModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("unitDataModel") UnitDataModel unitDataModel) {
		model.addAttribute("recipeCategoryModels", recipeCategoryRepository.findAll());
		model.addAttribute("ingredientDataModels", ingredientDataRepository.findAll());
		model.addAttribute("unitDataModels", unitDataRepository.findAll());
		
		return "editRecipe";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String addRecipe(@Valid @ModelAttribute("recipeModel") RecipeModel recipeModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel,
			@Valid @ModelAttribute("ingredientDataModel") IngredientDataModel ingredientDataModel,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("unitDataModel") UnitDataModel unitDataModel,

			BindingResult bindingResult,
			Model model, Principal principal, @RequestParam(value = "action", required = true) String action) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}
		
		
		System.out.println(unitDataModel.getIdUnitData());
		System.out.println(unitDataModel.getName());
		System.out.println(ingredientDataModel.getIdIngredientData());
		System.out.println(ingredientDataModel.getName());
		
		if(ingredientModel.getAmount() > 0 && unitDataModel.getIdUnitData() > 0 && ingredientDataModel.getIdIngredientData() > 0)
		{
			recipeModel.add(ingredientService.save(ingredientModel,unitDataModel,ingredientDataModel));
		}

		
		recipeModel.setUserModel(
				userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		 recipeModel
				.setRecipeCategoryModel(recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));
	
		
		
		System.out.println(recipeModel.getIdRecipe());
		System.out.println(recipeModel.getRecipeCategoryModel().getName());

		
		if (recipeModel.getUserModel() == null) {
			String errorMessage = "";
			errorMessage += "Username is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);

			return "forward:/login";
		}
		if (recipeCategoryModel.getIdCategory() == 0) {
			String errorMessage = "";
			errorMessage += "Recipe Category is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		System.out.println(recipeModel.getNameRecipe());

		recipeRepository.save(recipeModel);
		RecipeModel recipe2 = recipeModel;
		model.addAttribute("message", "New recipe " + recipe2.getIdRecipe() + " added.");
		System.out.println(recipeModel.getNameRecipe());

		if (action.equals("addIngredient")) {
			return "redirect:/editRecipe?id=" + recipe2.getIdRecipe();
		}
		
		
		
		

		return "forward:/list"; 
	}

	@RequestMapping(value = "/editRecipe", method = RequestMethod.GET)
	public String showEditRecipeForm(Model model, @RequestParam int id, Principal principal,
			@Valid @ModelAttribute("recipeModel") RecipeModel recipeModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel,
			@Valid @ModelAttribute("ingredientDataModel") IngredientDataModel ingredientDataModel,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("unitDataModel") UnitDataModel unitDataModel) {

		List<RecipeCategoryModel> recipeCategoryModels = recipeCategoryRepository.findAll();
		model.addAttribute("recipeCategoryModels", recipeCategoryModels);
		RecipeModel recipe = recipeRepository.findByIdRecipe(id);
		if (recipe != null) {
			recipeCategoryModel = recipe.getRecipeCategoryModel();
			model.addAttribute("recipeCategoryModel", recipeCategoryModel);
			model.addAttribute("recipeModel", recipe);
			model.addAttribute("ingredientDataModels",ingredientDataRepository.findAll());
			model.addAttribute("ingredients", recipe.getIngredientModels());
			model.addAttribute("unitDataModels", unitDataRepository.findAll());

			return "editRecipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/list";
		}
	}

	@RequestMapping(value = "/editRecipe", method = RequestMethod.POST)
	public String editRecipe(
			@Valid @ModelAttribute("recipeModel") RecipeModel recipeModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel,
			@Valid @ModelAttribute("ingredientDataModel") IngredientDataModel ingredientDataModel,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("unitDataModel") UnitDataModel unitDataModel,
			@RequestParam int id,
			BindingResult bindingResult, Model model, Principal principal,
			@RequestParam(value = "action", required = true) String action) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		if (recipeCategoryModel.getIdCategory() == 0) {
			String errorMessage = "";
			errorMessage += "Recipe Category is invalid<br>";
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		RecipeModel recipe = recipeRepository.findByIdRecipe(id);
		System.out.println(recipeModel.getIdRecipe());

		if (recipe == null) {
			model.addAttribute("errorMessage", "Recipe does not exist!<br>");
		} else {

			recipe.setNameRecipe(recipeModel.getNameRecipe());
			recipe.setDescription(recipeModel.getDescription());
			recipe.setPreparation(recipeModel.getPreparation());
			if(ingredientModel.getAmount() > 0 && unitDataModel.getIdUnitData() > 0 && ingredientDataModel.getIdIngredientData() > 0)
			{
				recipe.add(ingredientService.save(ingredientModel,unitDataModel,ingredientDataModel));
			}
			recipe.setRecipeCategoryModel(
					recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));

			RecipeModel recipe2 = recipeRepository.save(recipe);
			model.addAttribute("message", "Changed recipe " + recipe2.getIdRecipe());
			if (action.equals("addIngredient")) {
				return "redirect:/editRecipe?id=" + recipe2.getIdRecipe();
			}
		}

		return "forward:/list";
	}

}