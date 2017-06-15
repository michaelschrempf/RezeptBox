package at.fh.swenga.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.IngredientDAO;
import at.fh.swenga.dao.RecipeDAO;
import at.fh.swenga.dao.UserDAO;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UserModel;

@Controller
public class RecipeController {

	@Autowired
	RecipeDAO recipeDAO;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	IngredientDAO ingredientDAO;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<RecipeModel> recipes = recipeDAO.getAllRecipes();
		List<UserModel> users = userDAO.getAllUserModels();
		
		model.addAttribute("Recipes", recipes);
		model.addAttribute("Users", users);
		return "index";
	}

	@RequestMapping("/fillRecipeList")
	@Transactional
	public String fillData(Model model) {
		
		UserModel user1 = new UserModel(1,"kevin","stessel","kiv1995","cobra11");

		RecipeModel p1 = new RecipeModel(1,"Cordon Bleu", "Gefülltes Schnitzel", "hahahahah");
		p1.setUsermodel(user1);
		recipeDAO.persist(p1);

		/*RecipeModel p2 = new RecipeModel(2,"Schnitzel", "Schnitzel", "fritieren");
		//p2.setUsermodel(user2);
		recipeDAO.persist(p2);

		RecipeModel p3 = new RecipeModel(3,"Erdberren", "Doe", "now");
		//p3.setUsermodel(user3);
		recipeDAO.persist(p3);*/

		return "forward:list";
	}

	@RequestMapping("/deleteRecipe")
	public String delete(Model model, @RequestParam int id) {
		boolean isRemoved = recipeDAO.remove(id);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Recipe " + id + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no Recipe " + id);
		}

		// Multiple ways to "forward" to another Method
		// return "forward:/listRecipes";
		return index(model);
	}

	@RequestMapping("/searchRecipe")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("recipes", recipeDAO.getFilteredRecipes(searchString));
		return "index";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String showAddRecipeForm(Model model) {
		return "editRecipe";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String addRecipe(@Valid @ModelAttribute RecipeModel newRecipeModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listRecipes";
		}
		RecipeModel recipe = recipeDAO.getRecipeById(newRecipeModel.getId());

		if (recipe != null) {
			model.addAttribute("errorMessage", "Recipe already exists!<br>");
		} else {
			recipeDAO.addRecipe(newRecipeModel);
			model.addAttribute("message", "New recipe " + newRecipeModel.getId() + " added.");
		}

		return "forward:/list";
	}

	@RequestMapping(value = "/editRecipe", method = RequestMethod.GET)
	public String showChangeRecipeForm(Model model, @RequestParam int id) {
		RecipeModel recipe = recipeDAO.getRecipeById(id);
		if (recipe != null) {
			model.addAttribute("recipe", recipe);
			return "editRecipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/list";
		}
	}

	@RequestMapping(value = "/editRecipe", method = RequestMethod.POST)
	public String changeRecipe(@Valid @ModelAttribute RecipeModel changedRecipeModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		RecipeModel recipe = recipeDAO.getRecipeById(changedRecipeModel.getId());

		if (recipe == null) {
			model.addAttribute("errorMessage", "Recipe does not exist!<br>");
		} else {
			recipe.setId(changedRecipeModel.getId());
			recipe.setName(changedRecipeModel.getName());
			recipe.setDescription(changedRecipeModel.getDescription());
			recipe.setPreparation(changedRecipeModel.getPreparation());
			recipe.setUsermodel(changedRecipeModel.getUsermodel());
			model.addAttribute("message", "Changed recipe " + changedRecipeModel.getId());
		}

		return "forward:/list";
	}
	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}