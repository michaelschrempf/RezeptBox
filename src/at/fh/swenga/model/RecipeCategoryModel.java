package at.fh.swenga.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "RecipeCategoryModel")
public class RecipeCategoryModel implements java.io.Serializable {

	/*@OneToMany(mappedBy="receptmodel", fetch=FetchType.EAGER)
	private Set<ReceptModel> receptModel;*/
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;

	@Column(nullable = false, length = 45)
	private String name;
	
	@Column
	@OneToMany(mappedBy = "recipeCategoryModel", fetch = FetchType.EAGER)
	private List<RecipeModel> recipeModel;
	
	
	
	@Version
	long version;
	
	public RecipeCategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public RecipeCategoryModel(int idCategory, String name, List<RecipeModel> recipeModel, long version) {
		super();
		this.idCategory = idCategory;
		this.name = name;
		this.recipeModel = recipeModel;
		this.version = version;
	}
	
	public int getIdCategory() {
		return idCategory;
	}



	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<RecipeModel> getRecipeModel() {
		return recipeModel;
	}



	public void setRecipeModel(List<RecipeModel> recipeModel) {
		this.recipeModel = recipeModel;
	}



	public long getVersion() {
		return version;
	}



	public void setVersion(long version) {
		this.version = version;
	}
	
	



	

	

}
