package at.fh.swenga.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.IngredientModel;

@Repository
@Transactional
public class IngredientDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<IngredientModel> getIngredientModels() {
 
		TypedQuery<IngredientModel> typedQuery = entityManager.createQuery(
				"select em from IngredientModel em", IngredientModel.class);
		List<IngredientModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public IngredientModel getIngredientModel(String name) {
		try {
 
			TypedQuery<IngredientModel> typedQuery = entityManager.createQuery(
					"select em from IngredientModel em where em.name = :name",
					IngredientModel.class);
			typedQuery.setParameter("name", name);
			IngredientModel IngredientModel = typedQuery.getSingleResult();
			return IngredientModel;
		} catch (NoResultException e) {
			return null;
		}
	}

}
