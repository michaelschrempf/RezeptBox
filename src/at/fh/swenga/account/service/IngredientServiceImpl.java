package at.fh.swenga.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.dao.IngredientDataRepository;
import at.fh.swenga.dao.IngredientRepository;
import at.fh.swenga.dao.UnitDataRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.model.IngredientDataModel;
import at.fh.swenga.model.IngredientModel;
import at.fh.swenga.model.UnitDataModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserRoleModel;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    
	@Autowired
    private IngredientRepository ingredientRepository;
	@Autowired
	private UnitDataRepository unitDataRepository;
	@Autowired
	private IngredientDataRepository ingredientDataRepository;
	@Autowired
	private UnitDataRepository unitDataModelRepository;
  
  
    


    @Override
    public IngredientModel save(IngredientModel ingredient, UnitDataModel unitDataModel, IngredientDataModel ingredientDataModel) {
    
    	ingredient.setUnitDataModel(unitDataRepository.findByIdUnitData(unitDataModel.getIdUnitData()));
    	ingredient.setAmount(ingredient.getAmount());
    	ingredient.setIngredientDataModel(ingredientDataRepository.findByIdIngredientData(ingredientDataModel.getIdIngredientData()));
        return ingredientRepository.save(ingredient);
        
    }

    @Override
    public IngredientModel findByIdIngredient(int idIngredient) {
        return ingredientRepository.findByIdIngredient(idIngredient);
    }
}