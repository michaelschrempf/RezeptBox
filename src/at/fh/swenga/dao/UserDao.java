package at.fh.swenga.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public class UserDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<UserModel> getUserModels() {
 
		TypedQuery<UserModel> typedQuery = entityManager.createQuery(
				"select em from UserModel em", UserModel.class);
		List<UserModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public UserModel getUserModel(String name) {
		try {
 
			TypedQuery<UserModel> typedQuery = entityManager.createQuery(
					"select em from UserModel em where em.name = :name",
					UserModel.class);
			typedQuery.setParameter("name", name);
			UserModel UserModel = typedQuery.getSingleResult();
			return UserModel;
		} catch (NoResultException e) {
			return null;
		}
	}

}
