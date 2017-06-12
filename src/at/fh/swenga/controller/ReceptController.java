package at.fh.swenga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.IngredientDao;
import at.fh.swenga.dao.ReceptDao;
import at.fh.swenga.dao.UserDao;
import at.fh.swenga.model.ReceptModel;
import at.fh.swenga.model.UserModel;

@Controller
public class ReceptController {

	@Autowired
	ReceptDao ReceptDao;

	@Autowired
	UserDao UserDao;
	
	@Autowired
	IngredientDao ingredientDao;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<ReceptModel> recepts = ReceptDao.getRecepts();
		List<UserModel> users = UserDao.getUserModels();
		
		model.addAttribute("Recepts", recepts);
		model.addAttribute("Users", users);
		return "index";
	}

	@RequestMapping("/fillReceptList")
	@Transactional
	public String fillData(Model model) {
		
		UserModel user1 = UserDao.getUserModel("Kevin");
		if (user1==null) user1 = new UserModel("kevin");
		
		UserModel user2 = UserDao.getUserModel("Martin");
		if (user2==null) user2 = new UserModel("Martin");
		
		UserModel user3 = UserDao.getUserModel("Sebastian");
		if (user3==null) user3 = new UserModel("Sebastian");

		ReceptModel p1 = new ReceptModel("Cordon Bleu", "Gefülltes Schnitzel", "hahahahah");
		p1.setUsermodel(user1);
		ReceptDao.persist(p1);

		ReceptModel p2 = new ReceptModel("Schnitzel", "Schnitzel", "fritieren");
		p2.setUsermodel(user2);
		ReceptDao.persist(p2);

		ReceptModel p3 = new ReceptModel("Erdberren", "Doe", "now");
		p3.setUsermodel(user3);
		ReceptDao.persist(p3);

		return "forward:list";
	}

	@RequestMapping("/searchRecepts")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("Recepts", ReceptDao.searchRecepts(searchString));
		model.addAttribute("Users", UserDao.getUserModel(searchString));
		return "index";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		ReceptDao.delete(id);

		return "forward:list";
	}

	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}