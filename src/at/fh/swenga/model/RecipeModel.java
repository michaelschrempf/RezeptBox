package at.fh.swenga.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;


@NamedQueries({
		@NamedQuery(name = "RecipeModel.doANameSearchWithLike", query = "select e from RecipeModel e where e.name like :search") })

@Entity
@Table
public class RecipeModel implements java.io.Serializable {

	// Attribute
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRecipe;

	@Column(nullable = false, length = 45)
	private String name;

	@Column(nullable = false, length = 2000)
	private String description;

	@Column(nullable = false, length = 2000)
	private String preparation;

	@Version
	long version;

	/* Realtionships */
	@ManyToOne
	@JoinColumn
	private UserModel userModel;

	@ManyToOne
	private RecipeCategoryModel recipeCategoryModel;

	@Column
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "recipemodel_ingredientmodel")
	private Set<IngredientModel> ingredientModels;

	/*
	 * @OneToMany(mappedBy="receptmodel", fetch=FetchType.EAGER) private
	 * Set<LikeModel> likes;
	 */

	/*
	 * @OneToMany(mappedBy="receptingredientmodel", fetch=FetchType.EAGER)
	 * private Set<ReceptIngredientModel> receptingredients;
	 */
	/*
	 * @ManyToOne (cascade = CascadeType.PERSIST)
	 * 
	 * @JoinColumn(name="recipeCategoryModel_fk") RecipeCategoryModel
	 * recipeCategoryModel;
	 * 
	 */
	/*-----------------------*/
	public RecipeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeModel(int idRecipe, String name, String description, String preparation, long version,
			UserModel userModel, RecipeCategoryModel recipeCategoryModel, Set<IngredientModel> ingredientModels) {
		super();
		this.idRecipe = idRecipe;
		this.name = name;
		this.description = description;
		this.preparation = preparation;
		this.version = version;
		this.userModel = userModel;
		this.recipeCategoryModel = recipeCategoryModel;
		this.ingredientModels = ingredientModels;
	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public RecipeCategoryModel getRecipeCategoryModel() {
		return recipeCategoryModel;
	}

	public void setRecipeCategoryModel(RecipeCategoryModel recipeCategoryModel) {
		this.recipeCategoryModel = recipeCategoryModel;
	}

	public Set<IngredientModel> getIngredientModels() {
		return ingredientModels;
	}

	public void setIngredientModels(Set<IngredientModel> ingredientModels) {
		this.ingredientModels = ingredientModels;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public void addIngredient(IngredientModel ingredient) {
		if (ingredientModels == null) {
			ingredientModels= new HashSet<IngredientModel>();
		}
		ingredientModels.add(ingredient);
	}

}
