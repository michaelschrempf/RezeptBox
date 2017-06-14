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

import at.fh.swenga.dao.IngredientDao;
import at.fh.swenga.dao.ReceptDao;
import at.fh.swenga.dao.UserDao;
import at.fh.swenga.model.ReceptModel;
import at.fh.swenga.model.UserModel;

@Controller
public class ReceptController {

	@Autowired
	ReceptDao receptDao;

	@Autowired
	UserDao UserDao;
	
	@Autowired
	IngredientDao ingredientDao;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<ReceptModel> recepts = receptDao.getRecepts();
		List<UserModel> users = UserDao.getUserModels();
		
		model.addAttribute("Recepts", recepts);
		model.addAttribute("Users", users);
		return "index";
	}

	@RequestMapping("/fillReceptList")
	@Transactional
	public String fillData(Model model) {
		
		UserModel user1 = new UserModel(1,"kevin","stessel","kiv1995","cobra11");

		ReceptModel p1 = new ReceptModel(1,"Cordon Bleu", "Gefülltes Schnitzel", "hahahahah");
		p1.setUsermodel(user1);
		receptDao.persist(p1);

		/*ReceptModel p2 = new ReceptModel(2,"Schnitzel", "Schnitzel", "fritieren");
		//p2.setUsermodel(user2);
		receptDao.persist(p2);

		ReceptModel p3 = new ReceptModel(3,"Erdberren", "Doe", "now");
		//p3.setUsermodel(user3);
		receptDao.persist(p3);*/

		return "forward:list";
	}

	@RequestMapping("/deleteRecept")
	public String delete(Model model, @RequestParam int id) {
		boolean isRemoved = receptDao.remove(id);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Recept " + id + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no Recept " + id);
		}

		// Multiple ways to "forward" to another Method
		// return "forward:/listRecepts";
		return index(model);
	}

	@RequestMapping("/searchRecepts")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("recepts", receptDao.getFilteredRecepts(searchString));
		return "index";
	}

	@RequestMapping(value = "/addRecept", method = RequestMethod.GET)
	public String showAddReceptForm(Model model) {
		return "editRecept";
	}

	@RequestMapping(value = "/addRecept", method = RequestMethod.POST)
	public String addRecept(@Valid @ModelAttribute ReceptModel newReceptModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listRecepts";
		}
		ReceptModel recept = receptDao.getReceptById(newReceptModel.getId());

		if (recept != null) {
			model.addAttribute("errorMessage", "Recept already exists!<br>");
		} else {
			receptDao.addRecept(newReceptModel);
			model.addAttribute("message", "New recept " + newReceptModel.getId() + " added.");
		}

		return "forward:/list";
	}

	@RequestMapping(value = "/editRecept", method = RequestMethod.GET)
	public String showChangeReceptForm(Model model, @RequestParam int id) {
		ReceptModel recept = receptDao.getReceptById(id);
		if (recept != null) {
			model.addAttribute("recept", recept);
			return "editRecept";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recept " + id);
			return "forward:/list";
		}
	}

	@RequestMapping(value = "/editRecept", method = RequestMethod.POST)
	public String changeRecept(@Valid @ModelAttribute ReceptModel changedReceptModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}

		ReceptModel recept = receptDao.getReceptById(changedReceptModel.getId());

		if (recept == null) {
			model.addAttribute("errorMessage", "Recept does not exist!<br>");
		} else {
			recept.setId(changedReceptModel.getId());
			recept.setName(changedReceptModel.getName());
			recept.setBeschreibung(changedReceptModel.getBeschreibung());
			recept.setZubereitung(changedReceptModel.getZubereitung());
			recept.setUsermodel(changedReceptModel.getUsermodel());
			model.addAttribute("message", "Changed recept " + changedReceptModel.getId());
		}

		return "forward:/list";
	}
	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}