package at.fh.swenga.dao;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.ReceptModel;
 
 
@Repository
@Transactional
public class ReceptDao {
	//allows us to read, update and delete ReceptModel in the database
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<ReceptModel> getRecepts() {
		TypedQuery<ReceptModel> typedQuery = entityManager.createQuery("select r from ReceptModel r",
				ReceptModel.class);
		List<ReceptModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public List<ReceptModel> searchRecepts(String searchString) {
		TypedQuery<ReceptModel> typedQuery = entityManager.createQuery(
				"select r from ReceptModel r where r.name like :search",
				ReceptModel.class);
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<ReceptModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public ReceptModel getRecept(int i) throws DataAccessException {
		return entityManager.find(ReceptModel.class, i);
	}
 
	public void persist(ReceptModel Recept) {
		entityManager.persist(Recept);
	}
 
	public ReceptModel merge(ReceptModel Recept) {
		return entityManager.merge(Recept);
	}
 
	public void delete(ReceptModel Recept) {
		entityManager.remove(Recept);
	}
 
	public int deleteAllRecepts() {
		int count = entityManager.createQuery("DELETE FROM ReceptModel").executeUpdate();
		return count;
	}
 
	public void delete(int id) {
		ReceptModel Recept = getRecept(id);
		if (Recept != null)
			delete(Recept);
	}
	private List<ReceptModel> recepts = new ArrayList<ReceptModel>();
	 

	public void addRecept(ReceptModel recept) {
		recepts.add(recept);
	}
 
	public boolean contains(ReceptModel recept) {
		return recepts.contains(recept);
	}

	public boolean isEmpty() {
		return recepts.isEmpty();
	}
	public ReceptModel getReceptById(int id) {
		for (ReceptModel receptModel : recepts) {
			if (receptModel.getId() == id) {
				return receptModel;
			}
		}
		return null;
	}
 
	public List<ReceptModel> getAllRecepts() {
		return recepts;
	}

	public List<ReceptModel> getFilteredRecepts(String searchString) {
 
		if (searchString == null || searchString.equals("")) {
			return recepts;
		}
 
		// List for results
		List<ReceptModel> filteredList = new ArrayList<ReceptModel>();
 
		// check every recept
		for (ReceptModel receptModel : recepts) {
 
			if ((receptModel.getName() != null && receptModel.getName().contains(searchString))
					|| (receptModel.getBeschreibung() != null && receptModel.getBeschreibung().contains(searchString))) {
				filteredList.add(receptModel);
			}
		}
		return filteredList;
	}

	public boolean remove(int id) {
		return recepts.remove(new ReceptModel(id,null, null, null));
	}
}