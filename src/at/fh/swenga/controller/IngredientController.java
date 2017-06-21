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

	
	@RequestMapping("/deleteIngredient")
	public String deleteRecipe(Model model, @RequestParam int id, Principal principal) {
		ingredientRepository.delete(id);

		return "forward:/list";
	}
	

}