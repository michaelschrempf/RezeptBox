package at.fh.swenga.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RecipeIngredientModel")
public class RecipeIngredientModel implements java.io.Serializable {
	private static final long serialVersionUID = 8098173157518993615L;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="ingredientModel_fk")
	IngredientModel ingredientModel;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="recipeModel_fk")
	RecipeModel recipeModel;
		
	@Id
	@Column(name = "id_recept")
	private int id;

	
	public RecipeIngredientModel(int id) {
		super();
		this.id = id;
	}
	public RecipeIngredientModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
