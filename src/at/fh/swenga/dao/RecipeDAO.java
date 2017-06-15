package at.fh.swenga.dao;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.RecipeModel;
 
 
@Repository
@Transactional
public class RecipeDAO {
	//allows us to read, update and delete RecipeModel in the database
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<RecipeModel> getRecipe() {
		TypedQuery<RecipeModel> typedQuery = entityManager.createQuery("select r from RecipeModel r",
				RecipeModel.class);
		List<RecipeModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public List<RecipeModel> searchRecipe(String searchString) {
		TypedQuery<RecipeModel> typedQuery = entityManager.createQuery(
				"select r from RecipeModel r where r.name like :search",
				RecipeModel.class);
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<RecipeModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public RecipeModel getRecipe(int i) throws DataAccessException {
		return entityManager.find(RecipeModel.class, i);
	}
 
	public void persist(RecipeModel Recipe) {
		entityManager.persist(Recipe);
	}
 
	public RecipeModel merge(RecipeModel Recipe) {
		return entityManager.merge(Recipe);
	}
 
	public void delete(RecipeModel Recipe) {
		entityManager.remove(Recipe);
	}
 
	public int deleteAllRecipes() {
		int count = entityManager.createQuery("DELETE FROM RecipeModel").executeUpdate();
		return count;
	}
 
	public void delete(int id) {
		RecipeModel Recipe = getRecipe(id);
		if (Recipe != null)
			delete(Recipe);
	}
	private List<RecipeModel> recipes = new ArrayList<RecipeModel>();
	 

	public void addRecipe(RecipeModel recipe) {
		recipes.add(recipe);
	}
 
	public boolean contains(RecipeModel recipe) {
		return recipes.contains(recipe);
	}

	public boolean isEmpty() {
		return recipes.isEmpty();
	}
	public RecipeModel getRecipeById(int id) {
		for (RecipeModel recipeModel : recipes) {
			if (recipeModel.getId() == id) {
				return recipeModel;
			}
		}
		return null;
	}
 
	public List<RecipeModel> getAllRecipes() {
		return recipes;
	}

	public List<RecipeModel> getFilteredRecipes(String searchString) {
 
		if (searchString == null || searchString.equals("")) {
			return recipes;
		}
 
		// List for results
		List<RecipeModel> filteredList = new ArrayList<RecipeModel>();
 
		// check every recipe
		for (RecipeModel recipeModel : recipes) {
 
			if ((recipeModel.getName() != null && recipeModel.getName().contains(searchString))
					|| (recipeModel.getDescription() != null && recipeModel.getDescription().contains(searchString))) {
				filteredList.add(recipeModel);
			}
		}
		return filteredList;
	}

	public boolean remove(int id) {
		return recipes.remove(new RecipeModel(id,null, null, null));
	}
}