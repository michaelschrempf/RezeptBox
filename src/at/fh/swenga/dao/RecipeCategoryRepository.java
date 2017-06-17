package at.fh.swenga.dao;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.RecipeCategoryModel;
import at.fh.swenga.model.RecipeModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserRoleModel;
 
 
@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategoryModel, Integer>{
	//allows us to read, update and delete RecipeModel in the database
	
	@Transactional
    public RecipeCategoryModel findByIdCategory(Integer idCategory);
	
	@Transactional
    public RecipeCategoryModel removeByIdCategory(Integer idCategory);
	
	@Transactional
    public List<RecipeCategoryModel> findByName(String name);
	
	
	
	
	
	
 
	
}