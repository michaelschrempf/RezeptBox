package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QuantityUnitModel")
public class QuantityUnitModel implements java.io.Serializable {
	
	@Id
	@Column(name = "id_ingredient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 45)
	private String name;
	
	//Relationship
	/*@OneToMany(mappedBy="quantityUnitModel", fetch=FetchType.EAGER)
	private Set<IngredientModel> ingredients;*/
	/*-----------------------------------------------*/
	
	public QuantityUnitModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuantityUnitModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public Set<IngredientModel> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientModel> ingredients) {
		this.ingredients = ingredients;
	}*/

}
