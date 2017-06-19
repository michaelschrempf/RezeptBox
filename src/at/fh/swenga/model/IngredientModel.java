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

	@Column(nullable = false, length = 45)
	private String name;

	@Column
	@ManyToMany(mappedBy="ingredientModels", fetch=FetchType.EAGER)
	private Set<RecipeModel> recipeModel;
	
	@Column
	@OneToMany(mappedBy = "ingredientModel", fetch = FetchType.EAGER)
	private Set<QuantityUnitModel> quantityUnitModels;
	
	

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

	

	



	public IngredientModel(int idIngredient, String name, Set<RecipeModel> recipeModel,
			Set<QuantityUnitModel> quantityUnitModels) {
		super();
		this.idIngredient = idIngredient;
		this.name = name;
		this.recipeModel = recipeModel;
		this.quantityUnitModels = quantityUnitModels;
	}







	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<RecipeModel> getRecipeModel() {
		return recipeModel;
	}



	public void setRecipeModel(Set<RecipeModel> recipeModel) {
		this.recipeModel = recipeModel;
	}



	public int getIdIngredient() {
		return idIngredient;
	}



	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	
	



	public Set<QuantityUnitModel> getQuantityUnitModels() {
		return quantityUnitModels;
	}







	public void setQuantityUnitModels(Set<QuantityUnitModel> quantityUnitModels) {
		this.quantityUnitModels = quantityUnitModels;
	}







	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void addQuantityUnitModel(QuantityUnitModel quantityUnitModel) {
		if (quantityUnitModels==null) {
			quantityUnitModels= new HashSet<QuantityUnitModel>();
		}
		quantityUnitModels.add(quantityUnitModel);
	}
	
	
	

}
