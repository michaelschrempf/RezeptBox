package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class QuantityUnitModel implements java.io.Serializable {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUnit;

	@Column(nullable = false, length = 45)
	private String name;
	
	@Column
	private int amount;
	

	@ManyToOne
	private IngredientModel ingredientModel;
	
	//Relationship
	/*@OneToMany(mappedBy="quantityUnitModel", fetch=FetchType.EAGER)
	private Set<IngredientModel> ingredients;*/
	/*-----------------------------------------------*/
	
	public QuantityUnitModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public QuantityUnitModel(int idUnit, String name, int amount) {
		super();
		this.idUnit = idUnit;
		this.name = name;
		this.amount = amount;
	}



	public int getIdUnit() {
		return idUnit;
	}



	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	
	

	/*public Set<IngredientModel> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientModel> ingredients) {
		this.ingredients = ingredients;
	}*/

}
