package at.fh.swenga.account.service;

import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.IngredientDataModel;
import at.fh.swenga.model.IngredientModel;
import at.fh.swenga.model.UnitDataModel;
import at.fh.swenga.model.UserModel;

@Transactional
public interface IngredientService {
		@Transactional
	    public IngredientModel save(IngredientModel ingredient, UnitDataModel unitDataModel, IngredientDataModel ingredientDataModel);
	    @Transactional
	    public IngredientModel findByIdIngredient(int idIngredient);
		
	}
	

