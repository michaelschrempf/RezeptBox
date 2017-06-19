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
import org.springframework.web.bind.annotation.RequestParam;

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
import at.fh.swenga.model.IngredientModel;
import at.fh.swenga.model.RecipeCategoryModel;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserRoleModel;

@Controller
public class IngredientController {

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
	private RecipeCategoryRepository recipeCategoryRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
	public String showEditRecipeForm(Model model, @RequestParam int id, Principal principal,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel) {

		List<RecipeCategoryModel> recipeCategoryModels = recipeCategoryRepository.findAll();
		model.addAttribute("recipeCategoryModels", recipeCategoryModels);
		RecipeModel recipe = recipeRepository.findByIdRecipe(id);
		if (recipe != null) {
			recipeCategoryModel = recipe.getRecipeCategoryModel();
			model.addAttribute("recipeCategoryModel", recipeCategoryModel);
			model.addAttribute("recipe", recipe);
			model.addAttribute("ingredientModels", ingredientRepository.findAll());
			model.addAttribute("ingredients", recipe.getIngredientModels());
			return "editRecipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/editRecipe";
		}
	}

	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
	public String buttonAddIngredient(@Valid @ModelAttribute RecipeModel changedRecipeModel, @RequestParam int id,
			BindingResult bindingResult, Model model, Principal principal,
			@Valid @ModelAttribute("recipeCategoryModel") RecipeCategoryModel recipeCategoryModel,
			@Valid @ModelAttribute("ingredientModel") IngredientModel ingredientModel,
			@RequestParam(value="buttonAddIngredient", required=true) String buttonAddIngredient) {
		if (id == 0) {
			changedRecipeModel.setRecipeCategoryModel(
					recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));
			changedRecipeModel.setUserModel(
					userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
			changedRecipeModel.addIngredient(ingredientModel);
			System.out.println(changedRecipeModel.getIdRecipe());
			System.out.println(changedRecipeModel.getRecipeCategoryModel().getName());

			if (bindingResult.hasErrors()) {
				String errorMessage = "";
				for (FieldError fieldError : bindingResult.getFieldErrors()) {
					errorMessage += fieldError.getField() + " is invalid<br>";
				}
				model.addAttribute("errorMessage", errorMessage);
				return "forward:/list";
			}
			if (changedRecipeModel.getUserModel() == null) {
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

			System.out.println(changedRecipeModel.getName());

			recipeRepository.save(changedRecipeModel);
			RecipeModel recipe2 = changedRecipeModel;
			model.addAttribute("message", "New recipe " + recipe2.getIdRecipe() + " added.");
			System.out.println(changedRecipeModel.getName());
			return "editRecipe";

		}
		// -----------------------------------------------------------------------------------------
		else {
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
			System.out.println(changedRecipeModel.getIdRecipe());

			if (recipe == null) {
				model.addAttribute("errorMessage", "Recipe does not exist!<br>");
			} else {
				recipe.setName(changedRecipeModel.getName());
				recipe.setDescription(changedRecipeModel.getDescription());
				recipe.setPreparation(changedRecipeModel.getPreparation());
				recipe.addIngredient(ingredientModel);
				recipe.setRecipeCategoryModel(
						recipeCategoryRepository.findByIdCategory(recipeCategoryModel.getIdCategory()));

				RecipeModel recipe2 = recipeRepository.save(recipe);
				model.addAttribute("message", "Changed recipe " + recipe2.getIdRecipe());
				return "editRecipe";
			}
		}
		return "editRecipe";
	} 

}