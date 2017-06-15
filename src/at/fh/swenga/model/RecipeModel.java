package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "RecipeModel")
public class RecipeModel implements java.io.Serializable {

	// Attribute
	@Id
	@Column(name = "id_recipe")
	private int id;

	@Column(nullable = false, length = 45)
	private String name;

	@Column(nullable = false, length = 500)
	private String description;

	@Column(nullable = false, length = 1000)
	private String preparation;

	@Version
	long version;

	/*Realtionships*/
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="userModel_fk")
	UserModel userModel;
	
	/*@OneToMany(mappedBy="receptmodel", fetch=FetchType.EAGER)
	private Set<LikeModel> likes;*/
	
	/*@OneToMany(mappedBy="receptingredientmodel", fetch=FetchType.EAGER)
	private Set<ReceptIngredientModel> receptingredients;
	*/
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="recipeCategoryModel_fk")
	RecipeCategoryModel recipeCategoryModel;
	
	
	/*-----------------------*/
	public RecipeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeModel(int id,String name, String description, String preparation) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.preparation = preparation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public UserModel getUsermodel() {
		return userModel;
	}

	public void setUsermodel(UserModel usermodel) {
		this.userModel = usermodel;
	}

	/*public Set<LikeModel> getLikes() {
		return likes;
	}

	public void setLikes(Set<LikeModel> likes) {
		this.likes = likes;
	}*/

	public RecipeCategoryModel getRecipeCategoryModel() {
		return recipeCategoryModel;
	}

	public void setRecipeCategoryModel(RecipeCategoryModel recipeCategoryModel) {
		this.recipeCategoryModel = recipeCategoryModel;
	}

	/*public Set<ReceptIngredientModel> getReceptingredients() {
		return receptingredients;
	}

	public void setReceptingredients(Set<ReceptIngredientModel> receptingredients) {
		this.receptingredients = receptingredients;
	}*/
}
