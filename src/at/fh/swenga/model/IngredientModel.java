package at.fh.swenga.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "IngredientModel")
public class IngredientModel implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534382506428673043L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idIngredient;

	
	@Column
	private int amount;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private IngredientDataModel ingredientDataModel;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private UnitDataModel unitDataModel;
	
	

	@ManyToMany(mappedBy="ingredientModels")
	private Set<RecipeModel> recipeModels;
	
	
	

		

	
	
	




	public IngredientModel(int idIngredient, UnitDataModel unitDataModel, int amount,
			Set<RecipeModel> recipeModels, IngredientDataModel ingredientDataModel) {
		super();
		this.idIngredient = idIngredient;
		this.unitDataModel = unitDataModel;
		this.amount = amount;
		this.recipeModels = recipeModels;
		this.ingredientDataModel = ingredientDataModel;
	}




	/*---------------------------------------------------------*/
	/*@OneToMany(mappedBy="ingredientModel", fetch=FetchType.EAGER)
	private Set<RecipeIngredientModel> recipeIngredients;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="quantityUnitModel_fk")
	QuantityUnitModel quantityUnits;*/

	/*---------------------------------------------------------*/

	public IngredientModel() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getIdIngredient() {
		return idIngredient;
	}




	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}



	




	public UnitDataModel getUnitDataModel() {
		return unitDataModel;
	}




	public void setUnitDataModel(UnitDataModel unitDataModel) {
		this.unitDataModel = unitDataModel;
	}
	
	



	public Set<RecipeModel> getRecipeModels() {
		return recipeModels;
	}




	public void setRecipeModels(Set<RecipeModel> recipeModels) {
		this.recipeModels = recipeModels;
	}
	
	




	public IngredientDataModel getIngredientDataModel() {
		return ingredientDataModel;
	}






	public void setIngredientDataModel(IngredientDataModel ingredientDataModel) {
		this.ingredientDataModel = ingredientDataModel;
	}
	
	






	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void add(RecipeModel recipeModel) {
		if (recipeModels == null) {
			recipeModels= new HashSet<RecipeModel>();
		}
		
		
		recipeModels.add(recipeModel);
	}

	
	
	
	
	



	









	




	
	
	

}
