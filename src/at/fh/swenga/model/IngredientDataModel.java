package at.fh.swenga.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class IngredientDataModel implements java.io.Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idIngredientData;

	@Column
	private String name;
	
	

	@Column
	@OneToMany(mappedBy = "ingredientDataModel", fetch = FetchType.EAGER)
	private Set<IngredientModel> ingredientModels;

	public IngredientDataModel(int idIngredientData, String name, Set<IngredientModel> ingredientModels) {
		super();
		this.idIngredientData = idIngredientData;
		this.name = name;
		this.ingredientModels = ingredientModels;
	}

	public IngredientDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdIngredientData() {
		return idIngredientData;
	}

	public void setIdIngredientData(int idIngredientData) {
		this.idIngredientData = idIngredientData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<IngredientModel> getIngredientModels() {
		return ingredientModels;
	}

	public void setIngredientModels(Set<IngredientModel> ingredientModels) {
		this.ingredientModels = ingredientModels;
	}
	
	public void add(IngredientModel ingredientModel) {
		if (ingredientModels == null) {
			ingredientModels= new HashSet<IngredientModel>();
		}
		
		
		ingredientModels.add(ingredientModel);
	}


}
