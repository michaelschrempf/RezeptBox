package at.fh.swenga.model;

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

	@Column(nullable = false)
	private int menge;
	
	@Column
	@ManyToMany
	@JoinTable
	private List<RecipeModel> recipeModels;
	
	

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

	

	public IngredientModel(int idIngredient, String name, int menge, List<RecipeModel> recipeModels) {
		super();
		this.idIngredient = idIngredient;
		this.name = name;
		this.menge = menge;
		this.recipeModels = recipeModels;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}



	public List<RecipeModel> getRecipeModels() {
		return recipeModels;
	}



	public void setRecipeModels(List<RecipeModel> recipeModels) {
		this.recipeModels = recipeModels;
	}



	public int getIdIngredient() {
		return idIngredient;
	}



	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
